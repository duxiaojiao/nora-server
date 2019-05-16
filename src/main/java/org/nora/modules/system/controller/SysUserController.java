package org.nora.modules.system.controller;


import org.nora.common.constlant.CommonConstant;
import org.nora.common.responses.ResponseType;
import org.nora.modules.system.entity.SysUser;
import org.nora.modules.system.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

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



    @PostMapping(value = "saveOrUpdate")
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

}
