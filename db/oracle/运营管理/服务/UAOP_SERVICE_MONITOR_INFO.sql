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
  is '��Ӧservice_info���id';
comment on column UAOP_SERVICE_MONITOR_INFO.IP
  is '�����������IP';
comment on column UAOP_SERVICE_MONITOR_INFO.PORT
  is '�˿�';
comment on column UAOP_SERVICE_MONITOR_INFO.SUCCESS
  is '�ɹ�����';
comment on column UAOP_SERVICE_MONITOR_INFO.ELAPSED_ALL
  is '�ɹ����ܺ�ʱ';
comment on column UAOP_SERVICE_MONITOR_INFO.ELAPSED_AVG
  is '�ɹ���ƽ����ʱ';
comment on column UAOP_SERVICE_MONITOR_INFO.MAX_TIME
  is '����';
comment on column UAOP_SERVICE_MONITOR_INFO.MIN_TIME
  is '���';
comment on column UAOP_SERVICE_MONITOR_INFO.FAULURE
  is 'ʧ�ܴ���';
