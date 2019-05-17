package org.nora.modules.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang3.StringUtils;
import org.nora.common.utils.GuidUtil;
import org.nora.modules.system.entity.SysMenu;
import org.nora.modules.system.mapper.SysMenuMapper;
import org.nora.modules.system.service.ISysMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Override
    public void addMenu(SysMenu menu) throws RuntimeException {
        menu.setGuid(GuidUtil.GenerateGuid());
        sysMenuMapper.insert(menu);
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
}
