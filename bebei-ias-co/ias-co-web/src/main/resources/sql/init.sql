CREATE TABLE `opt_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `username` varchar(128) NOT NULL COMMENT '用户名',
  `nickname` varchar(256) NOT NULL COMMENT '昵称',
  `password_md5` varchar(256) NOT NULL COMMENT '用户名',
  `status` int(11) NOT NULL DEFAULT 0 COMMENT '状态 待定',
  `deleted` tinyint(2) NOT NULL DEFAULT 0 COMMENT '是否删除 0 未删除 1 已删除',
  `created` datetime NOT NULL DEFAULT NOW() COMMENT '创建时间',
  `updated` datetime NOT NULL DEFAULT NOW() COMMENT '跟新时间',
  PRIMARY KEY (`id`),
  UNIQUE (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4