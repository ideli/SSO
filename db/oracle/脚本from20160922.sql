-- Add/modify columns 
alter table UAOP_SYS_DICT add PARENT_ROOT_KEY VARCHAR2(50);
alter table UAOP_SYS_DICT add TYPE VARCHAR2(2);
-- Add comments to the columns 
comment on column UAOP_SYS_DICT.PARENT_ROOT_KEY
  is '���ڵ�����';
comment on column UAOP_SYS_DICT.TYPE
  is '0����,1Ŀ¼';


