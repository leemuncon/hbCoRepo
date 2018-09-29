USE test;

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


CREATE TABLE `company` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(1024) NOT NULL COMMENT '公司名称',
  `nature` varchar(1024) NOT NULL COMMENT '公司性质',
  `legal_person` varchar(1024) NOT NULL COMMENT '法人代表',
  `business_license` varchar(1024) NOT NULL COMMENT '营业执照',
  `contact` varchar(1024) COMMENT '联系人',
  `phone` varchar(1024) COMMENT '联系电话',
  `province` varchar(128) COMMENT '省份',
  `city` varchar(128) COMMENT '城市',
  `district` varchar(128) COMMENT '县区',
  `address` varchar(1024) COMMENT '地址',
  `stuff_number` varchar(1024) COMMENT '职工人数',
  `last_year_production_value` varchar(1024) COMMENT '去年营业额',
  `production_value_unit` varchar(1024) COMMENT '单位',
  `laboratory_level` varchar(1024) COMMENT '实验室资质',
  `authentication` varchar(1024) COMMENT '认证情况',
  `production_license` varchar(1024) COMMENT '生产许可证',
  `status` int(11) NOT NULL DEFAULT 0 COMMENT '状态 0 正常 1 关闭',
  `check_year` varchar(1024) NOT NULL COMMENT '统计年份',
  `deleted` tinyint(2) NOT NULL DEFAULT 0 COMMENT '是否删除 0 未删除 1 已删除',
  `create_time` datetime NOT NULL DEFAULT NOW() COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT NOW() COMMENT '跟新时间' on update now(),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `product` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `product_category` varchar(1024) COMMENT '产品类型',
  `product_name` varchar(1024) COMMENT '产品名称',
  `executive_standard` varchar(1024) COMMENT '执行标准',
  `output` varchar(1024) COMMENT '产量',
  `unit` varchar(1024) COMMENT '单位',
  `company_id` int(11) NOT NULL COMMENT '公司id',
  `deleted` tinyint(2) NOT NULL DEFAULT 0 COMMENT '是否删除 0 未删除 1 已删除',
  `create_time` datetime NOT NULL DEFAULT NOW() COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT NOW() COMMENT '跟新时间' on update now(),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `equipment` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `type` varchar(1024) COMMENT '设备类型',
  `capacity` varchar(1024) COMMENT '产能',
  `output` varchar(1024) COMMENT '产量',
  `unit` varchar(1024) COMMENT '单位',
  `company_id` int(11) NOT NULL COMMENT '公司id',
  `deleted` tinyint(2) NOT NULL DEFAULT 0 COMMENT '是否删除 0 未删除 1 已删除',
  `create_time` datetime NOT NULL DEFAULT NOW() COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT NOW() COMMENT '跟新时间' on update now(),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4