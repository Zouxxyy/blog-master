
SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for article
-- ----------------------------
DROP TABLE IF EXISTS `article`;
CREATE TABLE `article` (
  `article_id` int(11) NOT NULL AUTO_INCREMENT,
  `article_user_id` int(11) unsigned DEFAULT '1',
  `article_category_id` int(11) NOT NULL COMMENT '博客分类id',
  `article_title` varchar(255) DEFAULT NULL,
  `article_content` mediumtext,
  `article_view_count` bigint(20) DEFAULT '0',
  `article_comment_count` int(11) DEFAULT '0',
  `article_like_count` int(11) DEFAULT '0',
  `article_status` tinyint(4) unsigned DEFAULT '1',
  `article_enable_comment` tinyint(4) unsigned DEFAULT '1',
  `article_update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '添加时间',
  `article_create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`article_id`)
) ENGINE=MyISAM AUTO_INCREMENT=173 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of article
-- ----------------------------
INSERT INTO `article` VALUES ('1', '1', '1',  '测试标题1', '测试用文章内容1', '0', '0', '0', '1', '1', '2019-01-01 00:00:00', '2019-01-01 00:00:00');
INSERT INTO `article` VALUES ('2', '1', '2',  '测试标题2', '测试用文章内容2', '0', '0', '0', '0', '1', '2019-01-02 00:00:00', '2019-01-01 00:00:00');
INSERT INTO `article` VALUES ('3', '1', '1',  '测试标题3', '测试用文章内容3', '0', '0', '0', '1', '1', '2019-01-03 00:00:00', '2019-01-01 00:00:00');
INSERT INTO `article` VALUES ('4', '1', '1',  '测试标题4', '测试用文章内容4', '0', '0', '0', '0', '1', '2019-01-04 00:00:00', '2019-01-01 00:00:00');
INSERT INTO `article` VALUES ('5', '1', '3',  '测试标题4', '测试用文章内容5', '0', '0', '0', '1', '1', '2019-01-05 00:00:00', '2019-01-01 00:00:00');
INSERT INTO `article` VALUES ('6', '1', '3',  '测试标题5', '测试用文章内容6', '0', '0', '0', '0', '1', '2019-01-06 00:00:00', '2019-01-01 00:00:00');
INSERT INTO `article` VALUES ('7', '1', '1',  '测试标题6', '测试用文章内容7', '0', '0', '0', '0', '1', '2019-01-07 00:00:00', '2019-01-01 00:00:00');
INSERT INTO `article` VALUES ('8', '1', '3',  '测试标题7', '测试用文章内容8', '0', '0', '0', '1', '1', '2019-01-08 00:00:00', '2019-01-01 00:00:00');
INSERT INTO `article` VALUES ('9', '1', '3',  '测试标题8', '测试用文章内容9', '0', '0', '0', '1', '1', '2019-01-09 00:00:00', '2019-01-01 00:00:00');
INSERT INTO `article` VALUES ('10', '1', '1',  '测试标题9', '测试用文章内容10', '0', '0', '0', '1', '1', '2019-01-10 00:00:00', '2019-01-01 00:00:00');
INSERT INTO `article` VALUES ('11', '1', '2',  '测试标题10', '测试用文章内容11', '0', '0', '0', '1', '1', '2019-01-11 00:00:00', '2019-01-01 00:00:00');
INSERT INTO `article` VALUES ('12', '1', '1',  '测试标题11', '测试用文章内容12', '0', '0', '0', '1', '1', '2019-01-12 00:00:00', '2019-01-01 00:00:00');


-- ----------------------------
-- Table structure for article_tag_ref
-- ----------------------------
DROP TABLE IF EXISTS `article_tag_ref`;
CREATE TABLE `article_tag_ref` (
  `article_id` int(11) NOT NULL,
  `tag_id` int(11) NOT NULL,
  PRIMARY KEY (`article_id`,`tag_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of article_tag_ref
-- ----------------------------
INSERT INTO `article_tag_ref` VALUES ('1', '1');
INSERT INTO `article_tag_ref` VALUES ('2', '2');
INSERT INTO `article_tag_ref` VALUES ('3', '1');
INSERT INTO `article_tag_ref` VALUES ('3', '2');
INSERT INTO `article_tag_ref` VALUES ('4', '2');
INSERT INTO `article_tag_ref` VALUES ('5', '1');
INSERT INTO `article_tag_ref` VALUES ('6', '1');
INSERT INTO `article_tag_ref` VALUES ('7', '2');
INSERT INTO `article_tag_ref` VALUES ('7', '3');
INSERT INTO `article_tag_ref` VALUES ('8', '1');
INSERT INTO `article_tag_ref` VALUES ('9', '2');
INSERT INTO `article_tag_ref` VALUES ('10', '2');
INSERT INTO `article_tag_ref` VALUES ('11', '3');
INSERT INTO `article_tag_ref` VALUES ('12', '1');

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category` (
  `category_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `category_name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`category_id`),
  UNIQUE KEY `category_name` (`category_name`)
) ENGINE=InnoDB AUTO_INCREMENT=64 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of category
-- ----------------------------
INSERT INTO `category` VALUES ('1', 'linux');
INSERT INTO `category` VALUES ('2', 'Java基础');
INSERT INTO `category` VALUES ('3', 'spring');
INSERT INTO `category` VALUES ('4', 'web');
INSERT INTO `category` VALUES ('5', 'python');
INSERT INTO `category` VALUES ('6', 'spark');



-- ----------------------------
-- Table structure for tag
-- ----------------------------
DROP TABLE IF EXISTS `tag`;
CREATE TABLE `tag` (
  `tag_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `tag_name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`tag_id`),
  UNIQUE KEY `tag_name` (`tag_name`)
) ENGINE=InnoDB AUTO_INCREMENT=59 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tag
-- ----------------------------
INSERT INTO `tag` VALUES ('1', 'spring');
INSERT INTO `tag` VALUES ('2', 'idea');
INSERT INTO `tag` VALUES ('3', '测试标签1');
INSERT INTO `tag` VALUES ('4', '测试标签2');
INSERT INTO `tag` VALUES ('5', '测试标签3');
INSERT INTO `tag` VALUES ('6', '测试标签4');
INSERT INTO `tag` VALUES ('7', '测试标签5');


-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `user_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `user_name` varchar(255) NOT NULL DEFAULT '',
  `user_pass` varchar(255) NOT NULL DEFAULT '',
  `user_nickname` varchar(255) NOT NULL DEFAULT '',
  `user_signature` varchar(500) DEFAULT NULL,
  `user_email` varchar(100) DEFAULT '',
  `user_avatar` varchar(255) DEFAULT NULL,
  `user_profile` text,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `user_name` (`user_name`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'admin', 'e10adc3949ba59abbe56e057f20f883e', 'zouxxyy', '测试签名', '12345678@qq.com', '', '测试简介');

-- ----------------------------
-- Table structure for comment
-- ----------------------------

DROP TABLE IF EXISTS `comment`;

CREATE TABLE `comment` (
  `comment_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `comment_pid` bigint(20) unsigned DEFAULT '0' COMMENT '父id',
  `comment_article_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '关联的article主键',
  `comment_author_name` varchar(50) NOT NULL DEFAULT '' COMMENT '评论者名称',
  `comment_author_email` varchar(100) NOT NULL DEFAULT '' COMMENT '评论人的邮箱',
  `comment_author_ip` varchar(20) NOT NULL DEFAULT '' COMMENT '评论时的ip地址',
  `comment_content` varchar(200) NOT NULL DEFAULT '' COMMENT '评论内容',
  `comment_create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '评论提交时间',
  `comment_status` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否审核通过 0-未审核 1-审核通过 2-管理员回复',
  `comment_like_count` int(11) DEFAULT '0' COMMENT '评论点赞数',
  PRIMARY KEY (`comment_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



-- ----------------------------
-- Records of comment
-- ----------------------------
INSERT INTO `comment` VALUES ('1', '0', '1', '游客1', '12345678@qq.com', '0.0.0.0', '测试评论1', '2019-01-01 20:12:12', '0', '0');
INSERT INTO `comment` VALUES ('2', '0', '2', '游客2', '12345678@qq.com', '0.0.0.0', '测试评论2', '2019-01-02 20:12:12', '0', '0');
INSERT INTO `comment` VALUES ('3', '0', '1', '游客3', '12345678@qq.com', '0.0.0.0', '测试评论3', '2019-01-03 20:12:12', '0', '0');
INSERT INTO `comment` VALUES ('4', '0', '3', '游客4', '12345678@qq.com', '0.0.0.0', '测试评论4', '2019-01-14 20:12:12', '0', '0');
INSERT INTO `comment` VALUES ('5', '0', '1', '游客5', '12345678@qq.com', '0.0.0.0', '测试评论5', '2019-01-21 20:12:12', '0', '0');
INSERT INTO `comment` VALUES ('6', '0', '4', '游客1', '12345678@qq.com', '0.0.0.0', '测试评论6', '2019-05-11 20:12:12', '0', '0');
INSERT INTO `comment` VALUES ('7', '0', '7', '游客2', '12345678@qq.com', '0.0.0.0', '测试评论7', '2019-01-06 20:12:12', '0', '0');
INSERT INTO `comment` VALUES ('8', '0', '5', '游客6', '12345678@qq.com', '0.0.0.0', '测试评论8', '2019-01-15 20:12:12', '0', '0');
INSERT INTO `comment` VALUES ('9', '0', '1', '游客1', '12345678@qq.com', '0.0.0.0', '测试评论9', '2019-01-01 10:12:12', '0', '0');
INSERT INTO `comment` VALUES ('10', '0', '3', '游客3', '12345678@qq.com', '0.0.0.0', '测试评论10', '2019-04-05 20:12:12', '0', '0');
INSERT INTO `comment` VALUES ('11', '0', '11', '游客1', '12345678@qq.com', '0.0.0.0', '测试评论11', '2019-02-01 20:12:12', '0', '0');
INSERT INTO `comment` VALUES ('12', '0', '6', '游客3', '12345678@qq.com', '0.0.0.0', '测试评论12', '2019-03-01 20:12:12', '0', '0');



-- ----------------------------
-- Table structure for log
-- ----------------------------
DROP TABLE IF EXISTS `log`;
CREATE TABLE `log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `time` datetime DEFAULT NULL COMMENT '操作时间',
  `type` varchar(255) DEFAULT NULL COMMENT '操作类型',
  `detail` varchar(255) DEFAULT NULL COMMENT '详情',
  `ip` varchar(255) DEFAULT NULL COMMENT 'ip',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=482 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of log
-- ----------------------------
INSERT INTO `log` VALUES ('1', '2019-01-20 22:49:51', '登陆', '用户登录', '0:0:0:0:0:0:0:1');
INSERT INTO `log` VALUES ('2', '2019-01-20 22:55:40', '登陆', '用户登录', '0:0:0:0:0:0:0:1');
INSERT INTO `log` VALUES ('3', '2019-01-20 22:55:50', '修改', '修改资料', '0:0:0:0:0:0:0:1');
INSERT INTO `log` VALUES ('4', '2019-01-20 22:55:59', '修改', '修改密码', '0:0:0:0:0:0:0:1');