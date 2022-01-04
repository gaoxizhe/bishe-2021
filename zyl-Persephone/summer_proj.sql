/*
 Navicat Premium Data Transfer

 Source Server         : lunwen.sosozj.cn
 Source Server Type    : MySQL
 Source Server Version : 50722
 Source Host           : lunwen.sosozj.cn:3307
 Source Schema         : summer_proj

 Target Server Type    : MySQL
 Target Server Version : 50722
 File Encoding         : 65001

 Date: 04/01/2022 14:34:08
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin`  (
  `AdminID` int(10) NOT NULL AUTO_INCREMENT,
  `AdminName` char(8) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `AdminPwd` char(8) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`AdminID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES (1, 'admin', 'admin');
INSERT INTO `admin` VALUES (2, 'admin2', '456');

-- ----------------------------
-- Table structure for cartitem
-- ----------------------------
DROP TABLE IF EXISTS `cartitem`;
CREATE TABLE `cartitem`  (
  `UserID` int(11) NOT NULL,
  `DrinkID` int(11) NOT NULL,
  `DrinkSweet` char(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `DrinkTemp` char(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `DrinkSpec` char(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `Number` int(11) NOT NULL,
  PRIMARY KEY (`UserID`, `DrinkID`, `DrinkSweet`, `DrinkTemp`, `DrinkSpec`) USING BTREE,
  INDEX `DrinkID`(`DrinkID`) USING BTREE,
  CONSTRAINT `cartitem_ibfk_1` FOREIGN KEY (`UserID`) REFERENCES `user` (`UserID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `cartitem_ibfk_2` FOREIGN KEY (`DrinkID`) REFERENCES `drink` (`DrinkID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for drink
-- ----------------------------
DROP TABLE IF EXISTS `drink`;
CREATE TABLE `drink`  (
  `DrinkID` int(11) NOT NULL AUTO_INCREMENT,
  `DrinkName` char(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `DrinkPrice_Super` float(3, 1) NOT NULL,
  `DrinkPrice_Big` float(3, 1) NOT NULL,
  `DrinkPrice_Medium` float(3, 1) NOT NULL,
  `DrinkType` char(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `DrinkDesc` char(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `PicAddres` char(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`DrinkID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 23 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of drink
-- ----------------------------
INSERT INTO `drink` VALUES (1, '法式布蕾拿铁', 25.0, 20.0, 15.0, 'Coffee', '经典拿铁与焦糖风味融于一杯，炭烤香气自然醇厚，加入滑嫩布丁，入口层次丰富,是香浓悠长的法式风情。', 'drink/d1.jpg');
INSERT INTO `drink` VALUES (2, '标准美式咖啡', 15.0, 12.0, 10.0, 'Coffee', 'Espresso (意式浓缩)与水的黄金配比，带来浓郁的咖啡芬芳，成为脑海中挥之不去的绝妙体验。', 'drink/d2.jpg');
INSERT INTO `drink` VALUES (3, '卡布奇诺', 20.0, 15.0, 12.0, 'Coffee', '经典奶咖，奶泡与咖啡交融，绵密醇香，轻盈如雪。', 'drink/d3.jpg');
INSERT INTO `drink` VALUES (4, '焦糖玛奇朵', 20.0, 15.0, 12.0, 'Coffee', '焦糖风味奶咖，上层注入丰富奶泡，层次感分明。', 'drink/drink1.jpg');
INSERT INTO `drink` VALUES (5, '桃桃山雾乌龙茶', 25.0, 20.0, 15.0, 'FruitTea', '白桃和黄桃清澈的果香和悠长的乌马龙茶香相得益彰，将不同的风味融入茶中，茶香果韵四溢，碰撞出清爽沁甜口感。', 'drink/taotao.jpg');
INSERT INTO `drink` VALUES (6, '大红袍寒天牛乳茶', 20.0, 15.0, 12.0, 'Tea', '大红袍牛乳茶醇厚的香气佐以香滑Q嫩的黑糖口味寒天晶球，口感顺滑,回甘悠长，好喝不腻。\r\n', 'drink/d6.jpg');
INSERT INTO `drink` VALUES (7, '丝袜奶茶', 15.0, 12.0, 10.0, 'MilkTea', '现场拉出香浓红茶配合浓郁黑白淡奶，撞出顺滑口感，香浓美味。', 'drink/siwa.jpg');
INSERT INTO `drink` VALUES (8, '冰酿芋圆奶茶', 15.0, 12.0, 10.0, 'MilkTea', '儿时记忆中米酿香甜清爽，Q萌软糯的芋圆，徜徉在奶茶的海洋里，记得每一 天都要喝一 口冰酿芋圆，神仙级的享受哦!\r\n', 'drink/yuyuan.jpg');
INSERT INTO `drink` VALUES (9, '葡萄冻冻', 15.0, 12.0, 10.0, 'FruitTea', '手剥夏黑葡萄搭配清冽绿茶茶底，茉莉花香浸入果肉，鲜爽清甜。', 'drink/d9.jpg');
INSERT INTO `drink` VALUES (10, '超级水果茶', 20.0, 15.0, 12.0, 'FruitTea', '超大杯水果茶，融合足足七种新鲜水果，满杯果肉搭配清香茶底，每一口都暗藏惊喜。', 'drink/d10.jpg');
INSERT INTO `drink` VALUES (11, '西瓜啵啵', 15.0, 12.0, 10.0, 'FruitTea', '选用当季品质上乘的西瓜，用白毫绿茶作底，Q弹的水晶球混合细腻的西瓜沙冰，呈现出不一样的口感，风味十足。', 'drink/melon.jpg');
INSERT INTO `drink` VALUES (12, '清爽柠檬茶', 12.0, 10.0, 8.0, 'FruitTea', '入口微咸，回味带着香水柠檬的酸甜，香气十足清爽解腻！', 'drink/lemon.jpg');
INSERT INTO `drink` VALUES (13, '四季奶青', 12.0, 10.0, 8.0, 'MilkTea', '四季春茶搭配特选植脂末，经由黄金比例调制而成，香滑顺口。', 'drink/d13.jpg');
INSERT INTO `drink` VALUES (14, '波霸奶茶', 12.0, 10.0, 8.0, 'MilkTea', '奶茶搭配波霸，口感软Q。', 'drink/d14.jpg');
INSERT INTO `drink` VALUES (15, '焦糖奶茶', 12.0, 10.0, 8.0, 'MilkTea', '香纯奶茶加入焦糖糖浆，香而顺口。', 'drink/jiaotang.jpg');
INSERT INTO `drink` VALUES (16, '幽兰四季春', 11.0, 9.0, 7.0, 'Tea', '精选四季春茶，乌龙茶的韵味，又有绿茶的香气，口感香气清逸，滋味醇厚。', 'drink/sijichun.jpg');
INSERT INTO `drink` VALUES (17, '鲜芋牛奶', 20.0, 18.0, 15.0, 'Milk', '手捣芋泥融入醇香牛奶，软糯绵密的芋泥中还有嚼得到的芋头颗粒，入口芋香四溢，丰富浓郁的口感触动着味蕾。', 'drink/xianyu.jpg');
INSERT INTO `drink` VALUES (18, '果片大红袍', 15.0, 14.0, 12.0, 'FruitTea', '品味来自武夷山的岩骨花香，香气馥郁的大红袍和清新果片的美妙相遇，浅尝一口，唇齿留香。', 'drink/guopian.jpg');

-- ----------------------------
-- Table structure for drinkorder
-- ----------------------------
DROP TABLE IF EXISTS `drinkorder`;
CREATE TABLE `drinkorder`  (
  `OrderID` int(11) NOT NULL AUTO_INCREMENT,
  `UserID` int(11) NOT NULL,
  `AddrID` int(11) NULL DEFAULT NULL,
  `TotalPrice` float(8, 2) NOT NULL,
  `OrderTime` date NOT NULL,
  `PayState` int(11) NOT NULL DEFAULT 0,
  PRIMARY KEY (`OrderID`) USING BTREE,
  INDEX `UserID`(`UserID`) USING BTREE,
  INDEX `AddrID`(`AddrID`) USING BTREE,
  CONSTRAINT `drinkorder_ibfk_1` FOREIGN KEY (`UserID`) REFERENCES `user` (`UserID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `drinkorder_ibfk_2` FOREIGN KEY (`AddrID`) REFERENCES `useraddr` (`AddrID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 31 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of drinkorder
-- ----------------------------
INSERT INTO `drinkorder` VALUES (30, 22, 27, 25.00, '2022-01-04', 1);

-- ----------------------------
-- Table structure for notice
-- ----------------------------
DROP TABLE IF EXISTS `notice`;
CREATE TABLE `notice`  (
  `n_id` int(11) NOT NULL,
  `title` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `details` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `n_time` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`n_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of notice
-- ----------------------------
INSERT INTO `notice` VALUES (1, '618大促', '618全场6.18折，上不封顶', '2020/06/17');
INSERT INTO `notice` VALUES (2, '抗疫我们在行动', '为了鼓励大家足不出户喝奶茶，现在所有饮品均免配送费', '2020/06/01');
INSERT INTO `notice` VALUES (3, '端午节上新啦', '端午节和朋友来杯超级水果茶吧', '2020/06/25');

-- ----------------------------
-- Table structure for orderitem
-- ----------------------------
DROP TABLE IF EXISTS `orderitem`;
CREATE TABLE `orderitem`  (
  `OrderID` int(11) NOT NULL,
  `DrinkID` int(11) NOT NULL,
  `DrinkSweet` char(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `DrinkTemp` char(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `DrinkSpec` char(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `Number` int(11) NOT NULL,
  PRIMARY KEY (`OrderID`, `DrinkID`, `DrinkSweet`, `DrinkTemp`, `DrinkSpec`) USING BTREE,
  INDEX `DrinkID`(`DrinkID`) USING BTREE,
  CONSTRAINT `orderitem_ibfk_1` FOREIGN KEY (`OrderID`) REFERENCES `drinkorder` (`OrderID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `orderitem_ibfk_2` FOREIGN KEY (`DrinkID`) REFERENCES `drink` (`DrinkID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of orderitem
-- ----------------------------
INSERT INTO `orderitem` VALUES (30, 1, '全糖', '热', '超级杯', 1);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `UserID` int(11) NOT NULL AUTO_INCREMENT,
  `UserName` char(8) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `UserPwd` char(8) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `UserPhone` char(11) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`UserID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 23 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (7, 'zzzz', '1234', '14857954515');
INSERT INTO `user` VALUES (8, 'eeee', '1234', '13456897541');
INSERT INTO `user` VALUES (22, 'gxz', 'gxz', '12312312311');

-- ----------------------------
-- Table structure for useraddr
-- ----------------------------
DROP TABLE IF EXISTS `useraddr`;
CREATE TABLE `useraddr`  (
  `AddrID` int(11) NOT NULL AUTO_INCREMENT,
  `UserID` int(11) NOT NULL,
  `City` char(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `County` char(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `Street` char(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `HouseNum` char(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`AddrID`) USING BTREE,
  INDEX `UserID`(`UserID`) USING BTREE,
  CONSTRAINT `useraddr_ibfk_1` FOREIGN KEY (`UserID`) REFERENCES `user` (`UserID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 28 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of useraddr
-- ----------------------------
INSERT INTO `useraddr` VALUES (27, 22, '太原', '山西', '古交市', '11222');

SET FOREIGN_KEY_CHECKS = 1;
