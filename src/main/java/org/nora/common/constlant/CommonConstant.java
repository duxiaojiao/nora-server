package org.nora.common.constlant;

public interface CommonConstant {

    /**
     * 响应客户端结果
     */
     class ServerApp {
        /** 成功 **/
        public final static String SERVER_RET_SUSSCESS = "0";
        /** 失败 **/
        public final static String SERVER_RET_FAILURE = "1";
    }

    class Message {

        /************************
         * 系统管理或通用消息
         *************************/
        /** 操作成功 **/
        public final static String OPTION_SUCCESS = "操作成功";
        /** 操作失败 **/
        public final static String OPTION_FAILURE = "操作失败";
        /** 操作失败，没有对应权限 **/
        public final static String OPTION_FAILURE_NO_PERMISSION = "操作失败,没有权限";
        /** 代码或名称已存在 **/
        public final static String ADD_UPDATE_PROP_EXIST = "数据库已存在同名属性";
        /** 角色代码已存在 **/
        public final static String ROLE_CODENAME_EXIST = "角色代码或名称已存在";
        /** 角色已分配权限 **/
        public final static String ROLE_EXIST_PERMISSION = "角色已分配权限,请先解除角色权限关系";
    }
}
