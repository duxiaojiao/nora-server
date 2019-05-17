package org.nora.modules.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.nora.common.utils.GuidUtil;
import org.nora.modules.system.entity.SysUser;
import org.nora.modules.system.mapper.SysUserMapper;
import org.nora.modules.system.service.ISysUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
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

        user.setGuid(GuidUtil.GenerateGuid());
        user.setLocked(true); //有效
        user.setPassword("123456");
        try {
            sysUserMapper.insert(user);
        } catch (Exception e) {
            throw new RuntimeException("添加用户失败");
        }
    }

    @Override
    public IPage<SysUser> queryUser(Integer pageNum, Integer pageSize, String search) {
        QueryWrapper<SysUser> wrapper = new QueryWrapper<>();
        if (StringUtils.isNoneEmpty(search)) {
            wrapper.like(true,"emp_code", "%"+search+"%").or()
                    .like(true,"emp_name", "%"+search+"%").or()
                    .like(true,"email", "%"+search+"%").or()
                    .like(true,"phone", "%"+search+"%");
        }
        Page<SysUser> page = new Page<>(pageNum,pageSize);
        return sysUserMapper.selectPage(page,wrapper);
    }

    @Override
    public void deleteUser(String guid) {
        int i = sysUserMapper.deleteById(guid);
    }
}
