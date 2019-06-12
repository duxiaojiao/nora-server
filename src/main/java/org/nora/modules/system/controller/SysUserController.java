package org.nora.modules.system.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.nora.common.annotation.PermInfo;
import org.nora.common.constlant.CommonConstant;
import org.nora.common.responses.ResponseType;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.nora.modules.system.dto.UserDto;
import org.nora.modules.system.entity.SysUser;
import org.nora.modules.system.param.UserParam;
import org.nora.modules.system.service.ISysUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Map;


/**
 * <p>
 * 系统用户表 前端控制器
 * </p>
 *
 * @author taylor
 * @since 2019-05-16
 */
@Slf4j
@RestController
@RequestMapping("/system/sysUser")
public class SysUserController {

    @Autowired
    private ISysUserService sysUserService;

    @PermInfo("新增用户")
    @RequiresPermissions("system:user:add")
    @PostMapping(value = "addUser")
    public ResponseType<String> addUser(@RequestBody UserParam userParam) {
        ResponseType<String> response = new ResponseType<>();
        try {
            SysUser sysUser = new SysUser();
            BeanUtils.copyProperties(userParam,sysUser);
            sysUserService.addUser(sysUser,userParam.getRoleIds());
            response.setMsg(CommonConstant.Message.OPTION_SUCCESS);
        } catch (RuntimeException e) {
            response.failure("添加用户失败");
            log.error("添加用户",e.getMessage(), e.getStackTrace());
        }
        return response;
    }

    @PermInfo("编辑用户")
    @RequiresPermissions("system:user:edit")
    @PostMapping(value = "editUser")
    public ResponseType<String> editUser(@RequestBody UserParam userParam) {
        ResponseType<String> response = new ResponseType<String>();
        try {
            SysUser sysUser = new SysUser();
            BeanUtils.copyProperties(userParam,sysUser);
            sysUserService.editUser(sysUser,userParam.getRoleIds());
            response.setMsg(CommonConstant.Message.OPTION_SUCCESS);
        } catch (RuntimeException e) {
            response.setMsg(CommonConstant.Message.OPTION_FAILURE);
        }
        return response;
    }

    @PermInfo("查询用户")
    @RequiresPermissions("system:user:query")
    @GetMapping(value = "queryUser")
    public ResponseType<IPage<SysUser>> queryUser(@RequestParam(value = "pageNum", defaultValue = "1") int pageNum,@RequestParam(value = "pageSize", defaultValue = "10") int pageSize,@RequestParam(value = "search", defaultValue = "") String search){
        ResponseType<IPage<SysUser>> response = new ResponseType<>();
        IPage<SysUser> userIPage = sysUserService.queryUser(pageNum, pageSize, search);
        response.success(userIPage);
        return response;
    }
     //查询单个用户信息包括角色
    @GetMapping(value = "queryUserById")
    public ResponseType<UserDto> queryUserById(@RequestParam(value = "guid") String guid){
        ResponseType<UserDto> response = new ResponseType<>();
        SysUser user = sysUserService.getById(guid);
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(user,userDto);
        userDto.setRoleIds(sysUserService.getRoleIds(guid));
        response.success(userDto);
        return response;
    }

    @PermInfo("删除用户")
    @RequiresPermissions("system:user:delete")
    @PostMapping(value = "deleteUser")
    public ResponseType<String> deleteUser(@RequestBody Map<String, Object> param) {
        ResponseType<String> response = new ResponseType<String>();
        String guid = (String) param.get("guid");
        sysUserService.deleteUser(guid);
        response.success();
        return response;
    }

    @PermInfo("重置密码")
    @RequiresPermissions("system:user:resetPwd")
    @PostMapping(value = "resetPwd")
    public ResponseType<String> resetPwd(@RequestBody String request) {
        ResponseType<String> response = new ResponseType<String>();
        try {
            JSONObject json = JSON.parseObject(request);
            String guid= json.getString("guid");
            String pwd = json.getString("password");
            if (StringUtils.isBlank(pwd)) {
                response.failure("无法更新密码：密码为空");
            }
            //密码加密
            RandomNumberGenerator saltGen = new SecureRandomNumberGenerator();
            String salt = saltGen.nextBytes().toBase64();
            String hashedPwd = new Sha256Hash(pwd, salt, 1024).toBase64();

            SysUser updateData = new SysUser();
            updateData.setGuid(guid);
            updateData.setPassword(hashedPwd);
            updateData.setSalt(salt);
            sysUserService.updateById(updateData);
            response.success();
        } catch (RuntimeException e) {
            response.failure("修改密码失败");
        }
        return response;
    }

}
