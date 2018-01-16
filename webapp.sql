drop table if exists f_role;

/*==============================================================*/
/* Table: f_role                                                */
/*==============================================================*/
CREATE TABLE `f_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主签ID',
  `role_code` varchar(50) DEFAULT NULL COMMENT '角色编码',
  `role_name` varchar(100) DEFAULT NULL COMMENT '角色名称',
  `description` varchar(200) DEFAULT NULL COMMENT '描述',
  `pid` int(11) DEFAULT NULL COMMENT '父ID',
  `role_type` int(11) DEFAULT NULL COMMENT '角色类型：1-管理角色；2-业务角色',
  `create_by` int(11) DEFAULT NULL,
  `create_datetime` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `update_by` int(11) DEFAULT NULL,
  `update_datetime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8;


alter table f_role comment '角色表';




drop table if exists f_group;

/*==============================================================*/
/* Table: f_group                                               */
/*==============================================================*/
CREATE TABLE `f_group` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键，组ID',
  `group_name` varchar(100) DEFAULT NULL COMMENT '组名称',
  `group_type` int(11) DEFAULT NULL COMMENT '组类型:1-用户组',
  `description` varchar(200) DEFAULT NULL COMMENT '描述',
  `create_by` int(11) DEFAULT NULL,
  `create_datetime` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `update_by` int(11) DEFAULT NULL,
  `update_datetime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

alter table f_group comment '组表';



drop table if exists f_role_resources;

/*==============================================================*/
/* Table: f_role_resources                                      */
/*==============================================================*/
CREATE TABLE `f_role_resources` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `role_id` int(11) DEFAULT NULL COMMENT '角色ID',
  `resource_id` int(11) DEFAULT NULL COMMENT '权限(资源)ID',
  `create_by` int(11) DEFAULT NULL,
  `create_datetime` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8165 DEFAULT CHARSET=utf8;


alter table f_role_resources comment '角色权限(资源)表';


drop table if exists f_group_resources;

/*==============================================================*/
/* Table: f_group_resources                                     */
/*==============================================================*/
CREATE TABLE `f_group_resources` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `group_id` int(11) DEFAULT NULL COMMENT '组ID',
  `resource_id` int(11) DEFAULT NULL COMMENT '权限(资源)ID',
  `create_by` int(11) DEFAULT NULL,
  `create_date` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=59 DEFAULT CHARSET=utf8;

alter table f_group_resources comment '组权限（资源）关系表';


drop table if exists f_group_role;

/*==============================================================*/
/* Table: f_group_role                                          */
/*==============================================================*/
CREATE TABLE `f_group_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `group_id` int(11) DEFAULT NULL COMMENT '组ID',
  `role_id` int(11) DEFAULT NULL COMMENT '角色ID',
  `create_by` int(11) DEFAULT NULL,
  `create_datetime` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;

alter table f_group_role comment '组角色关系表';


drop table if exists f_user_role;

/*==============================================================*/
/* Table: f_user_role                                           */
/*==============================================================*/
CREATE TABLE `f_user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL COMMENT '用户ID',
  `role_id` int(11) DEFAULT NULL COMMENT '角色ID',
  `create_by` int(11) DEFAULT NULL,
  `create_datetime` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table f_user_role comment '用户组角色关系表';



drop table if exists f_user_group;

/*==============================================================*/
/* Table: f_user_group                                          */
/*==============================================================*/
CREATE TABLE `f_user_group` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL COMMENT '用户ID',
  `group_id` int(11) DEFAULT NULL COMMENT '组ID',
  `create_by` int(11) DEFAULT NULL,
  `create_datetime` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8;

alter table f_user_group comment '用户组关系表';





drop table if exists f_sysuserinfo;

/*==============================================================*/
/* Table: f_sysuserinfo                                         */
/*==============================================================*/
CREATE TABLE `f_sysuserinfo` (
  `userid` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID，自动递增',
  `agentID` varchar(20) NOT NULL COMMENT '用户工号',
  `password` varchar(100) NOT NULL COMMENT '用户密码',
  `usertype` char(1) NOT NULL COMMENT '用户类型，对应通用字典表user_type',
  `username` varchar(50) NOT NULL COMMENT '姓名',
  `sex` char(1) DEFAULT NULL COMMENT '性别，对应通用字典表sex',
  `cardID` char(18) DEFAULT NULL COMMENT '身份证号',
  `mobile` char(11) DEFAULT NULL COMMENT '手机号',
  `nickname` varchar(50) DEFAULT NULL COMMENT '昵称',
  `is_nickname` char(1) DEFAULT NULL COMMENT '报告中是否使用昵称，对应通用字典表is_flag',
  `status` char(1) NOT NULL COMMENT '状态，对应通用字典表status',
  `createtime` datetime NOT NULL COMMENT '创建时间',
  `updatetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `pwd_last_datetime` datetime DEFAULT NULL COMMENT '密码最后修改时间',
  PRIMARY KEY (`userid`)
) ENGINE=InnoDB AUTO_INCREMENT=77 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='系统用户信息表';


alter table f_sysuserinfo comment '系统用户信息表';




drop table if exists f_user_resources;

/*==============================================================*/
/* Table: f_user_resources                                      */
/*==============================================================*/
CREATE TABLE `f_user_resources` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userid` int(11) DEFAULT NULL,
  `resourceid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7819 DEFAULT CHARSET=utf8;

alter table f_user_resources comment '用户（权限）资源关系表';




drop table if exists f_resources;

/*==============================================================*/
/* Table: f_resources                                           */
/*==============================================================*/
CREATE TABLE `f_resources` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(100) DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL COMMENT '名称',
  `url` varchar(100) DEFAULT NULL COMMENT 'URL',
  `function` varchar(100) DEFAULT NULL COMMENT '功能函数',
  `description` varchar(100) DEFAULT NULL COMMENT '描述',
  `classname` varchar(50) DEFAULT NULL,
  `icon` varchar(100) DEFAULT NULL COMMENT '图标',
  `type` int(11) DEFAULT NULL COMMENT '类型:1表示菜单，2表示功能按钮',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  `pid` int(11) DEFAULT NULL COMMENT '父ID',
  `level` int(11) DEFAULT NULL COMMENT '层级',
  `leaf` tinyint(1) DEFAULT NULL COMMENT '是否为子节点',
  `site` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=277 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

alter table f_resources comment '资源信息';

/*========================================================================================================================================================================*/
/*-----------------------------------------------------------------------------------建表脚本-----------------------------------------------------------------------------*/
/*========================================================================================================================================================================*/


/*========================================================================================================================================================================*/
/*-----------------------------------------------------------------------------------加字段脚本-----------------------------------------------------------------------------*/
/*========================================================================================================================================================================*/


/*========================================================================================================================================================================*/
/*-----------------------------------------------------------------------------------insert语句-----------------------------------------------------------------------------*/
/*========================================================================================================================================================================*/
/*插入user用户表测试使用*/
INSERT INTO `test`.`f_sysuserinfo`
(`agentID`,`password`,`usertype`,`username`,`sex`,`cardID`,`mobile`,`nickname`,`is_nickname`,`status`,`createtime`,`updatetime`,`pwd_last_datetime`)
VALUES
(1,'E00D9267C1AEC025FF3E69394179ECA513E406E2044DEDA8EBF39816','1','weidongdong','1',1,6,11,'1',0,now(),now(),now());