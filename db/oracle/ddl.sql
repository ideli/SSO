-- DROP Table UAOP_SYSUSER;
CREATE TABLE UAOP_SYSUSER
(
  ACCOUNT            VARCHAR(128) NOT NULL,   
  USER_NAME          VARCHAR(128)  NOT NULL,   
  PASS               VARCHAR(128) NOT NULL,
  USER_ID            VARCHAR(128),
  USER_TYPE          NUMERIC(3,0) NOT NULL,
  STATUS             NUMERIC(3,0) NOT NULL,
  CREATE_ACCOUNT     VARCHAR(128) NOT NULL,
  CREATE_TIME        NUMERIC(20,0) NOT NULL, 
  LAST_MODIFY_TIME   NUMERIC(20,0), 
  LAST_MODIFY_ACCOUNT     VARCHAR(128),
  LAST_TERMINAL      VARCHAR(128),
  LAST_SYS           VARCHAR(32),
  PRIMARY KEY(ACCOUNT)
);

-- DROP Table UAOP_SYSUSER_ROLE;
CREATE TABLE UAOP_SYSUSER_ROLE
(
   ROLE_ID     VARCHAR(32) NOT NULL,
   ACCOUNT     VARCHAR(128) NOT NULL,
   PRIMARY KEY(ACCOUNT,ROLE_ID)
);


-- DROP Table UAOP_ROLE;
CREATE TABLE UAOP_ROLE
(
  ROLE_ID           VARCHAR(32) NOT NULL,
  ROLE_NAME         VARCHAR(128) NOT NULL,
  SUPER_ID          VARCHAR(32),
  TYPE              NUMERIC(3,0) NOT NULL,
  NOTE              VARCHAR(1024),
  STATUS             NUMERIC(3,0) NOT NULL,
  CREATE_ACCOUNT     VARCHAR(128) NOT NULL,
  CREATE_TIME        NUMERIC(20,0) NOT NULL, 
  LAST_MODIFY_TIME   NUMERIC(20,0), 
  LAST_MODIFY_ACCOUNT     VARCHAR(128),
  LAST_TERMINAL      VARCHAR(128),
  LAST_SYS           VARCHAR(32),
  PRIMARY KEY(ROLE_ID)
);

-- DROP Table UAOP_ROLE_RESOURCE;
CREATE TABLE UAOP_ROLE_RESOURCE
(
   ROLE_ID           VARCHAR(32) NOT NULL,
   RESOURCE_ID       VARCHAR(128) NOT NULL,
   RESOURCE_TYPE     NUMERIC(3,0) NOT NULL,
   PRIVILEGE_VALUE   NUMERIC(20,0),
   SYSTEM_ID         VARCHAR(32) NOT NULL,
   PRIMARY KEY(ROLE_ID,RESOURCE_ID,SYSTEM_ID)
);

-- DROP Table UAOP_MENU_RESOURCE;
CREATE TABLE UAOP_MENU_RESOURCE
(
  SYSTEM_ID       VARCHAR(32) NOT NULL,
  RESOURCE_ID     VARCHAR(256) NOT NULL,
  RESOURCE_NAME   VARCHAR(512),
  SUPER_ID        VARCHAR(256),
  URL             VARCHAR(2048),
  ICON            VARCHAR(256),
  VISIBLE_STATE   NUMERIC(3,0), 
  ORDER_NUM       NUMERIC(5,0), 
  NOTE            VARCHAR(1024),
  PRIMARY KEY(RESOURCE_ID,SYSTEM_ID)
);


-- DROP Table UAOP_SERVICE_RESOURCE;
CREATE TABLE UAOP_SERVICE_RESOURCE
(
  SYSTEM_ID       VARCHAR(32) NOT NULL,
  RESOURCE_ID     VARCHAR(256) NOT NULL,
  SERVICE_NAME    VARCHAR(512),
  SUPER_ID        VARCHAR(256),
  URL             VARCHAR(2048),
  ICON            VARCHAR(256),
  VISIBLE_STATE   NUMERIC(3,0), 
  ORDER_NUM       NUMERIC(5,0), 
  INTERFACE       VARCHAR(256),
  NOTE            VARCHAR(1024),
  PRIMARY KEY(RESOURCE_ID,SYSTEM_ID)
);



-- DROP Table UAOP_USER_INFO;
CREATE TABLE UAOP_USER_INFO
(
  USER_ID            VARCHAR(128) NOT NULL,
  USER_NAME          VARCHAR(128)  NOT NULL,   
  SEX                NUMERIC(3,0)  NOT NULL,
  CID                VARCHAR(20)   NOT NULL,
  IS_POLICE          NUMERIC(3,0),
  POLICE_ID          VARCHAR(128)  NOT NULL,
  CONTACT            VARCHAR(256),
  AVATAR             VARCHAR(128),
  POST               VARCHAR(64),
  BIRTH              NUMERIC(20,0), 
  POLI               VARCHAR(64),
  PHONE              VARCHAR(32),
  FAX                VARCHAR(32),
  ADDRESS            VARCHAR(256),
  ZIPCODE            VARCHAR(32),
  PROVINCE           VARCHAR(64),
  CITY               VARCHAR(64),
  COUNTY             VARCHAR(64),
  EXT_STR1           VARCHAR(256),
  EXT_STR2           VARCHAR(256),
  EXT_STR3           VARCHAR(256),
  STATUS             NUMERIC(3,0) NOT NULL,
  CREATE_ACCOUNT     VARCHAR(128) NOT NULL,
  CREATE_TIME        NUMERIC(20,0) NOT NULL, 
  LAST_MODIFY_TIME   NUMERIC(20,0), 
  LAST_MODIFY_ACCOUNT     VARCHAR(128),
  LAST_TERMINAL      VARCHAR(128),
  LAST_SYS           VARCHAR(32),
  PRIMARY KEY(USER_ID)
);


-- DROP Table UAOP_USER_ORGANIZATION;
CREATE TABLE UAOP_USER_ORGANIZATION
(
    USER_ID      VARCHAR(128) NOT NULL,
    ORG_ID       VARCHAR(128) NOT NULL,
    TYPE		 NUMERIC(3,0)  NOT NULL,
    ADMIN        NUMERIC(3,0),
    PRIMARY KEY(USER_ID,ORG_ID)
);

-- DROP Table UAOP_ORGANIZATION;
CREATE TABLE UAOP_ORGANIZATION
(
    ORG_ID       VARCHAR(128)  NOT NULL,
    ORG_CODE     VARCHAR(128)  NOT NULL,
    ORG_NAME     VARCHAR(128)  NOT NULL,
    SUPER_ID     VARCHAR(128),
  	SYSTEM_ID    VARCHAR(32) NOT NULL,
	TYPE     	 NUMERIC(10),
	NOTE         VARCHAR(512),
	PINYIN       VARCHAR(128),
	FIRST_LETTER      VARCHAR(64),
	STATUS             NUMERIC(3,0) NOT NULL,
    CREATE_ACCOUNT     VARCHAR(128) NOT NULL,
    CREATE_TIME        NUMERIC(20,0) NOT NULL, 
    LAST_MODIFY_TIME   NUMERIC(20,0), 
    LAST_MODIFY_ACCOUNT     VARCHAR(128),
    LAST_TERMINAL      VARCHAR(128),
    LAST_SYS           VARCHAR(32),
    PRIMARY KEY(ORG_ID)
);


-- DROP Table UAOP_SYSPARAM;
CREATE TABLE UAOP_SYSPARAM
(
    SYSTEM_ID            VARCHAR(32) NOT NULL,
    PARAM_TYPE           VARCHAR(32)  NOT NULL,
    PARAM_NAME           VARCHAR(128)  NOT NULL,
  	PARAM_VALUE          VARCHAR(512),
	CREATE_USER          VARCHAR(128),
	CREATE_TIME          NUMERIC(20,0),
	LAST_MODIFY_TIME     NUMERIC(20,0),
	EXT_STR1             VARCHAR(128),
	EXT_STR2             VARCHAR(128),
	EXT_STR3             VARCHAR(128),
    PRIMARY KEY(SYSTEM_ID,PARAM_TYPE,PARAM_NAME)
);


-- Create table
create table UAOP_LOG_TOKEN
(
  TOKEN          VARCHAR2(32) not null,
  ACCOUNT        VARCHAR2(128) not null,
  SYS_CODE       VARCHAR2(32),
  TOKEN_TIME     VARCHAR2(20),
  RANDOM_VAL     VARCHAR2(10),
  EFFECTIVE_TIME NUMBER,
  INVALID_TIME   NUMBER,
  CREATE_DATE    DATE,
  USER_ID        VARCHAR2(128)
);
-- Add comments to the table 
comment on table UAOP_LOG_TOKEN
  is '口令日志表';
-- Add comments to the columns 
comment on column UAOP_LOG_TOKEN.TOKEN
  is '固定32位';
comment on column UAOP_LOG_TOKEN.ACCOUNT
  is '用户账号';
comment on column UAOP_LOG_TOKEN.SYS_CODE
  is '系统编码';
comment on column UAOP_LOG_TOKEN.TOKEN_TIME
  is '日期(yyyyMMddHHmmss)14位日期';
comment on column UAOP_LOG_TOKEN.RANDOM_VAL
  is '6位随机数';
comment on column UAOP_LOG_TOKEN.EFFECTIVE_TIME
  is '令牌生效时间(长整形)';
comment on column UAOP_LOG_TOKEN.INVALID_TIME
  is '令牌失效时间(长整形)';
comment on column UAOP_LOG_TOKEN.CREATE_DATE
  is '创建时间';
comment on column UAOP_LOG_TOKEN.USER_ID
  is '用户ID';
-- Create/Recreate primary, unique and foreign key constraints 
alter table UAOP_LOG_TOKEN
  add constraint PK_UAOP_LOG_TOKEN primary key (TOKEN);


-- DROP Table UAOP_SYS_DICT;
create table UAOP_SYS_DICT
(
  ID              VARCHAR(32) not null,
  SYS_CODE        VARCHAR(32),
  DICT_LEVEL      VARCHAR(4) not null,
  DICT_KEY        VARCHAR(50) not null,
  PARENT_KEY      VARCHAR(50),
  ROOT_KEY        VARCHAR(50),
  DICT_VALUE1     VARCHAR(200) not null,
  DICT_VALUE2     VARCHAR(200),
  DICT_VALUE3     VARCHAR(200),
  LEAF_FLAG       VARCHAR(4),
  DOWNLOAD_FLAG   VARCHAR(4),
  READONLY_FLAG   VARCHAR(4) not null,
  DICT_SORT       NUMERIC(4,0),
  DICT_PY         VARCHAR(50),
  OPEN_FLAG       VARCHAR(4) not null,
  REMARK          VARCHAR(4000),
  CREATE_USER     VARCHAR(50) not null,
  CREATE_DATETIME NUMERIC(20,0) not null,
  UPDATE_USER     VARCHAR(50),
  UPDATE_DATETIME NUMERIC(20,0),
  PARENT_ROOT_KEY VARCHAR(50),
  TYPE            VARCHAR(2),
  PRIMARY KEY(ID)
);


-- DROP Table UAOP_LOG_RECORD;
CREATE TABLE UAOP_LOG_RECORD
(
    ID                   NUMERIC(20,0),
    SYSTEM_ID            VARCHAR(32) NOT NULL,
    ACCOUNT              VARCHAR(128) NOT NULL,   
    USER_NAME            VARCHAR(128),   
  	TERMINAL_ID          VARCHAR(128),
	MODULE_ID            VARCHAR(128),
	OPERATE_ID           VARCHAR(128),
	OPERATE_TIME         NUMERIC(20,0),
	OPERATE_RESULT       NUMERIC(3,0),
	NOTE                 VARCHAR(1024),
	PRIMARY KEY(ID)
);


-- 增加是否启用状态
ALTER TABLE UAOP_SYSUSER add ACTIVE_STATUS NUMERIC(3,0) DEFAULT 1;

-- 增加菜单类型
ALTER TABLE UAOP_MENU_RESOURCE add MENU_TYPE NUMERIC(3,0)  DEFAULT 0;

-- 角色增加系统标志
ALTER TABLE UAOP_ROLE add SYSTEM_ID  VARCHAR(32);
ALTER TABLE UAOP_ROLE add ACTIVE_STATUS NUMERIC(3,0) DEFAULT 1;

-- 菜单增加英文名称
ALTER TABLE UAOP_MENU_RESOURCE add RESOURCE_EN_NAME   VARCHAR(512);

-- 菜单类型默认值0
ALTER TABLE UAOP_MENU_RESOURCE modify MENU_TYPE NUMERIC(3,0)  DEFAULT 0;