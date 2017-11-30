create user 'MY_BLOG'@'localhost' identified by 'ao92uoql729xj6Duiojsy678-ij82lxf';
grant all on MY_BLOG.* to 'MY_BLOG'@'localhost';
flush privileges;


create table BLOG_CONTENT(
           ID bigint not null auto_increment,
           DEL_FLAG tinyint not null default 0 COMMENT '删除标志',
           DATE DATETIME COMMENT '发表日期',
           DIGEST varchar(256) COMMENT '简介',
           ORIGINAL tinyint COMMENT '是否原创',
           TEMPLATE_ID bigint COMMENT '是否可见',
           CONTENT_ID bigint COMMENT '是否可见',
           TITLE varchar(256) COMMENT '标题',
           DATE_ID bigint COMMENT '日期分类',
           TYPE_ID bigint COMMENT '文章分类',
           KEY_WORD varchar(256) COMMENT '文章搜索关键字',
           primary key(ID)
)ENGINE=InnoDB,COMMENT '文章列表';

create table BLOG_CONTENT_RELEASE(
           ID bigint not null auto_increment,
           DEL_FLAG tinyint not null default 0 COMMENT '删除标志',
           C_TIME DATETIME COMMENT '发表日期',
           DIGEST varchar(256) COMMENT '简介',
           ORIGINAL tinyint COMMENT '是否原创',
           READER_COUNT int(32) COMMENT '阅读量',
           TITLE varchar(256) COMMENT '标题',
           DATE_ID bigint COMMENT '日期分类',
           TYPE_ID bigint COMMENT '文章分类',
           BLOG_CONTENT_ID bigint COMMENT '文章分类',
           HTML_CONTENT_ID bigint COMMENT '文章分类',
           KEY_WORD varchar(256) COMMENT '文章搜索关键字',
           URI varchar(256) COMMENT '文章搜索关键字',
           primary key(ID)
)ENGINE=InnoDB,COMMENT '文章列表';

create table BLOG_TYPE(
           ID bigint not null auto_increment,
           DEL_FLAG tinyint not null default 0 COMMENT '删除标志',
           NAME varchar(256) COMMENT '分类名称',
           primary key(ID)
)ENGINE=InnoDB,COMMENT '文章分类';

create table DATE_TYPE(
           ID bigint not null auto_increment,
           DEL_FLAG tinyint not null default 0 COMMENT '删除标志',
           DATE DATETIME COMMENT '日期',
           primary key(ID)
)ENGINE=InnoDB,COMMENT '日期分类';

create table CONTACT_MESSAGE(
           ID bigint not null auto_increment,
           DEL_FLAG tinyint not null default 0 COMMENT '删除标志',
           `READ` tinyint COMMENT '是否已阅读',
           TITLE varchar(256) COMMENT '标题',
           EMAIL varchar(256) COMMENT '电子邮箱',
           NAME varchar(256) COMMENT '人名',
           CONTENT varchar(2000) COMMENT '内容',
           primary key(ID)
)ENGINE=InnoDB,COMMENT '留言列表';

create table COMMENT(
           ID bigint not null auto_increment,
           P_ID bigint  default 0,
           DEL_FLAG tinyint not null default 0 COMMENT '删除标志',
           BLOG_CONTENT_ID bigint COMMENT '文章ID',
           NAME varchar(128) COMMENT '评论者',
           CONTENT varchar(2048) COMMENT '评论内容',
           DATE DATETIME COMMENT '评论日期',
           primary key(ID)
)ENGINE=InnoDB,COMMENT '评论列表';

