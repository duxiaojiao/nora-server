package org.nora.modules.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.nora.modules.system.entity.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 系统用户表 服务类
 * </p>
 *
 * @author taylor
 * @since 2019-05-16
 */
public interface ISysUserService extends IService<SysUser> {

    /**
     * 新增用户
     * @param user
     * @return
     */
    void addUser(SysUser user) throws  RuntimeException;

    /**
     * 通过关键词搜索分页
     * @param search
     * @return
     */
    IPage<SysUser> queryUser(Integer pageNum, Integer pageSize, String search);

}
