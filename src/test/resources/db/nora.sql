/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50721
Source Host           : localhost:3306
Source Database       : nora

Target Server Type    : MYSQL
Target Server Version : 50721
File Encoding         : 65001

Date: 2019-05-27 15:43:23
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
  `router` varchar(45) DEFAULT NULL COMMENT '路由',
  `icon` varchar(45) DEFAULT NULL COMMENT '图标',
  `sorter` int(3) DEFAULT '0' COMMENT '菜单排序',
  `create_user` varchar(100) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_user` varchar(100) DEFAULT NULL COMMENT '更新人',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`guid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='菜单表';

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('341534427845856af76e2cf5bcf7657d', '测试子菜单', '01', '6d896fd89169e7be94842f82ade01d85', '/', '', '3', null, '2019-05-23 11:06:10', null, '2019-05-23 11:06:10');
INSERT INTO `sys_menu` VALUES ('34b743cf685356d55d6794910a721ad9', '用户管理', 'userMgt', 'fd4984af191710c80fdf882a0476b0bb', '/users', 'team', '5', null, '2019-05-20 09:46:06', null, '2019-05-20 09:46:06');
INSERT INTO `sys_menu` VALUES ('3889a0aff840dccf0764de4a0b786bf8', '菜单管理', 'menuMgt', 'fd4984af191710c80fdf882a0476b0bb', '/menus', 'profile', '7', null, '2019-05-20 09:47:21', null, '2019-05-20 09:47:21');
INSERT INTO `sys_menu` VALUES ('5abc93b231e6a2fb606525af21d9bfcf', '首页', 'home', '', '/', 'home', '1', null, '2019-05-24 21:37:10', null, '2019-05-24 21:37:10');
INSERT INTO `sys_menu` VALUES ('6d896fd89169e7be94842f82ade01d85', '测试菜单', 'testMenu', '', null, '', '2', null, '2019-05-20 09:47:43', null, '2019-05-20 09:47:43');
INSERT INTO `sys_menu` VALUES ('eaf10c4341bea181d8532cb4e6c31fa2', '角色管理', 'roleMgt', 'fd4984af191710c80fdf882a0476b0bb', '/roles', 'solution', '6', null, '2019-05-20 09:46:45', null, '2019-05-20 09:46:45');
INSERT INTO `sys_menu` VALUES ('fd4984af191710c80fdf882a0476b0bb', '权限管理', 'authMgt', '', null, 'setting', '4', null, '2019-05-20 09:45:19', null, '2019-05-20 09:45:19');

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
INSERT INTO `sys_role` VALUES ('abaf0652272a44628b4f42dd23e26b5b', 'user', '用户', '普通用户', null, '2019-05-23 11:16:34', null, '2019-05-23 11:16:34');
INSERT INTO `sys_role` VALUES ('f11473e3-4fd4-4965-90f2-3199bc9013ae', 'superAdmin', '超级管理员', '权限最大的角色', null, '2019-05-17 12:58:34', null, '2019-05-17 12:58:34');

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `guid` varchar(36) NOT NULL COMMENT 'guid',
  `role_id` varchar(36) NOT NULL DEFAULT '' COMMENT '角色ID',
  `menu_id` varchar(36) NOT NULL DEFAULT '' COMMENT '菜单ID',
  `create_user` varchar(100) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_user` varchar(100) DEFAULT NULL COMMENT '更新人',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`guid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色授权表';

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES ('0bf30478269eaf269a20c0a45ce71114', '819b4ef66b6e6008da0ed8514b0a06e8', 'eaf10c4341bea181d8532cb4e6c31fa2', null, '2019-05-27 02:10:23', null, '2019-05-27 02:10:23');
INSERT INTO `sys_role_menu` VALUES ('212a9e1b3a585420b3c2be9f1a385066', 'abaf0652272a44628b4f42dd23e26b5b', '6d896fd89169e7be94842f82ade01d85', null, '2019-05-26 14:21:56', null, '2019-05-26 14:21:56');
INSERT INTO `sys_role_menu` VALUES ('3dc997df447bbdcb3d711b7e18a77dd7', 'f11473e3-4fd4-4965-90f2-3199bc9013ae', '6d896fd89169e7be94842f82ade01d85', null, '2019-05-26 20:04:08', null, '2019-05-26 20:04:08');
INSERT INTO `sys_role_menu` VALUES ('5bd79832544a1d9ef5f0de0a8c0bde66', 'f11473e3-4fd4-4965-90f2-3199bc9013ae', '341534427845856af76e2cf5bcf7657d', null, '2019-05-26 20:04:08', null, '2019-05-26 20:04:08');
INSERT INTO `sys_role_menu` VALUES ('5ce604e78cbc6c80f528cea9dd757dbb', 'abaf0652272a44628b4f42dd23e26b5b', '341534427845856af76e2cf5bcf7657d', null, '2019-05-26 14:21:56', null, '2019-05-26 14:21:56');
INSERT INTO `sys_role_menu` VALUES ('7fe989112e409ffa1bfee3f150ea1913', '819b4ef66b6e6008da0ed8514b0a06e8', '34b743cf685356d55d6794910a721ad9', null, '2019-05-27 02:10:23', null, '2019-05-27 02:10:23');
INSERT INTO `sys_role_menu` VALUES ('a679054711058a89eb261b56b4f88817', 'f11473e3-4fd4-4965-90f2-3199bc9013ae', 'fd4984af191710c80fdf882a0476b0bb', null, '2019-05-26 20:04:08', null, '2019-05-26 20:04:08');
INSERT INTO `sys_role_menu` VALUES ('ad0ada11e6955611da1d8ce36a775b7e', '819b4ef66b6e6008da0ed8514b0a06e8', 'fd4984af191710c80fdf882a0476b0bb', null, '2019-05-27 02:10:23', null, '2019-05-27 02:10:23');
INSERT INTO `sys_role_menu` VALUES ('c57921b4b346b7757136a7c112faa6c9', 'f11473e3-4fd4-4965-90f2-3199bc9013ae', '3889a0aff840dccf0764de4a0b786bf8', null, '2019-05-26 20:04:08', null, '2019-05-26 20:04:08');
INSERT INTO `sys_role_menu` VALUES ('d0a4b5690a0404b4a57637667763cc5b', '819b4ef66b6e6008da0ed8514b0a06e8', '3889a0aff840dccf0764de4a0b786bf8', null, '2019-05-27 02:10:23', null, '2019-05-27 02:10:23');
INSERT INTO `sys_role_menu` VALUES ('db5e6482dfc2c85c42ed1d4b2e4e088f', 'f11473e3-4fd4-4965-90f2-3199bc9013ae', '34b743cf685356d55d6794910a721ad9', null, '2019-05-26 20:04:08', null, '2019-05-26 20:04:08');
INSERT INTO `sys_role_menu` VALUES ('f13faecf471bf64d4f785ae9a25acb17', 'f11473e3-4fd4-4965-90f2-3199bc9013ae', 'eaf10c4341bea181d8532cb4e6c31fa2', null, '2019-05-26 20:04:08', null, '2019-05-26 20:04:08');
INSERT INTO `sys_role_menu` VALUES ('f23af17a7089f0d3f399ad84d63c9e5d', 'f11473e3-4fd4-4965-90f2-3199bc9013ae', '5abc93b231e6a2fb606525af21d9bfcf', null, '2019-05-26 20:04:08', null, '2019-05-26 20:04:08');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `guid` varchar(36) NOT NULL COMMENT 'guid',
  `user_name` varchar(45) NOT NULL DEFAULT '' COMMENT '用户名',
  `emp_code` varchar(45) NOT NULL COMMENT '员工工号',
  `emp_name` varchar(50) NOT NULL COMMENT '员工姓名',
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
INSERT INTO `sys_user` VALUES ('1111111111', 'HR', 'S66156', '赵四', null, '112344555', 'Z97YPbTvsRqL0RtYuF5loiNGBXFn7QwPXyYqMhCm++Y=', 'LCZLYiR0hXqbUCCdAtZWHQ==', null, null, '2019-05-16 17:39:40', null, '2019-05-16 17:39:40');
INSERT INTO `sys_user` VALUES ('94394a9ac0cc1a4bf8d9698d76eeca9d', 'admin', '11', '22', '44', '33', '9gMC+SRrWCjjJExXL0xgKbTwdgVPONhR7E8quEm5Vdk=', 'BRhE3AU9vOgHuGhMJ8RPKA==', '1', null, '2019-05-24 16:55:52', null, '2019-05-24 16:55:52');
INSERT INTO `sys_user` VALUES ('qwertyu', 'user', 'S66157', '赵能', '12345@qq.com', '12456789432', 'VCgO2pONI/QR+cRsyjvTfcbaoD/545e7jIOLiWBjMPk=', 'qwxm8jiqD5vvNM010jZhhw==', '1', null, '2019-05-16 18:20:48', null, '2019-05-16 18:20:48');

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `guid` varchar(36) NOT NULL COMMENT 'guid',
  `user_id` varchar(36) NOT NULL COMMENT '用户ID',
  `role_id` varchar(36) NOT NULL COMMENT '角色ID',
  `create_user` varchar(100) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_user` varchar(100) DEFAULT NULL COMMENT '更新人',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`guid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户角色表';

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('b2a9dd0b9c9f4e5a7fbf3a1b47f32075', '94394a9ac0cc1a4bf8d9698d76eeca9d', 'f11473e3-4fd4-4965-90f2-3199bc9013ae', null, '2019-05-25 15:50:29', null, '2019-05-25 15:50:29');
INSERT INTO `sys_user_role` VALUES ('c07ad35ffd3026cd64b0e9b27d240504', 'qwertyu', 'abaf0652272a44628b4f42dd23e26b5b', null, '2019-05-26 14:21:37', null, '2019-05-26 14:21:37');
INSERT INTO `sys_user_role` VALUES ('f6257dacb05f75ffaa3170adf8e2bce7', '1111111111', '819b4ef66b6e6008da0ed8514b0a06e8', null, '2019-05-26 15:59:07', null, '2019-05-26 15:59:07');
