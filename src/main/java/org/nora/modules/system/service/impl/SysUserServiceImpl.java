package org.nora.modules.system.service.impl;

import org.nora.modules.system.entity.SysUser;
import org.nora.modules.system.mapper.SysUserMapper;
import org.nora.modules.system.service.ISysUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * <p>
 * 系统用户表 服务实现类
 * </p>
 *
 * @author taylor
 * @since 2019-05-16
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public void addUser(SysUser user) throws RuntimeException {

        user.setGuid("qwertyu");
        user.setLock(true); //有效
        user.setPassword("123456");
        try {
            sysUserMapper.insert(user);
        } catch (Exception e) {
            throw new RuntimeException("添加用户失败");
        }
    }
}
