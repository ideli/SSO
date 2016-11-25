-- Create table
create table UAOP_SERVICE_INFO
(
  ID                  VARCHAR2(32) not null,
  SERVICE_NAME        VARCHAR2(256) not null,
  SERVICE_DESC        VARCHAR2(2000),
  PROTOCOL            VARCHAR2(20) not null,
  INTERFACE           VARCHAR2(256) not null,
  METHOD              VARCHAR2(64) not null,
  URL                 VARCHAR2(64),
  OPEN_FLAG           VARCHAR2(1) not null,
  STATUS              NUMBER(3) not null,
  CREATE_ACCOUNT      VARCHAR2(128) not null,
  CREATE_TIME         NUMBER(20) not null,
  LAST_MODIFY_TIME    NUMBER(20),
  LAST_MODIFY_ACCOUNT VARCHAR2(128),
  LAST_TERMINAL       VARCHAR2(128),
  LAST_SYS            VARCHAR2(32)
);
-- Add comments to the columns 
comment on column UAOP_SERVICE_INFO.SERVICE_NAME
  is '��������';
comment on column UAOP_SERVICE_INFO.SERVICE_DESC
  is '��������';
comment on column UAOP_SERVICE_INFO.PROTOCOL
  is 'Э��';
comment on column UAOP_SERVICE_INFO.INTERFACE
  is '�ӿ�';
comment on column UAOP_SERVICE_INFO.METHOD
  is '����';
comment on column UAOP_SERVICE_INFO.URL
  is 'REST�����URL';
comment on column UAOP_SERVICE_INFO.OPEN_FLAG
  is '�Ƿ�����,1����,0��ֹ����';
comment on column UAOP_SERVICE_INFO.STATUS
  is '�߼�ɾ�����,0��Ч';
