
--redis配置
insert into uaop_sysparam (SYSTEM_ID, PARAM_TYPE, PARAM_NAME, PARAM_VALUE, CREATE_USER, CREATE_TIME, LAST_MODIFY_TIME, EXT_STR1, EXT_STR2, EXT_STR3)
values ('UAOP', 'REDIS', 'redis_host', '172.16.0.112', 'Test', 1466993719222, 1466994131586, '', '', '');
insert into uaop_sysparam (SYSTEM_ID, PARAM_TYPE, PARAM_NAME, PARAM_VALUE, CREATE_USER, CREATE_TIME, LAST_MODIFY_TIME, EXT_STR1, EXT_STR2, EXT_STR3)
values ('UAOP', 'REDIS', 'redis_port', '6379', 'Test', 1466993719222, 1466994131586, '', '', '');
insert into uaop_sysparam (SYSTEM_ID, PARAM_TYPE, PARAM_NAME, PARAM_VALUE, CREATE_USER, CREATE_TIME, LAST_MODIFY_TIME, EXT_STR1, EXT_STR2, EXT_STR3)
values ('UAOP', 'REDIS', 'redis_expire', '1800', 'Test', 1466993719222, 1466994131586, '', '', '');

--单点登录地址
insert into uaop_sysparam (SYSTEM_ID, PARAM_TYPE, PARAM_NAME, PARAM_VALUE, CREATE_USER, CREATE_TIME, LAST_MODIFY_TIME, EXT_STR1, EXT_STR2, EXT_STR3)
values ('UAOP', 'SSO', 'LOGIN_URL', 'http://192.168.40.30:9090/sso/login.html', 'Test', 1466993719222, 1466994131586, '', '', '');

--REST端口
insert into uaop_sysparam (SYSTEM_ID, PARAM_TYPE, PARAM_NAME, PARAM_VALUE, CREATE_USER, CREATE_TIME, LAST_MODIFY_TIME, EXT_STR1, EXT_STR2, EXT_STR3)
values ('UAOP', 'REST', 'rest_port', '8811', 'Test', 1466993719222, 1466994131586, '', '', '');

--DUBBO注册中心
insert into uaop_sysparam (SYSTEM_ID, PARAM_TYPE, PARAM_NAME, PARAM_VALUE, CREATE_USER, CREATE_TIME, LAST_MODIFY_TIME, EXT_STR1, EXT_STR2, EXT_STR3)
values ('UAOP', 'DUBBO', 'registry_address', 'zookeeper://172.16.0.114:52181', 'Test', 1466993719222, 1466994131586, '', '', '');

--REST-log端口
insert into uaop_sysparam (SYSTEM_ID, PARAM_TYPE, PARAM_NAME, PARAM_VALUE, CREATE_USER, CREATE_TIME, LAST_MODIFY_TIME, EXT_STR1, EXT_STR2, EXT_STR3)
values ('UAOP', 'REST', 'rest_log_port', '8812', 'Test', 1466993719222, 1466994131586, '', '', '');

commit;




