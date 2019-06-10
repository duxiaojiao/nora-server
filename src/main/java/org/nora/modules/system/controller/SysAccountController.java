package org.nora.modules.system.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.subject.Subject;
import org.nora.common.responses.ResponseType;
import org.nora.common.utils.GuidUtil;
import org.nora.modules.system.dto.MenuTreeDto;
import org.nora.modules.system.dto.UserDetailDto;
import org.nora.modules.system.dto.UserDto;
import org.nora.modules.system.entity.SysMenu;
import org.nora.modules.system.entity.SysUser;
import org.nora.modules.system.service.ISysMenuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * <p>
 * 系统用户表 前端控制器
 * </p>
 *
 * @author taylor
 * @since 2019-05-25
 */
@RestController
@RequestMapping("/system/account")
public class SysAccountController {

    private static final Logger log = LoggerFactory.getLogger(SysAccountController.class);

    @Autowired
    private ISysMenuService sysMenuService;

    @GetMapping("/401")
    public ResponseType<String> page401() {
        ResponseType<String> response = new ResponseType<>();
        response.failure("501","请登录");
        return response;
    }

    @GetMapping("/index")
    public ResponseType<String> pageIndex() {
        ResponseType<String> response = new ResponseType<>();
        response.success();
        return response;
    }

    @GetMapping("/403")
    public ResponseType<String> page403() {
        ResponseType<String> response = new ResponseType<>();
        response.failure("506","无操作权限");
        return response;
    }


    @PostMapping("/login")
    public ResponseType<UserDto> login(@RequestBody String body){
        ResponseType<UserDto> response = new ResponseType<>();
        JSONObject json = JSON.parseObject(body);
        String uname = json.getString("userName");
        String pwd = json.getString("password");

        if (StringUtils.isEmpty(uname)){
            response.failure("用户名不能为空");
            return response;
        }
        if (StringUtils.isEmpty(pwd)){
            response.failure("密码不能为空");
            return response;
        }

        Subject currentUser = SecurityUtils.getSubject();
        try {
            //登录
            currentUser.login( new UsernamePasswordToken(uname, pwd) );
            //从session取出用户信息
            SysUser user = (SysUser) currentUser.getPrincipal();
            if (user==null) throw new AuthenticationException();
            log.info("user login: {}, sessionId: {}",user.getUserName(),currentUser.getSession().getId());
            //返回登录用户的信息给前台，含用户的所有角色和权限
//            return Json.succ(oper)
//                    .data("token", UUID.randomUUID().toString())
//                    .data("uid",user.getUid())
//                    .data("nick",user.getNick())
//                    .data("roles",user.getRoles())
//                    .data("perms",user.getPerms());
            UserDto userDto = new UserDto();
            userDto.setToken(GuidUtil.GenerateGuid());
            BeanUtils.copyProperties(user,userDto);
            response.success(userDto);
            return response;
        } catch ( UnknownAccountException uae ) {
            response.failure("用户帐号或密码不正确");
            return response;

        } catch ( IncorrectCredentialsException ice ) {
            response.failure("用户帐号或密码不正确");
            return response;

        } catch ( LockedAccountException lae ) {
            response.failure("用户帐号被锁定不可用");
            return response;

        } catch ( AuthenticationException ae ) {
            response.failure("登录失败："+ae.getMessage());
            return response;
        }
    }

    @PostMapping("/logout")
    public ResponseType<String> logout(){
        ResponseType<String> response = new ResponseType<>();
        SecurityUtils.getSubject().logout();
        response.setMsg("退出成功");
        response.success();
        return response;
    }

    @GetMapping(value = "queryUserMenuTree")
    public ResponseType<List<MenuTreeDto>> queryUserMenuTree(){
        ResponseType<List<MenuTreeDto>> response = new ResponseType<>();
        List<MenuTreeDto> menuTrees=new ArrayList<>();
        SysUser user = (SysUser) SecurityUtils.getSubject().getPrincipal();
        List<SysMenu> menus = sysMenuService.queryUserMenuTree(user.getGuid());
        for (SysMenu menu : menus) {
            MenuTreeDto tree = new MenuTreeDto();
            BeanUtils.copyProperties(menu,tree);
            menuTrees.add(tree);
        }
        List<MenuTreeDto> tree = this.getMenuTree(menuTrees, "");
        return response.success(tree);
    }

    @GetMapping(value = "queryUserDetail")
    public ResponseType<UserDetailDto> queryUserDetail(){
        ResponseType<UserDetailDto> response = new ResponseType<>();
        UserDetailDto userDetail = new UserDetailDto();
        SysUser user = (SysUser) SecurityUtils.getSubject().getPrincipal();
        BeanUtils.copyProperties(user,userDetail);
        return response.success(userDetail);
    }

    //递归菜单形成菜单树
    private List<MenuTreeDto> getMenuTree(List<MenuTreeDto> menuTrees,String parentId){
        //根据父节点寻找所有的子节点
        List<MenuTreeDto> children = menuTrees.stream().filter(menu -> parentId.equals(menu.getParentId())).collect(Collectors.toList());
        List<MenuTreeDto> successor = menuTrees.stream().filter(menu -> !parentId.equals(menu.getParentId())).collect(Collectors.toList());
        children.forEach(x ->
                {
                    List<MenuTreeDto> tree = getMenuTree(successor, x.getGuid());
                    if (tree.size() > 0) {
                        List<MenuTreeDto> temp = new ArrayList<>();
                        x.setChildren(temp);
                        tree.forEach(y -> x.getChildren().add(y));
                    }
                }
        );
        return children;
    }



}
