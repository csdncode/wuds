/*
 Navicat Premium Data Transfer

 Source Server         : wuds
 Source Server Type    : MySQL
 Source Server Version : 50610
 Source Host           : localhost:3306
 Source Schema         : wuds

 Target Server Type    : MySQL
 Target Server Version : 50610
 File Encoding         : 65001

 Date: 30/01/2019 22:44:38
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for message
-- ----------------------------
DROP TABLE IF EXISTS `message`;
CREATE TABLE `message`  (
  `msg` char(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`msg`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `username` char(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
  `password` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码的MD5值',
  `role` int(1) UNSIGNED ZEROFILL NOT NULL COMMENT '用户角色',
  `name` char(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户姓名',
  `grade` char(6) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户班级',
  PRIMARY KEY (`username`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for work
-- ----------------------------
DROP TABLE IF EXISTS `work`;
CREATE TABLE `work`  (
  `course` char(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '课程名',
  `expNo` char(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '实验编号',
  `grade` char(6) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '班级',
  `endTime` bigint(19) NOT NULL COMMENT '结束时间',
  `count` int(10) NOT NULL DEFAULT 0 COMMENT '已提交人数',
  PRIMARY KEY (`course`, `expNo`, `grade`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

SET FOREIGN_KEY_CHECKS = 1;
