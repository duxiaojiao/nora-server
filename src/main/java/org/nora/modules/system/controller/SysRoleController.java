package org.nora.modules.system.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.nora.common.responses.ResponseType;
import org.nora.modules.system.dto.RoleSelectMenuTreeDto;
import org.nora.modules.system.entity.SysMenu;
import org.nora.modules.system.entity.SysRole;
import org.nora.modules.system.entity.SysRoleMenu;
import org.nora.modules.system.param.RoleMenuParam;
import org.nora.modules.system.service.ISysMenuService;
import org.nora.modules.system.service.ISysRoleMenuService;
import org.nora.modules.system.service.ISysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    @Autowired
    private ISysMenuService sysMenuService;

    @Autowired
    private ISysRoleMenuService sysRoleMenuService;

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

    @PostMapping(value = "saveRoleMenu")
    public ResponseType<String> saveRoleMenu(@RequestBody RoleMenuParam roleMenu) {
        ResponseType<String> response = new ResponseType<>();
        sysRoleMenuService.saveRoleMenu(roleMenu);
        response.success();
        return response;
    }

    @GetMapping(value = "queryRoleMenu")
    public ResponseType<List<String>> queryRoleMenu(@RequestParam(value = "roleId") String roleId) {
        ResponseType<List<String>> response = new ResponseType<>();
        List<SysRoleMenu> list = sysRoleMenuService.list(new QueryWrapper<SysRoleMenu>().lambda().eq(SysRoleMenu::getRoleId, roleId));
        List<String> menuIds = list.stream().map(SysRoleMenu::getMenuId).collect(Collectors.toList());
        response.success(menuIds);
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

    @GetMapping(value = "queryRoleSelectMenuTree")
    public ResponseType<List<RoleSelectMenuTreeDto>> queryRoleSelectMenuTree(){
        ResponseType<List<RoleSelectMenuTreeDto>> response = new ResponseType<>();
        List<RoleSelectMenuTreeDto> RoleMenuenuSelectTrees=new ArrayList<>();
        List<SysMenu> menus = sysMenuService.list(new QueryWrapper<SysMenu>().orderByAsc("sorter"));
        for (SysMenu menu : menus) {
            RoleSelectMenuTreeDto tree = new RoleSelectMenuTreeDto();
            tree.setTitle(menu.getMenuName());
            tree.setValue(menu.getGuid());
            tree.setKey(menu.getGuid());
            tree.setParentId(menu.getParentId());
            RoleMenuenuSelectTrees.add(tree);
        }
        List<RoleSelectMenuTreeDto> tree = this.getRoleMenuSelectTree(RoleMenuenuSelectTrees, "");
        return response.success(tree);
    }

    //递归菜单形成菜单选择树
    private List<RoleSelectMenuTreeDto> getRoleMenuSelectTree(List<RoleSelectMenuTreeDto> roleMenuTrees,String parentId){
        //根据父节点寻找所有的子节点
        List<RoleSelectMenuTreeDto> children = roleMenuTrees.stream().filter(menu -> parentId.equals(menu.getParentId())).collect(Collectors.toList());
        List<RoleSelectMenuTreeDto> successor = roleMenuTrees.stream().filter(menu -> !parentId.equals(menu.getParentId())).collect(Collectors.toList());
        children.forEach(x ->
                {
                    List<RoleSelectMenuTreeDto> tree = getRoleMenuSelectTree(successor, x.getKey());
                    if (tree.size() > 0) {
                        List<RoleSelectMenuTreeDto> temp = new ArrayList<>();
                        x.setChildren(temp);
                        tree.forEach(y -> x.getChildren().add(y));
                    }
                }
        );
        return children;
    }

}
