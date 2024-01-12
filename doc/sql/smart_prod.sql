CREATE DATABASE IF NOT EXISTS smart_prod CHARACTER SET utf8mb4 collate utf8mb4_0900_ai_ci;
USE smart_prod;

DROP TABLE IF EXISTS sys_layout;
CREATE TABLE `sys_layout`
(
    `id`   char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'id layout_id',
    `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '单据名称',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci
  ROW_FORMAT = DYNAMIC COMMENT ='单据布局表';

DROP TABLE IF EXISTS sys_layout_d;
CREATE TABLE `sys_layout_d`
(
    `id`             char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci    NOT NULL COMMENT 'id',
    `layout_id`      char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci      DEFAULT NULL COMMENT '单据id',
    `sort`           int                                                            DEFAULT NULL COMMENT '序号',
    `field_name`     varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci   DEFAULT NULL COMMENT '字段名称',
    `label`          varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '显示名称',
    `width`          int                                                            DEFAULT '0' COMMENT '显示宽度',
    `input_type`     varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci   DEFAULT NULL COMMENT '显示类型 text input dialog select filter date time button money',
    `visible`        bit(1)                                                         DEFAULT NULL COMMENT '是否显示',
    `readonly`       bit(1)                                                         DEFAULT NULL COMMENT '是否只读',
    `layout_type`    varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci   DEFAULT NULL COMMENT '表单类型 table/form',
    `data_source`    varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  DEFAULT NULL COMMENT '数据源 作为 select 下拉的数据源 以及 按钮组对象',
    `validate_rules` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  DEFAULT NULL COMMENT '表单验证',
    `filterable`     bit(1)                                                         DEFAULT NULL COMMENT '下拉搜索',
    `auth_config`    varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '用户角色表ID',
    `sortable`       bit(1)                                                         DEFAULT b'0' COMMENT '是否前端排序',
    PRIMARY KEY (`id`) USING BTREE,
    KEY `idx_id` (`id`) USING BTREE,
    KEY `idx_layout_id` (`layout_id`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci
  ROW_FORMAT = DYNAMIC COMMENT ='单据布局明细';

DROP TABLE IF EXISTS sys_layout_user;
CREATE TABLE `sys_layout_user`
(
    `id`           char(19)                                                  NOT NULL COMMENT 'id',
    `user_id`      char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '用户id',
    `content`      longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '内容',
    `gmt_create`   datetime                                                  DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `gmt_modified` datetime                                                  DEFAULT NULL COMMENT '修改时间',
    PRIMARY KEY (`id`) USING BTREE,
    KEY `idx_user_id` (`user_id`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT ='用户布局表';

DROP TABLE IF EXISTS sys_user;
CREATE TABLE `sys_user`
(
    `id`           char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'ID',
    `username`     varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  DEFAULT NULL COMMENT '用户名',
    `password`     varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  DEFAULT NULL COMMENT '密码',
    `phone`        varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  DEFAULT NULL COMMENT '手机号码',
    `real_name`    varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  DEFAULT NULL COMMENT '真实姓名',
    `id_card`      varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  DEFAULT NULL COMMENT '身份证号',
    `email`        varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  DEFAULT NULL COMMENT '邮箱',
    `icon`         varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '头像',
    `status`       int                                                           DEFAULT '1' COMMENT '帐号启用状态:0->禁用；1->启用',
    `gender`       int                                                           DEFAULT NULL COMMENT '性别：0->未知；1->男；2->女',
    `gmt_create`   datetime                                                      DEFAULT NULL COMMENT '创建时间',
    `gmt_modified` datetime                                                      DEFAULT NULL COMMENT '修改时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci
  ROW_FORMAT = COMPACT COMMENT ='用户表';

INSERT INTO `sys_user`
VALUES ('1', 'admin', '$2a$10$QSyoNvrANb9dHlwwz0eWDuiySeKSLkqMM5FXqMfYYw/eo.6GMkjRm', '123123123123', NULL, NULL, NULL,
        NULL, 1, 1, now(), now());

DROP TABLE IF EXISTS sys_menu;
CREATE TABLE `sys_menu`
(
    `id`           char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'ID',
    `pid`          char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci          DEFAULT NULL COMMENT '父级ID',
    `title`        varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci      DEFAULT NULL COMMENT '菜单名称',
    `sort`         int                                                                DEFAULT NULL COMMENT '菜单排序',
    `name`         varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci      DEFAULT NULL COMMENT '前端名称',
    `path`         varchar(100)                                                       DEFAULT NULL COMMENT '前端路径',
    `icon`         varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci      DEFAULT NULL COMMENT '前端图标',
    `permission`   varchar(125) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci      DEFAULT NULL COMMENT '权限标识',
    `create`       bit(1)                                                    NOT NULL DEFAULT b'0' COMMENT '是否外链：0->否；1->是；',
    `type`         int                                                       NOT NULL COMMENT '权限类型：0->目录；1->菜单；2->按钮（接口绑定权限）',
    `component`    varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci      DEFAULT NULL COMMENT '前端资源路径',
    `hidden`       int                                                       NOT NULL DEFAULT '0' COMMENT '是否隐藏 0-否 1-是',
    `gmt_create`   datetime                                                           DEFAULT NULL COMMENT '创建时间',
    `gmt_modified` datetime                                                           DEFAULT NULL COMMENT '修改时间',
    PRIMARY KEY (`id`) USING BTREE,
    KEY `index_pid` (`pid`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci
  ROW_FORMAT = COMPACT COMMENT ='系统菜单表';

DROP TABLE IF EXISTS sys_role;
CREATE TABLE `sys_role`
(
    `id`           char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'ID',
    `name`         varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '名称',
    `description`  varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '描述',
    `status`       int                                                           DEFAULT '1' COMMENT '启用状态：0->禁用；1->启用',
    `sort`         int                                                           DEFAULT '0' COMMENT '排序',
    `gmt_create`   datetime                                                      DEFAULT NULL COMMENT '创建时间',
    `gmt_modified` datetime                                                      DEFAULT NULL COMMENT '修改时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci
  ROW_FORMAT = COMPACT COMMENT ='角色表';

INSERT INTO `sys_role`
VALUES ('1', '管理员', '拥有所有权限', 1, 0, now(), now());

DROP TABLE IF EXISTS sys_user_role;
CREATE TABLE `sys_user_role`
(
    `id`      char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'ID',
    `user_id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户id',
    `role_id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '角色id',
    PRIMARY KEY (`id`) USING BTREE,
    KEY `index_user_id` (`user_id`) USING BTREE,
    KEY `index_role_id` (`role_id`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci
  ROW_FORMAT = COMPACT COMMENT ='用户角色关系表';

INSERT INTO `sys_user_role`
VALUES ('1', '1', '1');

DROP TABLE IF EXISTS sys_role_menu;
CREATE TABLE `sys_role_menu`
(
    `id`      char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'ID',
    `role_id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '角色id',
    `menu_id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '菜单id',
    PRIMARY KEY (`id`) USING BTREE,
    KEY `index_role_id` (`role_id`) USING BTREE,
    KEY `index_menu_id` (`menu_id`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci
  ROW_FORMAT = COMPACT COMMENT ='角色菜单关系表';

-- generate_snowflake_id() 为自定义函数，需要先执行 /doc/layout/1 生成雪花算法  的sql文件
INSERT INTO `sys_role_menu` (`id`, `role_id`, `menu_id`)
VALUES (generate_snowflake_id(), '1', '1'),
       (generate_snowflake_id(), '1', '2'),
       (generate_snowflake_id(), '1', '3'),
       (generate_snowflake_id(), '1', '4'),
       (generate_snowflake_id(), '1', '5'),
       (generate_snowflake_id(), '1', '6'),
       (generate_snowflake_id(), '1', '7'),
       (generate_snowflake_id(), '1', '8'),
       (generate_snowflake_id(), '1', '9'),
       (generate_snowflake_id(), '1', '10'),
       (generate_snowflake_id(), '1', '11'),
       (generate_snowflake_id(), '1', '12'),
       (generate_snowflake_id(), '1', '13'),
       (generate_snowflake_id(), '1', '14'),
       (generate_snowflake_id(), '1', '15'),
       (generate_snowflake_id(), '1', '16'),
       (generate_snowflake_id(), '1', '17'),
       (generate_snowflake_id(), '1', '18'),
       (generate_snowflake_id(), '1', '20'),
       (generate_snowflake_id(), '1', '21'),
       (generate_snowflake_id(), '1', '22'),
       (generate_snowflake_id(), '1', '23'),
       (generate_snowflake_id(), '1', '24'),
       (generate_snowflake_id(), '1', '25'),
       (generate_snowflake_id(), '1', '26');

DROP TABLE IF EXISTS sys_file;
CREATE TABLE `sys_file`
(
    `id`                  char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci     NOT NULL COMMENT 'ID',
    `resource_id`         char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci     DEFAULT NULL COMMENT '来源id',
    `resource_code`       varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  DEFAULT NULL COMMENT '来源单号',
    `resource_details_id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci     DEFAULT NULL COMMENT '来源明细id',
    `resource_type`       varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  DEFAULT NULL COMMENT '来源类型',
    `fileType`            varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '文件类型',
    `type`                varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  DEFAULT NULL COMMENT '文件类型',
    `name`                varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '文件名称',
    `size`                double                                                        DEFAULT NULL COMMENT '文件大小(M)',
    `upload_man`          varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NOT NULL COMMENT '上传人',
    `path`                varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '文件路径',
    `gmt_create`          datetime                                                      DEFAULT NULL COMMENT '创建时间',
    `gmt_modified`        datetime                                                      DEFAULT NULL COMMENT '修改时间',
    PRIMARY KEY (`id`) USING BTREE,
    KEY `idx_resource_id` (`resource_id`) USING BTREE,
    KEY `idx_resource_code` (`resource_code`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci
  ROW_FORMAT = DYNAMIC COMMENT ='文件信息表';