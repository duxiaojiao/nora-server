package org.nora.modules.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.nora.modules.system.entity.SysRoleMenu;
import org.nora.modules.system.mapper.SysRoleMenuMapper;
import org.nora.modules.system.param.RoleMenuParam;
import org.nora.modules.system.service.ISysRoleMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 角色授权表 服务实现类
 * </p>
 *
 * @author taylor
 * @since 2019-05-20
 */
@Service
public class SysRoleMenuServiceImpl extends ServiceImpl<SysRoleMenuMapper, SysRoleMenu> implements ISysRoleMenuService {

    @Override
    public void saveRoleMenu(RoleMenuParam roleMenu) {
        LambdaQueryWrapper<SysRoleMenu> query = new QueryWrapper<SysRoleMenu>().lambda().eq(SysRoleMenu::getRoleId, roleMenu.getRoleId());
        this.remove(query);
        List<SysRoleMenu> list = new ArrayList<>();
        List<String> menuIds = roleMenu.getMenuIds();
        if(menuIds.size()>0){
            for (String menuId : menuIds) {
                SysRoleMenu sysRoleMenu = new SysRoleMenu();
                sysRoleMenu.setRoleId(roleMenu.getRoleId());
                sysRoleMenu.setMenuId(menuId);
                list.add(sysRoleMenu);

            }
        }
        this.saveBatch(list);

    }
}
