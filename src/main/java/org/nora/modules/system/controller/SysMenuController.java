package org.nora.modules.system.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import org.nora.common.responses.ResponseType;
import org.nora.modules.system.entity.SysMenu;
import org.nora.modules.system.service.ISysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 菜单表 前端控制器
 * </p>
 *
 * @author taylor
 * @since 2019-05-17
 */
@RestController
@RequestMapping("/system/sysMenu")
public class SysMenuController {

    @Autowired
    private ISysMenuService sysMenuService;

    @PostMapping(value = "addMenu")
    public ResponseType<String> addRole(@RequestBody SysMenu menu) {
        ResponseType<String> response = new ResponseType<String>();
        sysMenuService.addMenu(menu);
        response.success();
        return response;
    }

    @GetMapping(value = "queryMenu")
    public ResponseType<IPage<SysMenu>> queryUser(@RequestParam(value = "pageNum", defaultValue = "1") int pageNum, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize, @RequestParam(value = "search", defaultValue = "") String search){
        ResponseType<IPage<SysMenu>> response = new ResponseType<>();
        IPage<SysMenu> roleIPage = sysMenuService.queryMenu(pageNum, pageSize, search);
        response.success(roleIPage);
        return response;
    }



}
