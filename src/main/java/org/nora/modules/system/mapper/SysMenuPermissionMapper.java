package org.nora.modules.system.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.nora.modules.system.entity.SysMenu;
import org.nora.modules.system.entity.SysMenuPermission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 菜单权限表 Mapper 接口
 * </p>
 *
 * @author taylor
 * @since 2019-06-09
 */
@Repository
@Mapper
public interface SysMenuPermissionMapper extends BaseMapper<SysMenuPermission> {

    List<String> getUserPermissions(@Param("guid") String guid);

}
