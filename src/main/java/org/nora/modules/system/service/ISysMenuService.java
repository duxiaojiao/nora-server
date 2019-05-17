package org.nora.modules.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.nora.modules.system.entity.SysMenu;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 菜单表 服务类
 * </p>
 *
 * @author taylor
 * @since 2019-05-17
 */
public interface ISysMenuService extends IService<SysMenu> {

    /**
     * 新增角色
     * @param menu
     * @return
     */
    void addMenu(SysMenu menu) throws  RuntimeException;

    /**
     * 通过关键词搜索分页
     * @param search
     * @return
     */
    IPage<SysMenu> queryMenu(Integer pageNum, Integer pageSize, String search);

}
