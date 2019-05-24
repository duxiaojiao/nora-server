package org.nora.modules.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.nora.modules.system.entity.SysUser;
import org.nora.modules.system.entity.SysUserRole;
import org.nora.modules.system.mapper.SysUserMapper;
import org.nora.modules.system.mapper.SysUserRoleMapper;
import org.nora.modules.system.service.ISysUserRoleService;
import org.nora.modules.system.service.ISysUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


/**
 * <p>
 * 系统用户表 服务实现类
 * </p>
 *
 * @author taylor
 * @since 2019-05-16
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private ISysUserRoleService userRoleService;
    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;

    @Override
    @Transactional
    public void addUser(SysUser user,List<String> roleIds) throws RuntimeException {

        user.setLocked(true); //有效
        user.setPassword("123456");
        try {
            sysUserMapper.insert(user);
            if (CollectionUtils.isNotEmpty(roleIds)) {
                for (String roleId : roleIds) {
                    SysUserRole userRole = new SysUserRole();
                    userRole.setUserId(user.getGuid());
                    userRole.setRoleId(roleId);
                    sysUserRoleMapper.insert(userRole);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("添加用户失败");
        }
    }

    @Override
    public IPage<SysUser> queryUser(Integer pageNum, Integer pageSize, String search) {
        QueryWrapper<SysUser> wrapper = new QueryWrapper<>();
        if (StringUtils.isNoneEmpty(search)) {
            wrapper.like(true,"emp_code", "%"+search+"%").or()
                    .like(true,"emp_name", "%"+search+"%").or()
                    .like(true,"email", "%"+search+"%").or()
                    .like(true,"phone", "%"+search+"%");
        }
        Page<SysUser> page = new Page<>(pageNum,pageSize);
        return sysUserMapper.selectPage(page,wrapper);
    }

    @Override
    public void deleteUser(String guid) {
        sysUserMapper.deleteById(guid);
    }

    @Override
    @Transactional
    public void editUser(SysUser user,List<String> roleIds) {
        sysUserMapper.updateById(user);
        sysUserRoleMapper.delete(new QueryWrapper<SysUserRole>().lambda().eq(SysUserRole::getUserId, user.getGuid()));
        if (CollectionUtils.isNotEmpty(roleIds)) {
            for (String roleId : roleIds) {
                SysUserRole userRole = new SysUserRole();
                userRole.setUserId(user.getGuid());
                userRole.setRoleId(roleId);
                sysUserRoleMapper.insert(userRole);
            }
        }

    }

    @Override
    public List<String> getRoleIds(String guid) {
        List<String> list = new ArrayList<>();
        List<SysUserRole> userRole = userRoleService.list(new QueryWrapper<SysUserRole>().lambda().eq(SysUserRole::getUserId, guid));
        if(userRole.size()>0){
            for (SysUserRole sysUserRole : userRole) {
                list.add(sysUserRole.getRoleId());
            }
        }
        return list;

    }
}
