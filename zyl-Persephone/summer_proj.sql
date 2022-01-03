/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 50730
Source Host           : localhost:3306
Source Database       : summer_proj

Target Server Type    : MYSQL
Target Server Version : 50730
File Encoding         : 65001

Date: 2020-07-03 01:13:03
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `admin`
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `AdminID` int(10) NOT NULL AUTO_INCREMENT,
  `AdminName` char(8) NOT NULL,
  `AdminPwd` char(8) NOT NULL,
  PRIMARY KEY (`AdminID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES ('1', 'admin1', '123');
INSERT INTO `admin` VALUES ('2', 'admin2', '456');

-- ----------------------------
-- Table structure for `cartitem`
-- ----------------------------
DROP TABLE IF EXISTS `cartitem`;
CREATE TABLE `cartitem` (
  `UserID` int(11) NOT NULL,
  `DrinkID` int(11) NOT NULL,
  `DrinkSweet` char(10) NOT NULL,
  `DrinkTemp` char(10) NOT NULL,
  `DrinkSpec` char(10) NOT NULL,
  `Number` int(11) NOT NULL,
  PRIMARY KEY (`UserID`,`DrinkID`,`DrinkSweet`,`DrinkTemp`,`DrinkSpec`),
  KEY `DrinkID` (`DrinkID`),
  CONSTRAINT `cartitem_ibfk_1` FOREIGN KEY (`UserID`) REFERENCES `user` (`UserID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `cartitem_ibfk_2` FOREIGN KEY (`DrinkID`) REFERENCES `drink` (`DrinkID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cartitem
-- ----------------------------

-- ----------------------------
-- Table structure for `drink`
-- ----------------------------
DROP TABLE IF EXISTS `drink`;
CREATE TABLE `drink` (
  `DrinkID` int(11) NOT NULL AUTO_INCREMENT,
  `DrinkName` char(10) NOT NULL,
  `DrinkPrice_Super` float(3,1) NOT NULL,
  `DrinkPrice_Big` float(3,1) NOT NULL,
  `DrinkPrice_Medium` float(3,1) NOT NULL,
  `DrinkType` char(10) NOT NULL,
  `DrinkDesc` char(100) NOT NULL,
  `PicAddres` char(255) NOT NULL,
  PRIMARY KEY (`DrinkID`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of drink
-- ----------------------------
INSERT INTO `drink` VALUES ('1', '法式布蕾拿铁', '25.0', '20.0', '15.0', 'Coffee', '经典拿铁与焦糖风味融于一杯，炭烤香气自然醇厚，加入滑嫩布丁，入口层次丰富,是香浓悠长的法式风情。', 'drink/d1.jpg');
INSERT INTO `drink` VALUES ('2', '标准美式咖啡', '15.0', '12.0', '10.0', 'Coffee', 'Espresso (意式浓缩)与水的黄金配比，带来浓郁的咖啡芬芳，成为脑海中挥之不去的绝妙体验。', 'drink/d2.jpg');
INSERT INTO `drink` VALUES ('3', '卡布奇诺', '20.0', '15.0', '12.0', 'Coffee', '经典奶咖，奶泡与咖啡交融，绵密醇香，轻盈如雪。', 'drink/d3.jpg');
INSERT INTO `drink` VALUES ('4', '焦糖玛奇朵', '20.0', '15.0', '12.0', 'Coffee', '焦糖风味奶咖，上层注入丰富奶泡，层次感分明。', 'drink/drink1.jpg');
INSERT INTO `drink` VALUES ('5', '桃桃山雾乌龙茶', '25.0', '20.0', '15.0', 'FruitTea', '白桃和黄桃清澈的果香和悠长的乌马龙茶香相得益彰，将不同的风味融入茶中，茶香果韵四溢，碰撞出清爽沁甜口感。', 'drink/taotao.jpg');
INSERT INTO `drink` VALUES ('6', '大红袍寒天牛乳茶', '20.0', '15.0', '12.0', 'Tea', '大红袍牛乳茶醇厚的香气佐以香滑Q嫩的黑糖口味寒天晶球，口感顺滑,回甘悠长，好喝不腻。\r\n', 'drink/d6.jpg');
INSERT INTO `drink` VALUES ('7', '丝袜奶茶', '15.0', '12.0', '10.0', 'MilkTea', '现场拉出香浓红茶配合浓郁黑白淡奶，撞出顺滑口感，香浓美味。', 'drink/siwa.jpg');
INSERT INTO `drink` VALUES ('8', '冰酿芋圆奶茶', '15.0', '12.0', '10.0', 'MilkTea', '儿时记忆中米酿香甜清爽，Q萌软糯的芋圆，徜徉在奶茶的海洋里，记得每一 天都要喝一 口冰酿芋圆，神仙级的享受哦!\r\n', 'drink/yuyuan.jpg');
INSERT INTO `drink` VALUES ('9', '葡萄冻冻', '15.0', '12.0', '10.0', 'FruitTea', '手剥夏黑葡萄搭配清冽绿茶茶底，茉莉花香浸入果肉，鲜爽清甜。', 'drink/d9.jpg');
INSERT INTO `drink` VALUES ('10', '超级水果茶', '20.0', '15.0', '12.0', 'FruitTea', '超大杯水果茶，融合足足七种新鲜水果，满杯果肉搭配清香茶底，每一口都暗藏惊喜。', 'drink/d10.jpg');
INSERT INTO `drink` VALUES ('11', '西瓜啵啵', '15.0', '12.0', '10.0', 'FruitTea', '选用当季品质上乘的西瓜，用白毫绿茶作底，Q弹的水晶球混合细腻的西瓜沙冰，呈现出不一样的口感，风味十足。', 'drink/melon.jpg');
INSERT INTO `drink` VALUES ('12', '清爽柠檬茶', '12.0', '10.0', '8.0', 'FruitTea', '入口微咸，回味带着香水柠檬的酸甜，香气十足清爽解腻！', 'drink/lemon.jpg');
INSERT INTO `drink` VALUES ('13', '四季奶青', '12.0', '10.0', '8.0', 'MilkTea', '四季春茶搭配特选植脂末，经由黄金比例调制而成，香滑顺口。', 'drink/d13.jpg');
INSERT INTO `drink` VALUES ('14', '波霸奶茶', '12.0', '10.0', '8.0', 'MilkTea', '奶茶搭配波霸，口感软Q。', 'drink/d14.jpg');
INSERT INTO `drink` VALUES ('15', '焦糖奶茶', '12.0', '10.0', '8.0', 'MilkTea', '香纯奶茶加入焦糖糖浆，香而顺口。', 'drink/jiaotang.jpg');
INSERT INTO `drink` VALUES ('16', '幽兰四季春', '11.0', '9.0', '7.0', 'Tea', '精选四季春茶，乌龙茶的韵味，又有绿茶的香气，口感香气清逸，滋味醇厚。', 'drink/sijichun.jpg');
INSERT INTO `drink` VALUES ('17', '鲜芋牛奶', '20.0', '18.0', '15.0', 'Milk', '手捣芋泥融入醇香牛奶，软糯绵密的芋泥中还有嚼得到的芋头颗粒，入口芋香四溢，丰富浓郁的口感触动着味蕾。', 'drink/xianyu.jpg');
INSERT INTO `drink` VALUES ('18', '果片大红袍', '15.0', '14.0', '12.0', 'FruitTea', '品味来自武夷山的岩骨花香，香气馥郁的大红袍和清新果片的美妙相遇，浅尝一口，唇齿留香。', 'drink/guopian.jpg');

-- ----------------------------
-- Table structure for `drinkorder`
-- ----------------------------
DROP TABLE IF EXISTS `drinkorder`;
CREATE TABLE `drinkorder` (
  `OrderID` int(11) NOT NULL AUTO_INCREMENT,
  `UserID` int(11) NOT NULL,
  `AddrID` int(11) DEFAULT NULL,
  `TotalPrice` float(8,2) NOT NULL,
  `OrderTime` date NOT NULL,
  `PayState` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`OrderID`),
  KEY `UserID` (`UserID`),
  KEY `AddrID` (`AddrID`),
  CONSTRAINT `drinkorder_ibfk_1` FOREIGN KEY (`UserID`) REFERENCES `user` (`UserID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `drinkorder_ibfk_2` FOREIGN KEY (`AddrID`) REFERENCES `useraddr` (`AddrID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of drinkorder
-- ----------------------------
INSERT INTO `drinkorder` VALUES ('4', '7', '11', '63.00', '2020-06-25', '1');
INSERT INTO `drinkorder` VALUES ('5', '8', '12', '36.00', '2020-06-25', '1');
INSERT INTO `drinkorder` VALUES ('6', '9', '13', '75.00', '2020-06-25', '1');
INSERT INTO `drinkorder` VALUES ('7', '9', '13', '24.00', '2020-06-26', '1');
INSERT INTO `drinkorder` VALUES ('8', '1', '2', '58.00', '2020-06-26', '1');
INSERT INTO `drinkorder` VALUES ('9', '3', '14', '48.00', '2020-06-26', '1');
INSERT INTO `drinkorder` VALUES ('10', '2', '15', '18.00', '2020-06-27', '1');
INSERT INTO `drinkorder` VALUES ('11', '7', '11', '52.00', '2020-06-27', '1');
INSERT INTO `drinkorder` VALUES ('12', '10', '16', '55.00', '2020-06-27', '1');
INSERT INTO `drinkorder` VALUES ('13', '11', '17', '42.00', '2020-06-28', '1');
INSERT INTO `drinkorder` VALUES ('14', '5', '18', '52.00', '2020-06-28', '1');
INSERT INTO `drinkorder` VALUES ('15', '5', '18', '42.00', '2020-06-28', '1');
INSERT INTO `drinkorder` VALUES ('16', '6', '19', '48.00', '2020-06-29', '1');
INSERT INTO `drinkorder` VALUES ('17', '4', '20', '44.00', '2020-06-29', '1');
INSERT INTO `drinkorder` VALUES ('18', '7', '11', '39.00', '2020-06-29', '1');
INSERT INTO `drinkorder` VALUES ('19', '1', '9', '107.00', '2020-06-30', '1');
INSERT INTO `drinkorder` VALUES ('20', '8', '12', '60.00', '2020-06-30', '1');
INSERT INTO `drinkorder` VALUES ('21', '2', '15', '48.00', '2020-07-01', '1');
INSERT INTO `drinkorder` VALUES ('22', '9', '13', '45.00', '2020-07-01', '1');
INSERT INTO `drinkorder` VALUES ('23', '11', '17', '94.00', '2020-07-01', '1');
INSERT INTO `drinkorder` VALUES ('24', '7', '21', '20.00', '2020-07-02', '1');
INSERT INTO `drinkorder` VALUES ('27', '16', '24', '24.00', '2020-07-02', '1');
INSERT INTO `drinkorder` VALUES ('28', '18', '25', '24.00', '2020-07-03', '1');
INSERT INTO `drinkorder` VALUES ('29', '20', '26', '30.00', '2020-07-03', '1');

-- ----------------------------
-- Table structure for `notice`
-- ----------------------------
DROP TABLE IF EXISTS `notice`;
CREATE TABLE `notice` (
  `n_id` int(11) NOT NULL,
  `title` varchar(32) DEFAULT NULL,
  `details` varchar(200) DEFAULT NULL,
  `n_time` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`n_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of notice
-- ----------------------------
INSERT INTO `notice` VALUES ('1', '618大促', '618全场6.18折，上不封顶', '2020/06/17');
INSERT INTO `notice` VALUES ('2', '抗疫我们在行动', '为了鼓励大家足不出户喝奶茶，现在所有饮品均免配送费', '2020/06/01');
INSERT INTO `notice` VALUES ('3', '端午节上新啦', '端午节和朋友来杯超级水果茶吧', '2020/06/25');

-- ----------------------------
-- Table structure for `orderitem`
-- ----------------------------
DROP TABLE IF EXISTS `orderitem`;
CREATE TABLE `orderitem` (
  `OrderID` int(11) NOT NULL,
  `DrinkID` int(11) NOT NULL,
  `DrinkSweet` char(10) NOT NULL,
  `DrinkTemp` char(10) NOT NULL,
  `DrinkSpec` char(10) NOT NULL,
  `Number` int(11) NOT NULL,
  PRIMARY KEY (`OrderID`,`DrinkID`,`DrinkSweet`,`DrinkTemp`,`DrinkSpec`),
  KEY `DrinkID` (`DrinkID`),
  CONSTRAINT `orderitem_ibfk_1` FOREIGN KEY (`OrderID`) REFERENCES `drinkorder` (`OrderID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `orderitem_ibfk_2` FOREIGN KEY (`DrinkID`) REFERENCES `drink` (`DrinkID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of orderitem
-- ----------------------------
INSERT INTO `orderitem` VALUES ('4', '1', '全糖', '常温', '超级杯', '1');
INSERT INTO `orderitem` VALUES ('4', '10', '全糖', '加冰', '大杯', '2');
INSERT INTO `orderitem` VALUES ('4', '12', '全糖', '加冰', '中杯', '1');
INSERT INTO `orderitem` VALUES ('5', '8', '全糖', '常温', '大杯', '1');
INSERT INTO `orderitem` VALUES ('5', '13', '少糖', '加冰', '中杯', '3');
INSERT INTO `orderitem` VALUES ('6', '3', '半糖', '热', '超级杯', '2');
INSERT INTO `orderitem` VALUES ('6', '4', '半糖', '热', '大杯', '1');
INSERT INTO `orderitem` VALUES ('6', '7', '少糖', '加冰', '中杯', '2');
INSERT INTO `orderitem` VALUES ('7', '14', '全糖', '热', '超级杯', '1');
INSERT INTO `orderitem` VALUES ('7', '15', '全糖', '热', '超级杯', '1');
INSERT INTO `orderitem` VALUES ('8', '8', '半糖', '加冰', '超级杯', '2');
INSERT INTO `orderitem` VALUES ('8', '18', '全糖', '常温', '大杯', '2');
INSERT INTO `orderitem` VALUES ('9', '9', '全糖', '加冰', '大杯', '4');
INSERT INTO `orderitem` VALUES ('10', '16', '半糖', '热', '大杯', '2');
INSERT INTO `orderitem` VALUES ('11', '15', '全糖', '热', '超级杯', '1');
INSERT INTO `orderitem` VALUES ('11', '17', '全糖', '热', '超级杯', '2');
INSERT INTO `orderitem` VALUES ('12', '2', '全糖', '加冰', '超级杯', '1');
INSERT INTO `orderitem` VALUES ('12', '14', '全糖', '热', '大杯', '2');
INSERT INTO `orderitem` VALUES ('12', '17', '全糖', '热', '超级杯', '1');
INSERT INTO `orderitem` VALUES ('13', '5', '半糖', '加冰', '中杯', '2');
INSERT INTO `orderitem` VALUES ('13', '11', '全糖', '热', '大杯', '1');
INSERT INTO `orderitem` VALUES ('14', '1', '全糖', '热', '超级杯', '1');
INSERT INTO `orderitem` VALUES ('14', '6', '全糖', '常温', '中杯', '1');
INSERT INTO `orderitem` VALUES ('14', '8', '全糖', '常温', '超级杯', '1');
INSERT INTO `orderitem` VALUES ('15', '6', '半糖', '加冰', '中杯', '2');
INSERT INTO `orderitem` VALUES ('15', '16', '半糖', '加冰', '大杯', '2');
INSERT INTO `orderitem` VALUES ('16', '17', '全糖', '热', '超级杯', '1');
INSERT INTO `orderitem` VALUES ('16', '18', '半糖', '加冰', '大杯', '2');
INSERT INTO `orderitem` VALUES ('17', '4', '全糖', '热', '超级杯', '1');
INSERT INTO `orderitem` VALUES ('17', '15', '全糖', '热', '超级杯', '2');
INSERT INTO `orderitem` VALUES ('18', '4', '半糖', '常温', '中杯', '2');
INSERT INTO `orderitem` VALUES ('18', '10', '半糖', '热', '大杯', '1');
INSERT INTO `orderitem` VALUES ('19', '3', '全糖', '热', '超级杯', '2');
INSERT INTO `orderitem` VALUES ('19', '5', '全糖', '热', '超级杯', '1');
INSERT INTO `orderitem` VALUES ('19', '8', '全糖', '加冰', '超级杯', '2');
INSERT INTO `orderitem` VALUES ('19', '14', '全糖', '热', '超级杯', '1');
INSERT INTO `orderitem` VALUES ('20', '1', '全糖', '热', '超级杯', '1');
INSERT INTO `orderitem` VALUES ('20', '6', '全糖', '热', '超级杯', '1');
INSERT INTO `orderitem` VALUES ('20', '17', '少糖', '热', '中杯', '1');
INSERT INTO `orderitem` VALUES ('21', '2', '半糖', '加冰', '大杯', '2');
INSERT INTO `orderitem` VALUES ('21', '7', '半糖', '热', '大杯', '1');
INSERT INTO `orderitem` VALUES ('21', '11', '少糖', '常温', '大杯', '1');
INSERT INTO `orderitem` VALUES ('22', '3', '半糖', '热', '大杯', '2');
INSERT INTO `orderitem` VALUES ('22', '7', '全糖', '热', '超级杯', '1');
INSERT INTO `orderitem` VALUES ('23', '11', '半糖', '热', '大杯', '2');
INSERT INTO `orderitem` VALUES ('23', '13', '半糖', '热', '大杯', '2');
INSERT INTO `orderitem` VALUES ('23', '17', '全糖', '热', '超级杯', '1');
INSERT INTO `orderitem` VALUES ('23', '18', '全糖', '热', '超级杯', '2');
INSERT INTO `orderitem` VALUES ('24', '2', '全糖', '热', '中杯', '2');
INSERT INTO `orderitem` VALUES ('27', '3', '半糖', '常温', '中杯', '2');
INSERT INTO `orderitem` VALUES ('28', '6', '全糖', '热', '中杯', '2');
INSERT INTO `orderitem` VALUES ('29', '4', '半糖', '常温', '大杯', '2');

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `UserID` int(11) NOT NULL AUTO_INCREMENT,
  `UserName` char(8) NOT NULL,
  `UserPwd` char(8) NOT NULL,
  `UserPhone` char(11) NOT NULL,
  PRIMARY KEY (`UserID`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', '李华123', '123456', '13546219458');
INSERT INTO `user` VALUES ('2', '王祥123', '12345678', '13956442645');
INSERT INTO `user` VALUES ('3', '张一123', '456253', '15846523479');
INSERT INTO `user` VALUES ('4', '刘建123', '159475', '13956424697');
INSERT INTO `user` VALUES ('5', '华锋123', '125964', '15863212645');
INSERT INTO `user` VALUES ('6', '李梓慕12', '458621', '13459621315');
INSERT INTO `user` VALUES ('7', 'zzzz', '1234', '14857954515');
INSERT INTO `user` VALUES ('8', 'eeee', '1234', '13456897541');
INSERT INTO `user` VALUES ('9', 'yy123', '1234', '18908356906');
INSERT INTO `user` VALUES ('10', '自信且爱笑15', '12345', '13695685214');
INSERT INTO `user` VALUES ('11', 'ww1234', '1234', '13458696541');
INSERT INTO `user` VALUES ('16', 'wang', '1111', '14785963251');
INSERT INTO `user` VALUES ('18', 'wang123', '1111', '15862549632');
INSERT INTO `user` VALUES ('20', 'wang789', '1111', '14857954515');

-- ----------------------------
-- Table structure for `useraddr`
-- ----------------------------
DROP TABLE IF EXISTS `useraddr`;
CREATE TABLE `useraddr` (
  `AddrID` int(11) NOT NULL AUTO_INCREMENT,
  `UserID` int(11) NOT NULL,
  `City` char(20) NOT NULL,
  `County` char(20) NOT NULL,
  `Street` char(20) NOT NULL,
  `HouseNum` char(10) NOT NULL,
  PRIMARY KEY (`AddrID`),
  KEY `UserID` (`UserID`),
  CONSTRAINT `useraddr_ibfk_1` FOREIGN KEY (`UserID`) REFERENCES `user` (`UserID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of useraddr
-- ----------------------------
INSERT INTO `useraddr` VALUES ('1', '1', '重庆', '沙坪坝区', '沙坪坝街道', '重庆大学A区中门');
INSERT INTO `useraddr` VALUES ('2', '1', '重庆', '沙坪坝区', '大学城', '重庆大学虎溪校区');
INSERT INTO `useraddr` VALUES ('8', '1', 'ChongQing', '沙坪坝区', '解放碑', '112');
INSERT INTO `useraddr` VALUES ('9', '1', 'ChongQing', '沙坪坝区', '解放碑', '112');
INSERT INTO `useraddr` VALUES ('11', '7', '重庆市', '沙坪坝区', '沙正街', '666号');
INSERT INTO `useraddr` VALUES ('12', '8', '重庆市', '沙坪坝区', '沙正街', '123号');
INSERT INTO `useraddr` VALUES ('13', '9', '重庆市', '沙坪坝区', '沙正街', '444号');
INSERT INTO `useraddr` VALUES ('14', '3', '重庆市', '沙坪坝区', '沙正街', '789号');
INSERT INTO `useraddr` VALUES ('15', '2', '重庆市', '沙坪坝区', '沙正街', '456号');
INSERT INTO `useraddr` VALUES ('16', '10', '重庆市', '南岸区', '涂山路', '158号');
INSERT INTO `useraddr` VALUES ('17', '11', '重庆市', '沙坪坝区', '沙正街', '7号');
INSERT INTO `useraddr` VALUES ('18', '5', '重庆市', '沙坪坝区', '沙正街', '55号');
INSERT INTO `useraddr` VALUES ('19', '6', '重庆市', '南岸区', '涂山路', '123号');
INSERT INTO `useraddr` VALUES ('20', '4', '重庆市', '沙坪坝区', '沙正街', '789号');
INSERT INTO `useraddr` VALUES ('21', '7', '重庆市', '南岸区', '涂山路', '123号');
INSERT INTO `useraddr` VALUES ('24', '16', '重庆市', '沙坪坝区', '沙正街', '666号');
INSERT INTO `useraddr` VALUES ('25', '18', '重庆市', '沙坪坝区', '沙正街', '666号');
INSERT INTO `useraddr` VALUES ('26', '20', '重庆市', '沙坪坝区', '沙正街', '666号');
