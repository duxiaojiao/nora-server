package org.nora.common.autoFill;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.shiro.SecurityUtils;
import org.nora.modules.system.entity.SysUser;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class CommonMetaObjectHandler implements MetaObjectHandler {

    /**
     * 创建时间
     */
    private final String createTime = "createTime";
    /**
     * 修改时间
     */
    private final String updateTime = "updateTime";
    /**
     * 创建者ID
     */
    private final String createUser = "createUser";

    /**
     * 修改者ID
     */
    private final String updateUser = "updateUser";


    @Override
    public void insertFill(MetaObject metaObject) {
        setInsertFieldValByName(createTime, LocalDateTime.now(), metaObject);
        setInsertFieldValByName(createUser, this.getUserName(), metaObject);
        setInsertFieldValByName(updateTime, LocalDateTime.now(), metaObject);
        setInsertFieldValByName(updateUser, this.getUserName(), metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        setUpdateFieldValByName(updateTime, LocalDateTime.now(), metaObject);
        setUpdateFieldValByName(updateUser, this.getUserName(), metaObject);
    }

    /**
     * 获取当前用户账号
     */
    private String getUserName() {
        SysUser user = (SysUser) SecurityUtils.getSubject().getPrincipal();
        return user.getUserName();
    }

}
