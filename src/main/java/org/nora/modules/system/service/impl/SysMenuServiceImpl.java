package org.nora.modules.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang3.StringUtils;
import org.nora.modules.system.dto.MenuTreeDto;
import org.nora.modules.system.entity.SysMenu;
import org.nora.modules.system.entity.SysMenuPermission;
import org.nora.modules.system.mapper.SysMenuMapper;
import org.nora.modules.system.service.ISysMenuPermissionService;
import org.nora.modules.system.service.ISysMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 菜单表 服务实现类
 * </p>
 *
 * @author taylor
 * @since 2019-05-17
 */
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements ISysMenuService {

    @Autowired
    private SysMenuMapper sysMenuMapper;

    @Autowired
    private ISysMenuPermissionService sysMenuPermissionService;


    @Override
    @Transactional
    public void addMenu(SysMenu menu,List<String> permissions) throws RuntimeException {
        save(menu);
        List<SysMenuPermission> menuPermissionList = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(permissions)) {
            for (String permission : permissions) {
                SysMenuPermission menuPermission = new SysMenuPermission();
                menuPermission.setMenuId(menu.getGuid());
                menuPermission.setPermission(permission);
                menuPermissionList.add(menuPermission);
            }
            sysMenuPermissionService.saveBatch(menuPermissionList);
        }


    }

    @Override
    @Transactional
    public void editMenu(SysMenu menu, List<String> permissions) throws RuntimeException {
        updateById(menu);
        List<SysMenuPermission> menuPermissionList = new ArrayList<>();
//        if (CollectionUtils.isNotEmpty(permissions)) {
            LambdaQueryWrapper<SysMenuPermission> query = new QueryWrapper<SysMenuPermission>().lambda().eq(SysMenuPermission::getMenuId, menu.getGuid());
            sysMenuPermissionService.remove(query);
            for (String permission : permissions) {
                SysMenuPermission menuPermission = new SysMenuPermission();
                menuPermission.setMenuId(menu.getGuid());
                menuPermission.setPermission(permission);
                menuPermissionList.add(menuPermission);
            }
            sysMenuPermissionService.saveBatch(menuPermissionList);
//        }
    }

    @Override
    public IPage<SysMenu> queryMenu(Integer pageNum, Integer pageSize, String search) {
        QueryWrapper<SysMenu> wrapper = new QueryWrapper<>();
        if (StringUtils.isNoneEmpty(search)) {
            wrapper.like(true,"menu_name", "%"+search+"%");
        }
        Page<SysMenu> page = new Page<>(pageNum,pageSize);
        return sysMenuMapper.selectPage(page,wrapper);
    }

    @Override
    public List<MenuTreeDto> queryMenuTree() {

        return null;
    }

    @Override
    public List<SysMenu> queryUserMenuTree(String guid) {
        return sysMenuMapper.queryUserMenuTree(guid);
    }
}
