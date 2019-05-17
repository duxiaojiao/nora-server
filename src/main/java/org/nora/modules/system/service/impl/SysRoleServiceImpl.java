package org.nora.modules.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang3.StringUtils;
import org.nora.common.utils.GuidUtil;
import org.nora.modules.system.entity.SysRole;
import org.nora.modules.system.mapper.SysMenuMapper;
import org.nora.modules.system.mapper.SysRoleMapper;
import org.nora.modules.system.service.ISysRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author taylor
 * @since 2019-05-17
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements ISysRoleService {

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Override
    public void addRole(SysRole role) throws RuntimeException {
        role.setGuid(GuidUtil.GenerateGuid());
        sysRoleMapper.insert(role);
    }

    @Override
    public IPage<SysRole> queryRole(Integer pageNum, Integer pageSize, String search) {
        QueryWrapper<SysRole> wrapper = new QueryWrapper<>();
        if (StringUtils.isNoneEmpty(search)) {
            wrapper.like(true,"role_name", "%"+search+"%");
        }
        Page<SysRole> page = new Page<>(pageNum,pageSize);
        return sysRoleMapper.selectPage(page,wrapper);
    }
}
