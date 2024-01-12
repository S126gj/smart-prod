DELETE FROM `sys_layout` WHERE ID IN (4, 5, 6);
DELETE FROM `sys_layout_d` WHERE LAYOUT_ID IN (4, 5, 6);

--  用户管理上部 --
INSERT INTO `sys_layout` VALUES ('4', '用户管理上部');
INSERT INTO `sys_layout_d` VALUES(generate_snowflake_id(), '4', 0, 'blurry', '搜索框', 6, 'input', true, false, 'form', null, null, 1, null, true);
INSERT INTO `sys_layout_d` VALUES(generate_snowflake_id(), '4', 1, 'dateRange', '选择日期', 6, 'daterange', true, false, 'form', null, null, 1, null, true);
INSERT INTO `sys_layout_d` VALUES(generate_snowflake_id(), '4', 2, 'enable', '是否启用', 6, 'select', true, false, 'form', null, null, 1, null, true);

--  用户管理下部 --
INSERT INTO `sys_layout` VALUES ('5', '用户管理下部');
INSERT INTO `sys_layout_d` VALUES(generate_snowflake_id(), '5', 0, 'username', '用户名', 100, 'text', true, true, 'table', null, null, 1, null, true);
INSERT INTO `sys_layout_d` VALUES(generate_snowflake_id(), '5', 1, 'roleList', '角色', 100, 'text', true, true, 'table', null, null, 1, null, true);
INSERT INTO `sys_layout_d` VALUES(generate_snowflake_id(), '5', 2, 'realName', '姓名', 100, 'text', true, true, 'table', null, null, 1, null, true);
INSERT INTO `sys_layout_d` VALUES(generate_snowflake_id(), '5', 3, 'phone', '手机号', 100, 'text', true, true, 'table', null, null, 1, null, true);
INSERT INTO `sys_layout_d` VALUES(generate_snowflake_id(), '5', 4, 'idCard', '身份证', 100, 'text', true, true, 'table', null, null, 1, null, true);
INSERT INTO `sys_layout_d` VALUES(generate_snowflake_id(), '5', 5, 'email', '邮箱', 100, 'text', true, true, 'table', null, null, 1, null, true);
INSERT INTO `sys_layout_d` VALUES(generate_snowflake_id(), '5', 6, 'genderDesc', '性别', 100, 'text', true, true, 'table', null, null, 1, null, true);
INSERT INTO `sys_layout_d` VALUES(generate_snowflake_id(), '5', 7, 'statusDesc', '状态', 100, 'text', true, true, 'table', null, null, 1, null, true);

--  用户管理详情 --
INSERT INTO `sys_layout` VALUES ('6', '用户管理详情');
INSERT INTO `sys_layout_d` VALUES(generate_snowflake_id(), '6', 0, 'username', '用户名', 100, 'input', true, false, 'table', null, null, 1, null, true);
INSERT INTO `sys_layout_d` VALUES(generate_snowflake_id(), '6', 1, 'phone', '手机号', 100, 'input', true, false, 'table', null, null, 1, null, true);
INSERT INTO `sys_layout_d` VALUES(generate_snowflake_id(), '6', 2, 'gender', '性别', 100, 'select', true, false, 'table', null, null, 1, null, true);
INSERT INTO `sys_layout_d` VALUES(generate_snowflake_id(), '6', 3, 'idCard', '身份证', 100, 'input', true, false, 'table', null, null, 1, null, true);
INSERT INTO `sys_layout_d` VALUES(generate_snowflake_id(), '6', 4, 'realName', '真实姓名', 100, 'input', true, false, 'table', null, null, 1, null, true);
INSERT INTO `sys_layout_d` VALUES(generate_snowflake_id(), '6', 5, 'email', '邮箱', 100, 'input', true, false, 'table', null, null, 1, null, true);
INSERT INTO `sys_layout_d` VALUES(generate_snowflake_id(), '6', 6, 'icon', '头像', 100, 'upload', true, false, 'table', null, null, 1, null, true);
INSERT INTO `sys_layout_d` VALUES(generate_snowflake_id(), '6', 7, 'roleList', '角色', 100, 'multi-select', true, false, 'table', '/combo/layout/getRole', null, 1, null, true);