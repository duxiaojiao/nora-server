package org.nora.modules.system.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import org.nora.common.responses.ResponseType;
import org.nora.common.utils.PermissionUtil;
import org.nora.modules.system.dto.MenuSelectTreeDto;
import org.nora.modules.system.dto.MenuTreeDto;
import org.nora.modules.system.dto.PermissionDto;
import org.nora.modules.system.entity.SysMenu;
import org.nora.modules.system.entity.SysMenuPermission;
import org.nora.modules.system.param.MenuParam;
import org.nora.modules.system.service.ISysMenuPermissionService;
import org.nora.modules.system.service.ISysMenuService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    @Autowired
    private ISysMenuPermissionService sysMenuPermissionService;

    @PostMapping(value = "addMenu")
    public ResponseType<String> addRole(@RequestBody MenuParam menuParam) {
        ResponseType<String> response = new ResponseType<String>();
        SysMenu menu = new SysMenu();
        BeanUtils.copyProperties(menuParam,menu);
        sysMenuService.addMenu(menu,menuParam.getPermissions());
        response.success();
        return response;
    }

    @PostMapping(value = "editMenu")
    public ResponseType<String> editMenu(@RequestBody MenuParam menuParam) {
        ResponseType<String> response = new ResponseType<String>();
        SysMenu menu = new SysMenu();
        BeanUtils.copyProperties(menuParam,menu);
        sysMenuService.editMenu(menu,menuParam.getPermissions());
        response.success();
        return response;
    }

    @GetMapping(value = "queryMenu")
    public ResponseType<IPage<SysMenu>> queryMenu(@RequestParam(value = "pageNum", defaultValue = "1") int pageNum, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize, @RequestParam(value = "search", defaultValue = "") String search){
        ResponseType<IPage<SysMenu>> response = new ResponseType<>();
        IPage<SysMenu> roleIPage = sysMenuService.queryMenu(pageNum, pageSize, search);
        response.success(roleIPage);
        return response;
    }

    @GetMapping(value = "queryMenuTree")
    public ResponseType<List<MenuTreeDto>> queryMenuTree(){
        ResponseType<List<MenuTreeDto>> response = new ResponseType<>();
        List<MenuTreeDto> menuTrees=new ArrayList<>();
        List<SysMenu> menus = sysMenuService.list(new QueryWrapper<SysMenu>().orderByAsc("sorter"));
        for (SysMenu menu : menus) {
            MenuTreeDto tree = new MenuTreeDto();
            BeanUtils.copyProperties(menu, tree);
            List<SysMenuPermission> permissions = sysMenuPermissionService.list(new QueryWrapper<SysMenuPermission>().eq("menu_id", menu.getGuid()));
            List<String> list = new ArrayList<>();
            if (CollectionUtils.isNotEmpty(permissions)) {
                for (SysMenuPermission permission : permissions) {
                    list.add(permission.getPermission());
                }
            }
            tree.setPermissions(list);
            menuTrees.add(tree);
        }
        List<MenuTreeDto> tree = this.getMenuTree(menuTrees, "");
        return response.success(tree);
    }

    @GetMapping(value = "queryMenuSelectTree")
    public ResponseType<List<MenuSelectTreeDto>> queryMenuSelectTree(){
        ResponseType<List<MenuSelectTreeDto>> response = new ResponseType<>();
        List<MenuSelectTreeDto> menuSelectTrees=new ArrayList<>();
        List<SysMenu> menus = sysMenuService.list(new QueryWrapper<SysMenu>().ne("menu_type","3"));
        for (SysMenu menu : menus) {
            MenuSelectTreeDto tree = new MenuSelectTreeDto();
            tree.setTitle(menu.getMenuName());
            tree.setValue(menu.getGuid());
            tree.setKey(menu.getGuid());
            tree.setParentId(menu.getParentId());
            menuSelectTrees.add(tree);
        }
        List<MenuSelectTreeDto> tree = this.getMenuSelectTree(menuSelectTrees, "");
        return response.success(tree);
    }

    @PostMapping(value = "deleteMenu")
    public ResponseType<String> deleteMenu(@RequestBody Map<String, Object> param) {
        ResponseType<String> response = new ResponseType<String>();
        String guid = (String) param.get("guid");
        sysMenuService.removeById(guid);
        response.success();
        return response;
    }

    @GetMapping(value = "queryMenuPermission")
    public ResponseType<List<PermissionDto>> queryMenuPermission(){
        ResponseType<List<PermissionDto>> response = new ResponseType<>();
        List<PermissionDto> permissionDtos = PermissionUtil.listPermission();
        return response.success(permissionDtos);
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

    //递归菜单形成菜单选择树
    private List<MenuSelectTreeDto> getMenuSelectTree(List<MenuSelectTreeDto> menuTrees,String parentId){
        //根据父节点寻找所有的子节点
        List<MenuSelectTreeDto> children = menuTrees.stream().filter(menu -> parentId.equals(menu.getParentId())).collect(Collectors.toList());
        List<MenuSelectTreeDto> successor = menuTrees.stream().filter(menu -> !parentId.equals(menu.getParentId())).collect(Collectors.toList());
        children.forEach(x ->
                {
                    List<MenuSelectTreeDto> tree = getMenuSelectTree(successor, x.getKey());
                    if (tree.size() > 0) {
                        List<MenuSelectTreeDto> temp = new ArrayList<>();
                        x.setChildren(temp);
                        tree.forEach(y -> x.getChildren().add(y));
                    }
                }
        );
        return children;
    }



}
