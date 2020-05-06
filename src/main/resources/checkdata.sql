/*
 Navicat Premium Data Transfer

 Source Server         : 172.18.3.10
 Source Server Type    : MySQL
 Source Server Version : 80013
 Source Host           : localhost:3306
 Source Schema         : checkdata

 Target Server Type    : MySQL
 Target Server Version : 80013
 File Encoding         : 65001

 Date: 30/04/2020 11:16:42
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for checkdata
-- ----------------------------
DROP TABLE IF EXISTS `checkdata`;
CREATE TABLE `checkdata`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `temperature` double(255, 0) NULL DEFAULT NULL,
  `humidity` double(255, 0) NULL DEFAULT NULL,
  `ph` double(255, 0) NULL DEFAULT NULL,
  `bch` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `zc` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `result` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `type` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `xh` int(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 63 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
