create table CONTENT(
           ID bigint not null auto_increment,
           DEL_FLAG tinyint not null default 0 COMMENT '删除标志',
           C_TIME DATETIME COMMENT '分类名称',
           URI VARCHAR(1024) COMMENT '分类名称',
           OSS_KEY VARCHAR(512) COMMENT '分类名称',
           FILE_NAME VARCHAR(512) COMMENT '分类名称',
           URI_FLAG tinyint COMMENT '分类名称',
           primary key(ID)
)ENGINE=InnoDB,COMMENT '存储表';