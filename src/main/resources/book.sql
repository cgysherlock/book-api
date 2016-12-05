/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 50713
Source Host           : localhost:3306
Source Database       : book

Target Server Type    : MYSQL
Target Server Version : 50713
File Encoding         : 65001

Date: 2016-12-05 16:19:28
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for ssf_book
-- ----------------------------
DROP TABLE IF EXISTS `ssf_book`;
CREATE TABLE `ssf_book` (
  `id` bigint(20) NOT NULL,
  `create_date` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `modify_date` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `name` varchar(255) NOT NULL COMMENT '书名',
  `author` varchar(20) DEFAULT NULL COMMENT '作者',
  `Introduction` varchar(255) DEFAULT NULL COMMENT '简介',
  `price` decimal(10,2) DEFAULT NULL COMMENT '价格',
  PRIMARY KEY (`id`,`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ssf_book
-- ----------------------------

-- ----------------------------
-- Table structure for ssf_bookshelf
-- ----------------------------
DROP TABLE IF EXISTS `ssf_bookshelf`;
CREATE TABLE `ssf_bookshelf` (
  `id` bigint(20) NOT NULL,
  `create_date` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `modify_date` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ssf_bookshelf
-- ----------------------------

-- ----------------------------
-- Table structure for ssf_bookshelf_collection
-- ----------------------------
DROP TABLE IF EXISTS `ssf_bookshelf_collection`;
CREATE TABLE `ssf_bookshelf_collection` (
  `id` bigint(20) NOT NULL,
  `create_date` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `modify_date` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `description` varchar(255) DEFAULT NULL,
  `bookshelf_id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`,`bookshelf_id`,`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ssf_bookshelf_collection
-- ----------------------------

-- ----------------------------
-- Table structure for ssf_book_collection
-- ----------------------------
DROP TABLE IF EXISTS `ssf_book_collection`;
CREATE TABLE `ssf_book_collection` (
  `id` bigint(20) NOT NULL,
  `create_date` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `modify_date` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `user_id` bigint(20) NOT NULL,
  `book_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`,`user_id`,`book_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ssf_book_collection
-- ----------------------------

-- ----------------------------
-- Table structure for ssf_book_search
-- ----------------------------
DROP TABLE IF EXISTS `ssf_book_search`;
CREATE TABLE `ssf_book_search` (
  `id` bigint(20) NOT NULL,
  `create_date` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `modify_date` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `user_id` bigint(20) NOT NULL,
  `book_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`,`user_id`,`book_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ssf_book_search
-- ----------------------------

-- ----------------------------
-- Table structure for ssf_book_type
-- ----------------------------
DROP TABLE IF EXISTS `ssf_book_type`;
CREATE TABLE `ssf_book_type` (
  `id` int(11) NOT NULL,
  `create_date` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `modify_date` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`,`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ssf_book_type
-- ----------------------------

-- ----------------------------
-- Table structure for ssf_book_type_search
-- ----------------------------
DROP TABLE IF EXISTS `ssf_book_type_search`;
CREATE TABLE `ssf_book_type_search` (
  `id` bigint(20) NOT NULL,
  `create_date` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `modify_date` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `user_id` bigint(20) NOT NULL,
  `book_type_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`,`user_id`,`book_type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ssf_book_type_search
-- ----------------------------

-- ----------------------------
-- Table structure for ssf_comment
-- ----------------------------
DROP TABLE IF EXISTS `ssf_comment`;
CREATE TABLE `ssf_comment` (
  `id` bigint(20) NOT NULL,
  `create_date` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `modify_date` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `content` varchar(255) NOT NULL,
  PRIMARY KEY (`id`,`content`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ssf_comment
-- ----------------------------

-- ----------------------------
-- Table structure for ssf_concern
-- ----------------------------
DROP TABLE IF EXISTS `ssf_concern`;
CREATE TABLE `ssf_concern` (
  `id` bigint(20) NOT NULL,
  `create_date` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `modify_date` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `concerner_id` bigint(20) NOT NULL,
  `concerned_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`,`concerner_id`,`concerned_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ssf_concern
-- ----------------------------

-- ----------------------------
-- Table structure for ssf_user_comment
-- ----------------------------
DROP TABLE IF EXISTS `ssf_user_comment`;
CREATE TABLE `ssf_user_comment` (
  `id` bigint(20) NOT NULL,
  `create_date` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `modify_date` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `like` tinyint(2) DEFAULT NULL,
  `user_id` bigint(20) NOT NULL,
  `comment_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`,`user_id`,`comment_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ssf_user_comment
-- ----------------------------

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` bigint(20) NOT NULL,
  `create_date` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `modify_date` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `photo` varchar(255) DEFAULT NULL,
  `tel` varchar(255) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`,`username`,`password`,`tel`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', '2016-12-04 15:30:01', '2016-12-04 15:30:01', '123', '123', null, null, '17826808005', null);
SET FOREIGN_KEY_CHECKS=1;
