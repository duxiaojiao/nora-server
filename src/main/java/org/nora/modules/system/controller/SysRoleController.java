package org.nora.modules.system.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import org.nora.common.responses.ResponseType;
import org.nora.modules.system.entity.SysRole;
import org.nora.modules.system.service.ISysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 * 角色表 前端控制器
 * </p>
 *
 * @author taylor
 * @since 2019-05-17
 */
@RestController
@RequestMapping("/system/sysRole")
public class SysRoleController {

    @Autowired
    private ISysRoleService sysRoleService;

    @PostMapping(value = "addRole")
    public ResponseType<String> addRole(@RequestBody SysRole role) {
        ResponseType<String> response = new ResponseType<String>();
        sysRoleService.addRole(role);
        response.success();
        return response;
    }

    @PostMapping(value = "editRole")
    public ResponseType<String> editRole(@RequestBody SysRole role) {
        ResponseType<String> response = new ResponseType<String>();
        sysRoleService.updateById(role);
        response.success();
        return response;
    }

    @PostMapping(value = "deleteRole")
    public ResponseType<String> deleteRole(@RequestBody Map<String, Object> param) {
        ResponseType<String> response = new ResponseType<String>();
        String guid = (String) param.get("guid");
        sysRoleService.removeById(guid);
        response.success();
        return response;
    }

    @GetMapping(value = "queryRole")
    public ResponseType<IPage<SysRole>> queryUser(@RequestParam(value = "pageNum", defaultValue = "1") int pageNum, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize, @RequestParam(value = "search", defaultValue = "") String search){
        ResponseType<IPage<SysRole>> response = new ResponseType<>();
        IPage<SysRole> roleIPage = sysRoleService.queryRole(pageNum, pageSize, search);
        response.success(roleIPage);
        return response;
    }

}
