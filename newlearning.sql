/*
Navicat MySQL Data Transfer

Source Server         : lx
Source Server Version : 50022
Source Host           : localhost:3306
Source Database       : newlearning

Target Server Type    : MYSQL
Target Server Version : 50022
File Encoding         : 65001

Date: 2014-04-21 12:25:08
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `t_catalog`
-- ----------------------------
DROP TABLE IF EXISTS `t_catalog`;
CREATE TABLE `t_catalog` (
  `id` int(11) NOT NULL auto_increment,
  `code` varchar(255) default NULL,
  `name` varchar(255) default NULL,
  `remark` varchar(255) default NULL,
  `parent` bit(1) default NULL,
  `pid` int(11) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_catalog
-- ----------------------------
INSERT INTO `t_catalog` VALUES ('-2', '1001', '未分区', 'roor', '', '-1');
INSERT INTO `t_catalog` VALUES ('-1', '1', '全部大纲', 'root', '', '0');
INSERT INTO `t_catalog` VALUES ('13', '1002', 'java', '', '', '-1');
INSERT INTO `t_catalog` VALUES ('17', '1002001', 'java基础', '', '', '13');
INSERT INTO `t_catalog` VALUES ('19', '1002001001', 'javaIO', '', '', '17');

-- ----------------------------
-- Table structure for `t_depart`
-- ----------------------------
DROP TABLE IF EXISTS `t_depart`;
CREATE TABLE `t_depart` (
  `id` int(10) NOT NULL auto_increment,
  `number` char(10) NOT NULL,
  `name` char(10) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_depart
-- ----------------------------
INSERT INTO `t_depart` VALUES ('1', '01', 'java工程师');
INSERT INTO `t_depart` VALUES ('2', '02', '软件测试员');
INSERT INTO `t_depart` VALUES ('4', '123', '123');

-- ----------------------------
-- Table structure for `t_employee`
-- ----------------------------
DROP TABLE IF EXISTS `t_employee`;
CREATE TABLE `t_employee` (
  `id` int(11) NOT NULL auto_increment,
  `username` varchar(255) default NULL,
  `pwd` varchar(255) default NULL,
  `truename` varchar(255) default NULL,
  `sex` int(11) default NULL,
  `birthDay` datetime default NULL,
  `createDate` datetime default NULL,
  `remark` varchar(255) default NULL,
  `roles` varchar(255) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_employee
-- ----------------------------
INSERT INTO `t_employee` VALUES ('1', 'admin01', 'admin01', 'dfd', '1', '2014-03-29 00:00:00', '2014-03-29 11:05:38', 'dfd', '1');
INSERT INTO `t_employee` VALUES ('2', 'admin02', 'admin02', 'fd', '1', '2014-03-29 00:00:00', '2014-03-29 11:05:48', 'fd', '1');
INSERT INTO `t_employee` VALUES ('3', 'admin03', 'admin03', 'dsf', '2', '2014-03-13 00:00:00', '2014-03-29 11:06:01', 'dffd', '1');
INSERT INTO `t_employee` VALUES ('4', 'admin04', 'admin04', 'dfd', '1', '2014-03-21 00:00:00', '2014-03-29 11:06:13', 'dff', '1');
INSERT INTO `t_employee` VALUES ('5', 'admin05', 'admin05', 'dfd', '1', '2014-03-13 00:00:00', '2014-03-29 11:06:28', 'dff', '1');
INSERT INTO `t_employee` VALUES ('6', 'admin06', 'admin06', 'dfd', '2', '2014-03-28 00:00:00', '2014-03-29 11:06:38', 'dffd', '1');
INSERT INTO `t_employee` VALUES ('7', 'admin07', 'admin07', 'dfdf', '2', '2014-03-21 00:00:00', '2014-03-29 11:06:59', 'fdfdf', '1');
INSERT INTO `t_employee` VALUES ('8', 'admin08', 'admin08', 'dffas', '2', '2014-03-29 00:00:00', '2014-03-29 11:07:14', 'fdfd', '1');
INSERT INTO `t_employee` VALUES ('9', 'admin09', 'admin09', 'afdsaf', '2', '2014-03-06 00:00:00', '2014-03-29 11:07:25', 'fdfdf', '1');
INSERT INTO `t_employee` VALUES ('10', 'admin10', 'admin10', 'fdsf', '2', '2014-03-07 00:00:00', '2014-03-29 11:07:35', 'dffd', '1');
INSERT INTO `t_employee` VALUES ('11', 'admin11', 'admin11', 'fdsf', '2', '2014-02-27 00:00:00', '2014-03-29 11:07:53', 'fdf', '1');
INSERT INTO `t_employee` VALUES ('40', 'admin', '123456', 'sdfs', '2', '2014-03-06 00:00:00', '2014-03-29 11:01:20', '', '1');
INSERT INTO `t_employee` VALUES ('41', 'test01', 'test01', 'sdfs', '1', '2014-03-05 00:00:00', '2014-03-29 11:01:29', '', '');
INSERT INTO `t_employee` VALUES ('42', 'test02', 'test02', 'sdfs', '2', '2014-03-06 00:00:00', '2014-03-29 11:01:39', '', '');
INSERT INTO `t_employee` VALUES ('43', 'test03', 'test03', 'sdfs', '2', '2014-03-07 00:00:00', '2014-03-29 11:01:49', '', '');
INSERT INTO `t_employee` VALUES ('46', 'test04', 'test04', '66', '1', '2014-03-13 00:00:00', '2014-03-29 11:02:23', '', '');
INSERT INTO `t_employee` VALUES ('47', 'test05', 'test05', 'dfd', '1', '2014-03-05 00:00:00', '2014-03-29 16:17:52', '', '');
INSERT INTO `t_employee` VALUES ('48', 'test06', 'test06', 'fgfg', '1', '2014-03-05 00:00:00', '2014-03-29 16:18:03', '', '');
INSERT INTO `t_employee` VALUES ('49', 'test07', 'test07', 'dfd', '1', '2014-03-05 00:00:00', '2014-03-29 16:19:27', '', '');
INSERT INTO `t_employee` VALUES ('50', 'test08', 'test08', 'fgf', '1', '2014-03-06 00:00:00', '2014-03-29 16:19:39', '', '');

-- ----------------------------
-- Table structure for `t_lecture`
-- ----------------------------
DROP TABLE IF EXISTS `t_lecture`;
CREATE TABLE `t_lecture` (
  `id` int(255) NOT NULL auto_increment,
  `title` varchar(20) NOT NULL,
  `content` varchar(500) NOT NULL,
  `creater` varchar(10) NOT NULL,
  `createDate` varchar(30) default 'CURRENT_TIMESTAMP',
  `catalog` int(255) NOT NULL,
  PRIMARY KEY  (`id`),
  KEY `fk_catalog_id` (`catalog`),
  CONSTRAINT `t_lecture_ibfk_1` FOREIGN KEY (`catalog`) REFERENCES `t_catalog` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_lecture
-- ----------------------------
INSERT INTO `t_lecture` VALUES ('1', 'io test01', 'test<br />', 'test', '2014-03-22', '19');
INSERT INTO `t_lecture` VALUES ('2', 'test01', 'test<br />', 'test', '2014-03-22', '-2');
INSERT INTO `t_lecture` VALUES ('3', 'test02', '', 'test', '2014-03-22', '-2');
INSERT INTO `t_lecture` VALUES ('4', 'test03', 'test<br />', 'test', '2014-03-22', '-2');
INSERT INTO `t_lecture` VALUES ('5', 'test04', 'test<br />', 'test', '2014-03-22', '-2');
INSERT INTO `t_lecture` VALUES ('6', 'test05', 'test<br />', ' test', '2014-03-27', '-2');
INSERT INTO `t_lecture` VALUES ('7', 'io test02', 'test<br />', 'test', '2014-04-17', '19');

-- ----------------------------
-- Table structure for `t_node`
-- ----------------------------
DROP TABLE IF EXISTS `t_node`;
CREATE TABLE `t_node` (
  `id` int(11) NOT NULL auto_increment,
  `name` varchar(255) default NULL,
  `url` varchar(255) default NULL,
  `type` int(11) default NULL,
  `remark` varchar(255) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_node
-- ----------------------------
INSERT INTO `t_node` VALUES ('1', '人员管理', 'employee/list.jsp', '-2', '');
INSERT INTO `t_node` VALUES ('2', '角色管理', 'role/role.jsp', '-2', '');
INSERT INTO `t_node` VALUES ('3', '权限管理', 'node/node.jsp', '-2', '');
INSERT INTO `t_node` VALUES ('4', '岗位管理', 'depart/list.jsp', '-1', '');
INSERT INTO `t_node` VALUES ('5', '大纲管理', 'catalog/tree.jsp', '-1', '');
INSERT INTO `t_node` VALUES ('6', '讲义管理', 'lecture/lecture.jsp', '-1', '');

-- ----------------------------
-- Table structure for `t_role`
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role` (
  `id` int(11) NOT NULL auto_increment,
  `roleName` varchar(255) default NULL,
  `createDate` datetime default NULL,
  `remark` varchar(255) default NULL,
  `nodes` varchar(255) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_role
-- ----------------------------
INSERT INTO `t_role` VALUES ('1', '管理员', '2014-03-31 17:23:19', '', '-1,4,5,6,-2,1,2,3');
INSERT INTO `t_role` VALUES ('2', '总经理', '2014-03-31 17:35:05', '', '-1,6,-2,1');
