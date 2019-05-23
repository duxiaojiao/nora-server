package org.nora.modules.system.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import org.nora.common.constlant.CommonConstant;
import org.nora.common.responses.ResponseType;
import org.nora.modules.system.dto.UserDto;
import org.nora.modules.system.entity.SysUser;
import org.nora.modules.system.param.UserParam;
import org.nora.modules.system.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


/**
 * <p>
 * 系统用户表 前端控制器
 * </p>
 *
 * @author taylor
 * @since 2019-05-16
 */
@RestController
@RequestMapping("/system/sysUser")
public class SysUserController {

    @Autowired
    private ISysUserService sysUserService;


    @PostMapping(value = "addUser")
    public ResponseType<String> addUser(@RequestBody SysUser user) {
        ResponseType<String> response = new ResponseType<String>();
        try {
            sysUserService.addUser(user);
            response.setMsg(CommonConstant.Message.OPTION_SUCCESS);
        } catch (RuntimeException e) {
            response.setMsg(CommonConstant.Message.OPTION_FAILURE);
        }
        return response;
    }

    @PostMapping(value = "editUser")
    public ResponseType<String> editUser(@RequestBody UserParam userParam) {
        ResponseType<String> response = new ResponseType<String>();
        try {
            SysUser sysUser = new SysUser();
            sysUser.setGuid(userParam.getGuid());
            sysUser.setEmpCode(userParam.getEmpCode());
            sysUser.setEmpName(userParam.getEmpName());
            sysUser.setEmail(userParam.getEmail());
            sysUser.setPhone(userParam.getPhone());
            sysUser.setLocked(userParam.getLocked());
            sysUserService.editUser(sysUser,userParam.getRoleIds());
            response.setMsg(CommonConstant.Message.OPTION_SUCCESS);
        } catch (RuntimeException e) {
            response.setMsg(CommonConstant.Message.OPTION_FAILURE);
        }
        return response;
    }

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
        userDto.setGuid(user.getGuid());
        userDto.setGuid(user.getGuid());
        userDto.setEmpCode(user.getEmpCode());
        userDto.setEmpName(user.getEmpName());
        userDto.setEmail(user.getEmail());
        userDto.setPhone(user.getPhone());
        userDto.setLocked(user.getLocked());
        userDto.setRoleIds(sysUserService.getRoleIds(guid));
        response.success(userDto);
        return response;
    }

    @PostMapping(value = "deleteUser")
    public ResponseType<String> deleteUser(@RequestBody Map<String, Object> param) {
        ResponseType<String> response = new ResponseType<String>();
        String guid = (String) param.get("guid");
        sysUserService.deleteUser(guid);
        response.success();
        return response;
    }

}
