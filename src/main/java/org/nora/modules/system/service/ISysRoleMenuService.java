package org.nora.modules.system.service;

import org.nora.modules.system.entity.SysRoleMenu;
import com.baomidou.mybatisplus.extension.service.IService;
import org.nora.modules.system.param.RoleMenuParam;

/**
 * <p>
 * 角色授权表 服务类
 * </p>
 *
 * @author taylor
 * @since 2019-05-20
 */
public interface ISysRoleMenuService extends IService<SysRoleMenu> {

    void saveRoleMenu(RoleMenuParam roleMenu );

}
