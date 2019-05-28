package org.nora.modules.system.entity;

import java.time.LocalDateTime;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 菜单表
 * </p>
 *
 * @author taylor
 * @since 2019-05-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SysMenu implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * guid
     */
    @TableId(type = IdType.UUID)
    private String guid;

    /**
     * 菜单名称
     */
    private String menuName;

    /**
     * 菜单编码
     */
    private String menuCode;

    /**
     * 父菜单
     */
    private String parentId;

    /**
     * 路由
     */
    private String router;

    /**
     * 图标
     */
    private String icon;
    /**
     * 菜单排序
     */
    private Integer sorter;

    /**
     * 创建人
     */
    @TableField(fill = FieldFill.INSERT)
    private String createUser;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 更新人
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String updateUser;

    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;


}
