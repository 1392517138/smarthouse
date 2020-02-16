/*
 Navicat Premium Data Transfer

 Source Server         : mac
 Source Server Type    : MySQL
 Source Server Version : 50727
 Source Host           : localhost:3306
 Source Schema         : smarthouse

 Target Server Type    : MySQL
 Target Server Version : 50727
 File Encoding         : 65001

<<<<<<< HEAD
 Date: 16/02/2020 13:18:30
=======
 Date: 15/02/2020 11:47:32
>>>>>>> github/master
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
<<<<<<< HEAD
=======
-- Table structure for knowledge
-- ----------------------------
DROP TABLE IF EXISTS `knowledge`;
CREATE TABLE `knowledge` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content` varchar(255) NOT NULL,
  `answer` varchar(5) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
>>>>>>> github/master
-- Table structure for plan
-- ----------------------------
DROP TABLE IF EXISTS `plan`;
CREATE TABLE `plan` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(20) NOT NULL,
  `time` varchar(20) DEFAULT NULL,
  `thing` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
<<<<<<< HEAD
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
=======
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
>>>>>>> github/master

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
<<<<<<< HEAD
  `phone` varchar(20) NOT NULL,
=======
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `phone` varchar(20) DEFAULT NULL,
  `email` varchar(20) NOT NULL,
>>>>>>> github/master
  `pwd` varchar(20) NOT NULL,
  `nickname` varchar(20) DEFAULT NULL,
  `borth` varchar(30) NOT NULL,
  `sex` varchar(10) NOT NULL,
  `photo` varchar(40) DEFAULT NULL,
  `family` varchar(150) DEFAULT NULL,
<<<<<<< HEAD
  PRIMARY KEY (`phone`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
=======
  `qnum` int(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `info` (`email`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
>>>>>>> github/master

-- ----------------------------
-- Table structure for user_info
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info` (
<<<<<<< HEAD
  `phone` varchar(20) NOT NULL,
=======
  `email` varchar(20) NOT NULL,
>>>>>>> github/master
  `temperature` double(20,0) DEFAULT NULL,
  `moisture` double(20,0) DEFAULT NULL,
  `height` varchar(1000) DEFAULT NULL,
  `weight` varchar(1000) DEFAULT NULL,
  `b_Temperature` varchar(1000) DEFAULT NULL,
  `b_Sugar` varchar(1000) DEFAULT NULL,
  `b_Oxygen` varchar(1000) DEFAULT NULL,
  `h_Rate` varchar(1000) DEFAULT NULL,
  `n_Measure` varchar(1000) DEFAULT NULL,
<<<<<<< HEAD
  PRIMARY KEY (`phone`) USING BTREE
=======
  PRIMARY KEY (`email`)
>>>>>>> github/master
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

SET FOREIGN_KEY_CHECKS = 1;
