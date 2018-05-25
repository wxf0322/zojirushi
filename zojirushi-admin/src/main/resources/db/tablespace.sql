-- 创建临时表空间
CREATE TEMPORARY TABLESPACE zojirushi_temp
TEMPFILE '/u01/app/oracle/oradata/zojirushi_temp.dbf'
SIZE 32m
AUTOEXTEND ON
NEXT 32m MAXSIZE UNLIMITED
EXTENT MANAGEMENT LOCAL;

-- 创建表空间
CREATE TABLESPACE zojirushi_data
LOGGING
  DATAFILE '/u01/app/oracle/oradata/zojirushi_data.dbf'
SIZE 32m
AUTOEXTEND ON
NEXT 32m MAXSIZE UNLIMITED
EXTENT MANAGEMENT LOCAL;

-- 创建用户
CREATE USER zojirushi IDENTIFIED BY zojirushi
ACCOUNT UNLOCK
DEFAULT TABLESPACE zojirushi_data
TEMPORARY TABLESPACE zojirushi_temp;

-- 授权限
GRANT CONNECT, RESOURCE TO zojirushi;
GRANT unlimited tablespace TO zojirushi;