package org.nora.modules.shiro;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.nora.modules.system.entity.SysUser;
import org.nora.modules.system.mapper.SysMenuPermissionMapper;
import org.nora.modules.system.service.ISysUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;

@Component("authorizer")
public class UserRealm extends AuthorizingRealm {

    private static final Logger log =LoggerFactory.getLogger(UserRealm.class);

    @Autowired
    private ISysUserService userService;
    @Autowired
    private SysMenuPermissionMapper permissionMapper;


    @Override
    public void setCredentialsMatcher(CredentialsMatcher credentialsMatcher) {
        //设置用于匹配密码的CredentialsMatcher
        HashedCredentialsMatcher hashMatcher = new HashedCredentialsMatcher();
        hashMatcher.setHashAlgorithmName(Sha256Hash.ALGORITHM_NAME);
        hashMatcher.setStoredCredentialsHexEncoded(false);
        hashMatcher.setHashIterations(1024);
        super.setCredentialsMatcher(hashMatcher);
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        if (principals == null) {
            throw new AuthorizationException("PrincipalCollection method argument cannot be null.");
        }
        SysUser user = (SysUser) getAvailablePrincipal(principals);
//        String userName = user.getUserName();
//        SysUser sysUser = userService.getOne(new QueryWrapper<SysUser>().eq("user_name", userName));
//        String guid = sysUser.getGuid();
        List<String> roleIds = userService.getRoleIds(user.getGuid());
        List<String> permissions = permissionMapper.getUserPermissions(user.getGuid());
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setRoles(new HashSet<>(roleIds));
        info.setStringPermissions(new HashSet<>(permissions));
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken upToken = (UsernamePasswordToken) token;
        String username = upToken.getUsername();

        if (username == null) {
            throw new AccountException("用户名不能为空");
        }

        SysUser userDB = userService.getOne(new QueryWrapper<SysUser>().eq("user_name", username));
        if (userDB == null) {
            throw new UnknownAccountException("找不到用户（"+username+"）的帐号信息");
        }

        //查询用户的角色和权限存到SimpleAuthenticationInfo中，这样在其它地方
        //SecurityUtils.getSubject().getPrincipal()就能拿出用户的所有信息，包括角色和权限
//        Set<AuthVo> roles = roleService.getRolesByUserId(userDB.getUid());
//        Set<AuthVo> perms = permService.getPermsByUserId(userDB.getUid());
//        userDB.getRoles().addAll(roles);
//        userDB.getPerms().addAll(perms);

        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(userDB, userDB.getPassword(), getName());
        if (userDB.getSalt() != null) {
            info.setCredentialsSalt(ByteSource.Util.bytes(userDB.getSalt()));
        }
        return info;

    }
}
