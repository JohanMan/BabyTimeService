CREATE TABLE `t_user` (
  `uid` char(19) NOT NULL PRIMARY KEY COMMENT '用户id',
  `openid` varchar(128) DEFAULT '' COMMENT '微信openid',
  `mobile` varchar(11) DEFAULT '' COMMENT '手机号',
  `password` varchar(32) DEFAULT NULL COMMENT '密码',
  `nick_name` varchar(50) DEFAULT NULL COMMENT '昵称',
  `avatar` varchar(255) DEFAULT NULL COMMENT '用户头像',
  `is_disabled` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否禁用 1（true）已禁用，  0（false）未禁用',
  `is_deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '逻辑删除 1（true）已删除， 0（false）未删除',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间'
) ENGINE=InnoDB CHARSET=utf8 COMMENT='用户表';

INSERT `t_user` (`uid`, `password`, `nick_name`) VALUES ('1000000000000000000', 'cbd991ef5c7db470524913e8f47dadf8', '管理员'); # admin@baby

CREATE TABLE `t_role` (
  `rid` int(11) NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT '角色id',
  `role_level` tinyint(1) NOT NULL DEFAULT '0' COMMENT '角色等级',
  `role_name` varchar(32) DEFAULT '' COMMENT '角色名称',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间'
) ENGINE=InnoDB CHARSET=utf8 COMMENT='角色表';

INSERT `t_role` (`role_level`, `role_name`) VALUES (1, '管理员');
INSERT `t_role` (`role_level`, `role_name`) VALUES (2, '后台管理员');
INSERT `t_role` (`role_level`, `role_name`) VALUES (3, 'VIP会员');
INSERT `t_role` (`role_level`, `role_name`) VALUES (4, '会员');

CREATE TABLE `t_user_role` (
  `id` char(19) NOT NULL PRIMARY KEY COMMENT 'id',
  `uid` char(19) NOT NULL COMMENT '用户id',
  `rid` int(11) NOT NULL COMMENT '角色id',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间'
) ENGINE=InnoDB CHARSET=utf8 COMMENT='用户角色表';

INSERT `t_user_role` (`id`, `uid`, `rid`) VALUES ('2000000000000000000', '1000000000000000000', '1');

CREATE TABLE `t_permission` (
  `pid` int(11) NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT '权限id',
  `permission_name` varchar(32) DEFAULT '' COMMENT '权限名称',
  `url` varchar(128) DEFAULT '' COMMENT '地址',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间'
) ENGINE=InnoDB CHARSET=utf8 COMMENT='权限表';

CREATE TABLE `t_role_permission` (
  `id` int(11) NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT 'id',
  `rid` int(11) NOT NULL COMMENT '角色id',
  `pid` int(11) NOT NULL COMMENT '权限id',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间'
) ENGINE=InnoDB CHARSET=utf8 COMMENT='角色权限表';



