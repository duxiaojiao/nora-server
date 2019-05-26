package org.nora.modules.system.entity;

import java.time.LocalDateTime;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 系统用户表
 * </p>
 *
 * @author taylor
 * @since 2019-05-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SysUser implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * guid
     */
    @TableId(type = IdType.UUID)
    private String guid;
    /**
     * 用户名
     */
    private String userName;

    /**
     * 员工工号
     */
    private String empCode;

    /**
     * 员工姓名
     */
    private String empName;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 手机
     */
    private String phone;

    /**
     * 密码
     */
    private String password;

    /**
     * 加密盐值
     */
    private String salt;

    /**
     * 是否锁定
     */
    private Boolean locked;

    /**
     * 创建者ID
     */
    private String createUser;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新者ID
     */
    private String updateUser;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;


}
