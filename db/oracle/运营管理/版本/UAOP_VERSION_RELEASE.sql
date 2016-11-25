-- Create table
create table UAOP_VERSION_RELEASE
(
  ID                  VARCHAR2(32) not null,
  PRODUCT_NAME        VARCHAR2(100) not null,
  PRODUCT             VARCHAR2(100) not null,
  VERSION_NUMBER      VARCHAR2(100) not null,
  VERSION_FEATURE     VARCHAR2(2000),
  FILE_NAME           VARCHAR2(100) not null,
  FILE_PATH           VARCHAR2(2000) not null,
  VALIDATE_STR        VARCHAR2(50) not null,
  OPEN_FLAG           VARCHAR2(10) default '0' not null,
  STATUS              NUMBER(3) not null,
  CREATE_ACCOUNT      VARCHAR2(128) not null,
  CREATE_TIME         NUMBER(20) not null,
  LAST_MODIFY_TIME    NUMBER(20),
  LAST_MODIFY_ACCOUNT VARCHAR2(128),
  LAST_TERMINAL       VARCHAR2(128),
  LAST_SYS            VARCHAR2(32)
);
-- Add comments to the columns 
comment on column UAOP_VERSION_RELEASE.ID
  is '主键';
comment on column UAOP_VERSION_RELEASE.PRODUCT_NAME
  is '产品名称';
comment on column UAOP_VERSION_RELEASE.PRODUCT
  is '产品缩写';
comment on column UAOP_VERSION_RELEASE.VERSION_NUMBER
  is '版本号';
comment on column UAOP_VERSION_RELEASE.VERSION_FEATURE
  is '版本说明';
comment on column UAOP_VERSION_RELEASE.FILE_NAME
  is '程序文件名';
comment on column UAOP_VERSION_RELEASE.FILE_PATH
  is '程序安装包路径';
comment on column UAOP_VERSION_RELEASE.VALIDATE_STR
  is '程序md5';
comment on column UAOP_VERSION_RELEASE.OPEN_FLAG
  is '是否可用 1-可用 0-不可用';
