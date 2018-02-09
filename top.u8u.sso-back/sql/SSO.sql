create user 'SSO'@'localhost' identified by 'ijksi289xjs82is9w8cl9nJ8-a9vjksa';
grant all on SSO.* to 'SSO'@'localhost';
flush privileges;


create table `USER`(
           ID bigint not null auto_increment,
           DEL_FLAG tinyint not null default 0 COMMENT '删除标志',
           LOGIN_NAME varchar(256) COMMENT '登录名',
           PASSWORD varchar(256) COMMENT '密码',
           primary key(ID)
)ENGINE=InnoDB,COMMENT '用户列表';
