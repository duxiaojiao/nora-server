-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: nora
-- ------------------------------------------------------
-- Server version	5.7.25

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `sys_menu`
--

DROP TABLE IF EXISTS `sys_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
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
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_menu`
--

LOCK TABLES `sys_menu` WRITE;
/*!40000 ALTER TABLE `sys_menu` DISABLE KEYS */;
INSERT INTO `sys_menu` VALUES ('341534427845856af76e2cf5bcf7657d','测试子菜单','01','6d896fd89169e7be94842f82ade01d85','/','','2',3,NULL,'2019-05-23 11:06:10','admin','2019-06-09 19:45:30'),('34b743cf685356d55d6794910a721ad9','用户管理','userMgt','fd4984af191710c80fdf882a0476b0bb','/users','team','2',5,NULL,'2019-05-20 09:46:06','admin','2019-06-08 22:53:25'),('35906ab768ceb044d543a47fa0b1b151','新增用户','addUser','34b743cf685356d55d6794910a721ad9',NULL,NULL,'3',0,'admin','2019-06-08 23:20:32','admin','2019-06-09 19:51:21'),('3889a0aff840dccf0764de4a0b786bf8','菜单管理','menuMgt','fd4984af191710c80fdf882a0476b0bb','/menus','profile','2',7,NULL,'2019-05-20 09:47:21','admin','2019-06-08 22:53:34'),('5abc93b231e6a2fb606525af21d9bfcf','首页','home','','/','home','1',1,NULL,'2019-05-24 21:37:10','admin','2019-06-08 22:52:28'),('6d896fd89169e7be94842f82ade01d85','测试菜单','testMenu','',NULL,'','1',2,NULL,'2019-05-20 09:47:43','admin','2019-06-08 22:53:01'),('bc5a66a87632b9505aa393661fcd3ccb','查询用户','queryUser','34b743cf685356d55d6794910a721ad9',NULL,NULL,'3',0,'admin','2019-06-09 20:52:07','admin','2019-06-09 20:52:07'),('c45b3fbc36911bbd9f8ea16812d66f06','编辑用户','editUser','34b743cf685356d55d6794910a721ad9',NULL,NULL,'3',3,'admin','2019-06-09 19:56:51','admin','2019-06-09 19:56:51'),('eaf10c4341bea181d8532cb4e6c31fa2','角色管理','roleMgt','fd4984af191710c80fdf882a0476b0bb','/roles','solution','2',6,NULL,'2019-05-20 09:46:45','admin','2019-06-08 22:53:30'),('fd4984af191710c80fdf882a0476b0bb','权限管理','authMgt','',NULL,'setting','1',4,NULL,'2019-05-20 09:45:19','admin','2019-06-08 22:53:20');
/*!40000 ALTER TABLE `sys_menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_menu_permission`
--

DROP TABLE IF EXISTS `sys_menu_permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
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
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_menu_permission`
--

LOCK TABLES `sys_menu_permission` WRITE;
/*!40000 ALTER TABLE `sys_menu_permission` DISABLE KEYS */;
INSERT INTO `sys_menu_permission` VALUES ('14bd6bd6332fd9f56c3b51b3ed8b7f29','bc5a66a87632b9505aa393661fcd3ccb','system:user:query','admin','2019-06-09 20:52:07','admin','2019-06-09 20:52:07'),('51d87826d82f4814a2808b1155825fc9','c45b3fbc36911bbd9f8ea16812d66f06','system:user:edit','admin','2019-06-09 19:56:51','admin','2019-06-09 19:56:51'),('d7c7a78261ad6566bb9d1815692a8ce2','35906ab768ceb044d543a47fa0b1b151','system:user:add','admin','2019-06-09 19:51:21','admin','2019-06-09 19:51:21');
/*!40000 ALTER TABLE `sys_menu_permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_role`
--

DROP TABLE IF EXISTS `sys_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
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
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_role`
--

LOCK TABLES `sys_role` WRITE;
/*!40000 ALTER TABLE `sys_role` DISABLE KEYS */;
INSERT INTO `sys_role` VALUES ('819b4ef66b6e6008da0ed8514b0a06e8','admin','管理员','普通管理员',NULL,'2019-05-20 10:03:58',NULL,'2019-05-20 10:03:58'),('abaf0652272a44628b4f42dd23e26b5b','user','用户','普通用户',NULL,'2019-05-23 11:16:34',NULL,'2019-05-23 11:16:34'),('f11473e3-4fd4-4965-90f2-3199bc9013ae','superAdmin','超级管理员','权限最大的角色',NULL,'2019-05-17 12:58:34',NULL,'2019-05-17 12:58:34');
/*!40000 ALTER TABLE `sys_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_role_menu`
--

DROP TABLE IF EXISTS `sys_role_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
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
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_role_menu`
--

LOCK TABLES `sys_role_menu` WRITE;
/*!40000 ALTER TABLE `sys_role_menu` DISABLE KEYS */;
INSERT INTO `sys_role_menu` VALUES ('0bf30478269eaf269a20c0a45ce71114','819b4ef66b6e6008da0ed8514b0a06e8','eaf10c4341bea181d8532cb4e6c31fa2',NULL,'2019-05-27 02:10:23',NULL,'2019-05-27 02:10:23'),('0ca2c87b5b30ab312faf7aab3566dee5','f11473e3-4fd4-4965-90f2-3199bc9013ae','eaf10c4341bea181d8532cb4e6c31fa2','admin','2019-06-09 20:52:20','admin','2019-06-09 20:52:20'),('212a9e1b3a585420b3c2be9f1a385066','abaf0652272a44628b4f42dd23e26b5b','6d896fd89169e7be94842f82ade01d85',NULL,'2019-05-26 14:21:56',NULL,'2019-05-26 14:21:56'),('289e63ab82af016b21c4c8b658608296','f11473e3-4fd4-4965-90f2-3199bc9013ae','34b743cf685356d55d6794910a721ad9','admin','2019-06-09 20:52:20','admin','2019-06-09 20:52:20'),('3ec5ff7b627e7d3b2680d8e96a2ab309','f11473e3-4fd4-4965-90f2-3199bc9013ae','6d896fd89169e7be94842f82ade01d85','admin','2019-06-09 20:52:20','admin','2019-06-09 20:52:20'),('5ce604e78cbc6c80f528cea9dd757dbb','abaf0652272a44628b4f42dd23e26b5b','341534427845856af76e2cf5bcf7657d',NULL,'2019-05-26 14:21:56',NULL,'2019-05-26 14:21:56'),('752c760e36c1320c00b90aa4cef97b1d','f11473e3-4fd4-4965-90f2-3199bc9013ae','35906ab768ceb044d543a47fa0b1b151','admin','2019-06-09 20:52:20','admin','2019-06-09 20:52:20'),('7fe989112e409ffa1bfee3f150ea1913','819b4ef66b6e6008da0ed8514b0a06e8','34b743cf685356d55d6794910a721ad9',NULL,'2019-05-27 02:10:23',NULL,'2019-05-27 02:10:23'),('8ff1f405984b7f8111588dd10c191442','f11473e3-4fd4-4965-90f2-3199bc9013ae','c45b3fbc36911bbd9f8ea16812d66f06','admin','2019-06-09 20:52:20','admin','2019-06-09 20:52:20'),('98f5b9c19d24c2ac2ad2fe566b888c80','f11473e3-4fd4-4965-90f2-3199bc9013ae','5abc93b231e6a2fb606525af21d9bfcf','admin','2019-06-09 20:52:20','admin','2019-06-09 20:52:20'),('a3520fef927616df90b08c6daadcb0bf','f11473e3-4fd4-4965-90f2-3199bc9013ae','3889a0aff840dccf0764de4a0b786bf8','admin','2019-06-09 20:52:20','admin','2019-06-09 20:52:20'),('ad0ada11e6955611da1d8ce36a775b7e','819b4ef66b6e6008da0ed8514b0a06e8','fd4984af191710c80fdf882a0476b0bb',NULL,'2019-05-27 02:10:23',NULL,'2019-05-27 02:10:23'),('c8e2063494533cc1438f45b46fa6ebe8','f11473e3-4fd4-4965-90f2-3199bc9013ae','bc5a66a87632b9505aa393661fcd3ccb','admin','2019-06-09 20:52:20','admin','2019-06-09 20:52:20'),('d0a4b5690a0404b4a57637667763cc5b','819b4ef66b6e6008da0ed8514b0a06e8','3889a0aff840dccf0764de4a0b786bf8',NULL,'2019-05-27 02:10:23',NULL,'2019-05-27 02:10:23'),('d3f8a736b2208d55162bb2c993f77002','f11473e3-4fd4-4965-90f2-3199bc9013ae','fd4984af191710c80fdf882a0476b0bb','admin','2019-06-09 20:52:20','admin','2019-06-09 20:52:20'),('e50f5eb7d544d5e375f231f6761e5b27','f11473e3-4fd4-4965-90f2-3199bc9013ae','341534427845856af76e2cf5bcf7657d','admin','2019-06-09 20:52:20','admin','2019-06-09 20:52:20');
/*!40000 ALTER TABLE `sys_role_menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_user`
--

DROP TABLE IF EXISTS `sys_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
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
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_user`
--

LOCK TABLES `sys_user` WRITE;
/*!40000 ALTER TABLE `sys_user` DISABLE KEYS */;
INSERT INTO `sys_user` VALUES ('1111111111','HR','S66156','赵四',NULL,'112344555','Z97YPbTvsRqL0RtYuF5loiNGBXFn7QwPXyYqMhCm++Y=','LCZLYiR0hXqbUCCdAtZWHQ==',NULL,NULL,'2019-05-16 17:39:40',NULL,'2019-05-16 17:39:40'),('94394a9ac0cc1a4bf8d9698d76eeca9d','admin','11','22','44','33','9gMC+SRrWCjjJExXL0xgKbTwdgVPONhR7E8quEm5Vdk=','BRhE3AU9vOgHuGhMJ8RPKA==',1,NULL,'2019-05-24 16:55:52',NULL,'2019-05-24 16:55:52'),('qwertyu','user','S66157','赵能','12345@qq.com','12456789432','VCgO2pONI/QR+cRsyjvTfcbaoD/545e7jIOLiWBjMPk=','qwxm8jiqD5vvNM010jZhhw==',1,NULL,'2019-05-16 18:20:48',NULL,'2019-05-16 18:20:48');
/*!40000 ALTER TABLE `sys_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_user_role`
--

DROP TABLE IF EXISTS `sys_user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
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
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_user_role`
--

LOCK TABLES `sys_user_role` WRITE;
/*!40000 ALTER TABLE `sys_user_role` DISABLE KEYS */;
INSERT INTO `sys_user_role` VALUES ('b2a9dd0b9c9f4e5a7fbf3a1b47f32075','94394a9ac0cc1a4bf8d9698d76eeca9d','f11473e3-4fd4-4965-90f2-3199bc9013ae',NULL,'2019-05-25 15:50:29',NULL,'2019-05-25 15:50:29'),('c07ad35ffd3026cd64b0e9b27d240504','qwertyu','abaf0652272a44628b4f42dd23e26b5b',NULL,'2019-05-26 14:21:37',NULL,'2019-05-26 14:21:37'),('f6257dacb05f75ffaa3170adf8e2bce7','1111111111','819b4ef66b6e6008da0ed8514b0a06e8',NULL,'2019-05-26 15:59:07',NULL,'2019-05-26 15:59:07');
/*!40000 ALTER TABLE `sys_user_role` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-06-09 20:58:22
