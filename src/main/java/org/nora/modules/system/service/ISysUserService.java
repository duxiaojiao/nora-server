package org.nora.modules.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.nora.modules.system.entity.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

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

    /**
     * 删除用户
     * @param guid
     * @return
     */
    void deleteUser(String guid);

    /**
     * 删除用户
     * @param user
     * @return
     */
    void editUser(SysUser user, List<String> roleIds);

    /**
     * 根据用户ID获取角色
     *
     * @param guid
     * @return
     */
    List<String> getRoleIds(String guid);
}
