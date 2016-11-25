-- Create table
create table UAOP_SERVICE_MONITOR_INFO
(
  ID                  VARCHAR2(32) not null,
  SERVICE_ID          VARCHAR2(32) not null,
  IP                  VARCHAR2(32) not null,
  PORT                VARCHAR2(6) not null,
  SUCCESS             NUMBER(10) not null,
  ELAPSED_ALL         NUMBER(20) not null,
  ELAPSED_AVG         NUMBER(10),
  MAX_TIME            NUMBER(10),
  MIN_TIME            NUMBER(10),
  FAULURE             NUMBER(10),
  STATUS              NUMBER(3) not null,
  CREATE_ACCOUNT      VARCHAR2(128) not null,
  CREATE_TIME         NUMBER(20) not null,
  LAST_MODIFY_TIME    NUMBER(20),
  LAST_MODIFY_ACCOUNT VARCHAR2(128),
  LAST_TERMINAL       VARCHAR2(128),
  LAST_SYS            VARCHAR2(32)
);
-- Add comments to the columns 
comment on column UAOP_SERVICE_MONITOR_INFO.SERVICE_ID
  is '对应service_info表的id';
comment on column UAOP_SERVICE_MONITOR_INFO.IP
  is '服务部署的主机IP';
comment on column UAOP_SERVICE_MONITOR_INFO.PORT
  is '端口';
comment on column UAOP_SERVICE_MONITOR_INFO.SUCCESS
  is '成功次数';
comment on column UAOP_SERVICE_MONITOR_INFO.ELAPSED_ALL
  is '成功的总耗时';
comment on column UAOP_SERVICE_MONITOR_INFO.ELAPSED_AVG
  is '成功的平均耗时';
comment on column UAOP_SERVICE_MONITOR_INFO.MAX_TIME
  is '最慢';
comment on column UAOP_SERVICE_MONITOR_INFO.MIN_TIME
  is '最快';
comment on column UAOP_SERVICE_MONITOR_INFO.FAULURE
  is '失败次数';
