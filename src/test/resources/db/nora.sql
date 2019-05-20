/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50721
Source Host           : localhost:3306
Source Database       : nora

Target Server Type    : MYSQL
Target Server Version : 50721
File Encoding         : 65001

Date: 2019-05-20 16:29:58
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `guid` varchar(36) NOT NULL COMMENT 'guid',
  `menu_name` varchar(45) NOT NULL COMMENT '菜单名称',
  `menu_code` varchar(45) NOT NULL COMMENT '菜单编码',
  `parent_id` varchar(45) DEFAULT '' COMMENT '父菜单',
  `icon` varchar(45) DEFAULT NULL COMMENT '图标',
  `create_user` varchar(100) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_user` varchar(100) DEFAULT NULL COMMENT '更新人',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`guid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='菜单表';

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('34b743cf685356d55d6794910a721ad9', '用户查询', 'userMgt', 'fd4984af191710c80fdf882a0476b0bb', null, null, '2019-05-20 09:46:06', null, '2019-05-20 09:46:06');
INSERT INTO `sys_menu` VALUES ('3889a0aff840dccf0764de4a0b786bf8', '菜单管理', 'menuMgt', 'fd4984af191710c80fdf882a0476b0bb', null, null, '2019-05-20 09:47:21', null, '2019-05-20 09:47:21');
INSERT INTO `sys_menu` VALUES ('6d896fd89169e7be94842f82ade01d85', '测试菜单', 'testMenu', '', null, null, '2019-05-20 09:47:43', null, '2019-05-20 09:47:43');
INSERT INTO `sys_menu` VALUES ('eaf10c4341bea181d8532cb4e6c31fa2', '角色管理', 'roleMgt', 'fd4984af191710c80fdf882a0476b0bb', null, null, '2019-05-20 09:46:45', null, '2019-05-20 09:46:45');
INSERT INTO `sys_menu` VALUES ('fd4984af191710c80fdf882a0476b0bb', '权限管理', 'authMgt', '', null, null, '2019-05-20 09:45:19', null, '2019-05-20 09:45:19');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `guid` varchar(36) NOT NULL COMMENT 'guid',
  `role_code` varchar(45) NOT NULL DEFAULT '' COMMENT '角色代码',
  `role_name` varchar(45) NOT NULL COMMENT '角色名称',
  `role_descr` varchar(100) DEFAULT NULL COMMENT '角色描述',
  `create_user` varchar(100) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_user` varchar(100) DEFAULT NULL COMMENT '更新人',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`guid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色表';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('819b4ef66b6e6008da0ed8514b0a06e8', 'admin', '管理员', '普通管理员', null, '2019-05-20 10:03:58', null, '2019-05-20 10:03:58');
INSERT INTO `sys_role` VALUES ('f11473e3-4fd4-4965-90f2-3199bc9013ae', 'superAdmin', '超级管理员', '权限最大的角色', null, '2019-05-17 12:58:34', null, '2019-05-17 12:58:34');

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `guid` varchar(36) NOT NULL COMMENT 'guid',
  `role_id` varchar(36) NOT NULL DEFAULT '' COMMENT '角色ID',
  `menu_id` varchar(36) NOT NULL DEFAULT '' COMMENT '菜单ID',
  PRIMARY KEY (`guid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色授权表';

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `guid` varchar(36) NOT NULL COMMENT 'guid',
  `emp_code` varchar(45) NOT NULL COMMENT '员工工号',
  `emp_name` varchar(50) NOT NULL COMMENT '用户名',
  `email` varchar(100) DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(11) DEFAULT NULL COMMENT '手机',
  `password` varchar(200) DEFAULT NULL COMMENT '密码',
  `salt` varchar(200) DEFAULT NULL COMMENT '加密盐值',
  `locked` tinyint(1) DEFAULT NULL COMMENT '是否锁定',
  `create_user` varchar(100) DEFAULT NULL COMMENT '创建者ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_user` varchar(100) DEFAULT NULL COMMENT '更新者ID',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`guid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='系统用户表';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1111111111', 'S66156', '赵四', null, null, null, null, null, null, '2019-05-16 17:39:40', null, '2019-05-16 17:39:40');
INSERT INTO `sys_user` VALUES ('221a9257-b510-4166-897b-1e8edef21eda', '11111', '1111', '11111', '11111', '123456', null, '1', null, '2019-05-17 15:50:37', null, '2019-05-17 15:50:37');
INSERT INTO `sys_user` VALUES ('qwertyu', 'S66157', '赵能', '12345@qq.com', '12456789432', '123456', null, '1', null, '2019-05-16 18:20:48', null, '2019-05-16 18:20:48');
