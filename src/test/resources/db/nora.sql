/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50721
Source Host           : localhost:3306
Source Database       : nora

Target Server Type    : MYSQL
Target Server Version : 50721
File Encoding         : 65001

Date: 2019-06-12 18:02:24
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
  `menu_type` varchar(10) DEFAULT '' COMMENT '菜单类型',
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
INSERT INTO `sys_menu` VALUES ('341534427845856af76e2cf5bcf7657d', '测试子菜单', '01', '6d896fd89169e7be94842f82ade01d85', '/', '', '2', '3', null, '2019-05-23 11:06:10', 'admin', '2019-06-09 19:45:30');
INSERT INTO `sys_menu` VALUES ('34b743cf685356d55d6794910a721ad9', '用户管理', 'userMgt', 'fd4984af191710c80fdf882a0476b0bb', '/users', 'team', '2', '5', null, '2019-05-20 09:46:06', 'admin', '2019-06-08 22:53:25');
INSERT INTO `sys_menu` VALUES ('35906ab768ceb044d543a47fa0b1b151', '新增用户', 'addUser', '34b743cf685356d55d6794910a721ad9', null, null, '3', '0', 'admin', '2019-06-08 23:20:32', 'admin', '2019-06-09 19:51:21');
INSERT INTO `sys_menu` VALUES ('3889a0aff840dccf0764de4a0b786bf8', '菜单管理', 'menuMgt', 'fd4984af191710c80fdf882a0476b0bb', '/menus', 'profile', '2', '7', null, '2019-05-20 09:47:21', 'admin', '2019-06-08 22:53:34');
INSERT INTO `sys_menu` VALUES ('4c7c0f64d448339a2a7611ee09a9b0d9', '删除用户', 'delUser', '34b743cf685356d55d6794910a721ad9', null, null, '3', '0', 'admin', '2019-06-12 17:33:19', 'admin', '2019-06-12 17:33:19');
INSERT INTO `sys_menu` VALUES ('5abc93b231e6a2fb606525af21d9bfcf', '首页', 'home', '', '/', 'home', '1', '1', null, '2019-05-24 21:37:10', 'admin', '2019-06-08 22:52:28');
INSERT INTO `sys_menu` VALUES ('6d896fd89169e7be94842f82ade01d85', '测试菜单', 'testMenu', '', null, '', '1', '2', null, '2019-05-20 09:47:43', 'admin', '2019-06-08 22:53:01');
INSERT INTO `sys_menu` VALUES ('bc5a66a87632b9505aa393661fcd3ccb', '查询用户', 'queryUser', '34b743cf685356d55d6794910a721ad9', null, null, '3', '0', 'admin', '2019-06-09 20:52:07', 'admin', '2019-06-09 20:52:07');
INSERT INTO `sys_menu` VALUES ('c45b3fbc36911bbd9f8ea16812d66f06', '编辑用户', 'editUser', '34b743cf685356d55d6794910a721ad9', null, null, '3', '3', 'admin', '2019-06-09 19:56:51', 'admin', '2019-06-09 19:56:51');
INSERT INTO `sys_menu` VALUES ('eaf10c4341bea181d8532cb4e6c31fa2', '角色管理', 'roleMgt', 'fd4984af191710c80fdf882a0476b0bb', '/roles', 'solution', '2', '6', null, '2019-05-20 09:46:45', 'admin', '2019-06-08 22:53:30');
INSERT INTO `sys_menu` VALUES ('f5e6ad0b71f738a53cf86beee4fd4ae6', '重置密码', 'resetPsd', '34b743cf685356d55d6794910a721ad9', null, null, '3', '0', 'admin', '2019-06-12 17:34:11', 'admin', '2019-06-12 17:34:11');
INSERT INTO `sys_menu` VALUES ('fd4984af191710c80fdf882a0476b0bb', '权限管理', 'authMgt', '', null, 'setting', '1', '4', null, '2019-05-20 09:45:19', 'admin', '2019-06-08 22:53:20');

-- ----------------------------
-- Table structure for sys_menu_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu_permission`;
CREATE TABLE `sys_menu_permission` (
  `guid` varchar(36) NOT NULL COMMENT 'guid',
  `menu_id` varchar(36) NOT NULL DEFAULT '' COMMENT '菜单ID',
  `permission` varchar(45) DEFAULT '' COMMENT '权限',
  `create_user` varchar(100) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_user` varchar(100) DEFAULT NULL COMMENT '更新人',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`guid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='菜单权限表';

-- ----------------------------
-- Records of sys_menu_permission
-- ----------------------------
INSERT INTO `sys_menu_permission` VALUES ('14bd6bd6332fd9f56c3b51b3ed8b7f29', 'bc5a66a87632b9505aa393661fcd3ccb', 'system:user:query', 'admin', '2019-06-09 20:52:07', 'admin', '2019-06-09 20:52:07');
INSERT INTO `sys_menu_permission` VALUES ('51d87826d82f4814a2808b1155825fc9', 'c45b3fbc36911bbd9f8ea16812d66f06', 'system:user:edit', 'admin', '2019-06-09 19:56:51', 'admin', '2019-06-09 19:56:51');
INSERT INTO `sys_menu_permission` VALUES ('6b7efb65e69c857688799ddcde85f637', '4c7c0f64d448339a2a7611ee09a9b0d9', 'system:user:delete', 'admin', '2019-06-12 17:33:19', 'admin', '2019-06-12 17:33:19');
INSERT INTO `sys_menu_permission` VALUES ('d7c7a78261ad6566bb9d1815692a8ce2', '35906ab768ceb044d543a47fa0b1b151', 'system:user:add', 'admin', '2019-06-09 19:51:21', 'admin', '2019-06-09 19:51:21');
INSERT INTO `sys_menu_permission` VALUES ('da47efe2bd2a410b635614a46eb8d85d', 'f5e6ad0b71f738a53cf86beee4fd4ae6', 'system:user:resetPwd', 'admin', '2019-06-12 17:34:11', 'admin', '2019-06-12 17:34:11');

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
INSERT INTO `sys_role_menu` VALUES ('13c726124e962bef57b634adeb8254c1', 'f11473e3-4fd4-4965-90f2-3199bc9013ae', '35906ab768ceb044d543a47fa0b1b151', 'admin', '2019-06-12 18:01:43', 'admin', '2019-06-12 18:01:43');
INSERT INTO `sys_role_menu` VALUES ('212a9e1b3a585420b3c2be9f1a385066', 'abaf0652272a44628b4f42dd23e26b5b', '6d896fd89169e7be94842f82ade01d85', null, '2019-05-26 14:21:56', null, '2019-05-26 14:21:56');
INSERT INTO `sys_role_menu` VALUES ('2ccbbe666fef150661e60b8fc3092666', 'f11473e3-4fd4-4965-90f2-3199bc9013ae', '4c7c0f64d448339a2a7611ee09a9b0d9', 'admin', '2019-06-12 18:01:43', 'admin', '2019-06-12 18:01:43');
INSERT INTO `sys_role_menu` VALUES ('3d7934ddcb7cf8341765fda4e7c3629c', 'f11473e3-4fd4-4965-90f2-3199bc9013ae', '5abc93b231e6a2fb606525af21d9bfcf', 'admin', '2019-06-12 18:01:43', 'admin', '2019-06-12 18:01:43');
INSERT INTO `sys_role_menu` VALUES ('467fdd7252833f47995b498b7f86b980', 'f11473e3-4fd4-4965-90f2-3199bc9013ae', '3889a0aff840dccf0764de4a0b786bf8', 'admin', '2019-06-12 18:01:43', 'admin', '2019-06-12 18:01:43');
INSERT INTO `sys_role_menu` VALUES ('4a756ac4e7c7af5a3f58f33c9a1e5d2c', 'f11473e3-4fd4-4965-90f2-3199bc9013ae', 'fd4984af191710c80fdf882a0476b0bb', 'admin', '2019-06-12 18:01:43', 'admin', '2019-06-12 18:01:43');
INSERT INTO `sys_role_menu` VALUES ('51aa346fd768a71ac2d3ae57cd440f21', 'f11473e3-4fd4-4965-90f2-3199bc9013ae', 'bc5a66a87632b9505aa393661fcd3ccb', 'admin', '2019-06-12 18:01:43', 'admin', '2019-06-12 18:01:43');
INSERT INTO `sys_role_menu` VALUES ('5ce604e78cbc6c80f528cea9dd757dbb', 'abaf0652272a44628b4f42dd23e26b5b', '341534427845856af76e2cf5bcf7657d', null, '2019-05-26 14:21:56', null, '2019-05-26 14:21:56');
INSERT INTO `sys_role_menu` VALUES ('7fe989112e409ffa1bfee3f150ea1913', '819b4ef66b6e6008da0ed8514b0a06e8', '34b743cf685356d55d6794910a721ad9', null, '2019-05-27 02:10:23', null, '2019-05-27 02:10:23');
INSERT INTO `sys_role_menu` VALUES ('a0d5a6de6faa6f726bf902b440d30aec', 'f11473e3-4fd4-4965-90f2-3199bc9013ae', 'eaf10c4341bea181d8532cb4e6c31fa2', 'admin', '2019-06-12 18:01:43', 'admin', '2019-06-12 18:01:43');
INSERT INTO `sys_role_menu` VALUES ('ad0ada11e6955611da1d8ce36a775b7e', '819b4ef66b6e6008da0ed8514b0a06e8', 'fd4984af191710c80fdf882a0476b0bb', null, '2019-05-27 02:10:23', null, '2019-05-27 02:10:23');
INSERT INTO `sys_role_menu` VALUES ('cf41a2ab631071badc2bdb8c0f283071', 'f11473e3-4fd4-4965-90f2-3199bc9013ae', 'f5e6ad0b71f738a53cf86beee4fd4ae6', 'admin', '2019-06-12 18:01:43', 'admin', '2019-06-12 18:01:43');
INSERT INTO `sys_role_menu` VALUES ('d0a4b5690a0404b4a57637667763cc5b', '819b4ef66b6e6008da0ed8514b0a06e8', '3889a0aff840dccf0764de4a0b786bf8', null, '2019-05-27 02:10:23', null, '2019-05-27 02:10:23');
INSERT INTO `sys_role_menu` VALUES ('db99891005a938daed50afe0ad25d71d', 'f11473e3-4fd4-4965-90f2-3199bc9013ae', '341534427845856af76e2cf5bcf7657d', 'admin', '2019-06-12 18:01:43', 'admin', '2019-06-12 18:01:43');
INSERT INTO `sys_role_menu` VALUES ('e28d9d4e4ed92eb5150ec5f340e69fe5', 'f11473e3-4fd4-4965-90f2-3199bc9013ae', '6d896fd89169e7be94842f82ade01d85', 'admin', '2019-06-12 18:01:43', 'admin', '2019-06-12 18:01:43');
INSERT INTO `sys_role_menu` VALUES ('e741b51b12d52addd968f312d3021ebf', 'f11473e3-4fd4-4965-90f2-3199bc9013ae', '34b743cf685356d55d6794910a721ad9', 'admin', '2019-06-12 18:01:43', 'admin', '2019-06-12 18:01:43');
INSERT INTO `sys_role_menu` VALUES ('fbaf0908bb539ccfd1f9b912389f8e50', 'f11473e3-4fd4-4965-90f2-3199bc9013ae', 'c45b3fbc36911bbd9f8ea16812d66f06', 'admin', '2019-06-12 18:01:43', 'admin', '2019-06-12 18:01:43');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `guid` varchar(36) NOT NULL COMMENT 'guid',
  `user_name` varchar(45) NOT NULL DEFAULT '' COMMENT '用户名',
  `emp_code` varchar(45) DEFAULT NULL COMMENT '员工工号',
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
INSERT INTO `sys_user_role` VALUES ('1885c6d16bca633895e6e4805d9e55f2', '23d4a6eacf87d0dba42a0ca1f30d5f0a', 'abaf0652272a44628b4f42dd23e26b5b', 'admin', '2019-06-12 17:56:54', 'admin', '2019-06-12 17:56:54');
INSERT INTO `sys_user_role` VALUES ('b2a9dd0b9c9f4e5a7fbf3a1b47f32075', '94394a9ac0cc1a4bf8d9698d76eeca9d', 'f11473e3-4fd4-4965-90f2-3199bc9013ae', null, '2019-05-25 15:50:29', null, '2019-05-25 15:50:29');
INSERT INTO `sys_user_role` VALUES ('c07ad35ffd3026cd64b0e9b27d240504', 'qwertyu', 'abaf0652272a44628b4f42dd23e26b5b', null, '2019-05-26 14:21:37', null, '2019-05-26 14:21:37');
INSERT INTO `sys_user_role` VALUES ('f6257dacb05f75ffaa3170adf8e2bce7', '1111111111', '819b4ef66b6e6008da0ed8514b0a06e8', null, '2019-05-26 15:59:07', null, '2019-05-26 15:59:07');
