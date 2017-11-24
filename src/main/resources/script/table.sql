CREATE TABLE `user` (
  `userId` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `userName` varchar(255) DEFAULT NULL COMMENT '用户名',
  `age` int(11) DEFAULT NULL COMMENT '用户年龄',
  `password` varchar(255) DEFAULT NULL COMMENT '密码',
  `phoneNo` varchar(50) DEFAULT NULL COMMENT '手机号',
  PRIMARY KEY (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=1011 DEFAULT CHARSET=utf8;