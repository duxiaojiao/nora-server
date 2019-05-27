package org.nora.modules.system.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.nora.common.responses.ResponseType;
import org.nora.modules.system.dto.MenuSelectTreeDto;
import org.nora.modules.system.dto.MenuTreeDto;
import org.nora.modules.system.entity.SysMenu;
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

    @PostMapping(value = "addMenu")
    public ResponseType<String> addRole(@RequestBody SysMenu menu) {
        ResponseType<String> response = new ResponseType<String>();
        sysMenuService.addMenu(menu);
        response.success();
        return response;
    }

    @PostMapping(value = "editMenu")
    public ResponseType<String> editMenu(@RequestBody SysMenu menu) {
        ResponseType<String> response = new ResponseType<String>();
        sysMenuService.updateById(menu);
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
//            tree.setKey(menu.getGuid());
            BeanUtils.copyProperties(menu,tree);
//            tree.setGuid(menu.getGuid());
//            tree.setMenuName(menu.getMenuName());
//            tree.setMenuCode(menu.getMenuCode());
//            tree.setParentId(menu.getParentId());
//            tree.setRouter(menu.getRouter());
//            tree.setIcon(menu.getIcon());
            menuTrees.add(tree);
        }
        List<MenuTreeDto> tree = this.getMenuTree(menuTrees, "");
        return response.success(tree);
    }

    @GetMapping(value = "queryMenuSelectTree")
    public ResponseType<List<MenuSelectTreeDto>> queryMenuSelectTree(){
        ResponseType<List<MenuSelectTreeDto>> response = new ResponseType<>();
        List<MenuSelectTreeDto> menuSelectTrees=new ArrayList<>();
        List<SysMenu> menus = sysMenuService.list(new LambdaQueryWrapper<>());
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
