package org.nora.modules.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.nora.modules.system.entity.SysRole;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 角色表 服务类
 * </p>
 *
 * @author taylor
 * @since 2019-05-17
 */
public interface ISysRoleService extends IService<SysRole> {

    /**
     * 新增角色
     * @param role
     * @return
     */
    void addRole(SysRole role) throws  RuntimeException;

    /**
     * 通过关键词搜索分页
     * @param search
     * @return
     */
    IPage<SysRole> queryRole(Integer pageNum, Integer pageSize, String search);

}
