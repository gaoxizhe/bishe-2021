/*
 Navicat Premium Data Transfer

 Source Server         : 百度云
 Source Server Type    : MySQL
 Source Server Version : 50722
 Source Host           : 180.76.245.121:3307
 Source Schema         : personnel

 Target Server Type    : MySQL
 Target Server Version : 50722
 File Encoding         : 65001

 Date: 03/02/2022 22:58:04
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for dept_inf
-- ----------------------------
DROP TABLE IF EXISTS `dept_inf`;
CREATE TABLE `dept_inf`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `remark` varchar(300) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 31 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of dept_inf
-- ----------------------------
INSERT INTO `dept_inf` VALUES (1, '信息工程学院', '信息工程学院');
INSERT INTO `dept_inf` VALUES (2, '建筑学院', '建筑学院');
INSERT INTO `dept_inf` VALUES (3, '财务学院', '财务学院');
INSERT INTO `dept_inf` VALUES (5, '医学院', '医学院');
INSERT INTO `dept_inf` VALUES (6, '音乐学院', '音乐学院');
INSERT INTO `dept_inf` VALUES (7, '机电学院', '机电学院');

-- ----------------------------
-- Table structure for document_inf
-- ----------------------------
DROP TABLE IF EXISTS `document_inf`;
CREATE TABLE `document_inf`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `filename` varchar(300) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `remark` varchar(300) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_date` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `user_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of document_inf
-- ----------------------------
INSERT INTO `document_inf` VALUES (1, '2,2', '01.jpg', '11', NULL, NULL);
INSERT INTO `document_inf` VALUES (2, '2,2', '01.jpg', NULL, NULL, NULL);
INSERT INTO `document_inf` VALUES (3, '4', '2.jpg', '4', NULL, NULL);
INSERT INTO `document_inf` VALUES (4, '1', '1.jpg', '1', NULL, NULL);
INSERT INTO `document_inf` VALUES (5, '2', '1.jpg', '2', NULL, NULL);
INSERT INTO `document_inf` VALUES (6, '2', '1.jpg', '2', NULL, NULL);
INSERT INTO `document_inf` VALUES (7, '3', '5.jpg', '3', NULL, NULL);
INSERT INTO `document_inf` VALUES (8, '2', '5.jpg', '2', NULL, NULL);
INSERT INTO `document_inf` VALUES (9, '额外热污染', 'tqe.sql', '热舞任务二r', NULL, 1);

-- ----------------------------
-- Table structure for employee_inf
-- ----------------------------
DROP TABLE IF EXISTS `employee_inf`;
CREATE TABLE `employee_inf`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `dept_id` int(11) NULL DEFAULT NULL,
  `job_id` int(11) NULL DEFAULT NULL,
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `card_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `address` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `post_code` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `tel` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `phone` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `qq_num` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sex` int(11) NULL DEFAULT NULL,
  `party` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `birthday` char(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `race` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `education` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `speciality` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `hobby` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `remark` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_date` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `file_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of employee_inf
-- ----------------------------
INSERT INTO `employee_inf` VALUES (1, 1, 8, '123', '4328011988', '山西太原', '510000', '020-77777777', '13902001111', '367500661', '2514258873@qq.com', 0, '党员', '1980-01-01 00:00:00', '满', '本科', '美声', '唱歌', '四大天王', '2021-03-14 11:35:18', '123', '6e0800c1-cbb4-4d8d-851c-6733e42e6da6层次分析法参数.txt');
INSERT INTO `employee_inf` VALUES (2, 3, 1, '杰克', '22623', '43234', '42427424', '42242', '4247242', '424241', '2514258872@qq.com', 2, NULL, NULL, NULL, '1', NULL, NULL, NULL, '2021-03-14 11:35:18', NULL, NULL);
INSERT INTO `employee_inf` VALUES (3, 1, 2, 'bb', '432801197711251038', '广州', '510000', '020-99999999', '13907351111', '367500641', '367500641@qq.com', 1, '党员', '1977-11-25 00:00:00', '汉', '本科', '计算机', '爬山', '无', '2021-07-14 09:54:52', NULL, NULL);
INSERT INTO `employee_inf` VALUES (4, NULL, NULL, '1', '2', '7', NULL, NULL, '6', NULL, '5', 3, NULL, NULL, NULL, '4', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `employee_inf` VALUES (5, 1, 1, '1', '1', '1', NULL, NULL, '1', NULL, '1', 1, NULL, NULL, NULL, '1', NULL, NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for job_inf
-- ----------------------------
DROP TABLE IF EXISTS `job_inf`;
CREATE TABLE `job_inf`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `remark` varchar(300) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 12 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of job_inf
-- ----------------------------
INSERT INTO `job_inf` VALUES (1, '职员1', '职员');
INSERT INTO `job_inf` VALUES (2, 'Java开发工程师', 'Java开发工程师');
INSERT INTO `job_inf` VALUES (3, 'Java中级开发工程师', 'Java中级开发工程师');
INSERT INTO `job_inf` VALUES (4, 'Java高级开发工程师', 'Java高级开发工程师');
INSERT INTO `job_inf` VALUES (6, '架构师', '架构师');
INSERT INTO `job_inf` VALUES (7, '主管', '主管');
INSERT INTO `job_inf` VALUES (8, '经理', '经理');
INSERT INTO `job_inf` VALUES (9, '总经理', '总经理');
INSERT INTO `job_inf` VALUES (10, '2', '2');
INSERT INTO `job_inf` VALUES (11, '1', '2,3,4,5,6,7');

-- ----------------------------
-- Table structure for notice_inf
-- ----------------------------
DROP TABLE IF EXISTS `notice_inf`;
CREATE TABLE `notice_inf`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `content` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `create_date` char(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `user_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of notice_inf
-- ----------------------------
INSERT INTO `notice_inf` VALUES (1, '1112', '132323', NULL, NULL);
INSERT INTO `notice_inf` VALUES (2, '444', '4444', NULL, NULL);

-- ----------------------------
-- Table structure for user_inf
-- ----------------------------
DROP TABLE IF EXISTS `user_inf`;
CREATE TABLE `user_inf`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `loginname` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `status` int(11) NULL DEFAULT NULL,
  `createdate` char(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `username` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_inf
-- ----------------------------
INSERT INTO `user_inf` VALUES (1, 'admin', '123456', 2, '2016-03-12 09:34:28', '超级管理员1');
INSERT INTO `user_inf` VALUES (2, 'root', '123', NULL, NULL, 'root');
INSERT INTO `user_inf` VALUES (3, '哈哈', '123', NULL, NULL, '哈哈');
INSERT INTO `user_inf` VALUES (4, 'rain', '123456', NULL, NULL, 'rain');

SET FOREIGN_KEY_CHECKS = 1;
