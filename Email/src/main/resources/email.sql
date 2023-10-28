drop table if exists sys_email_outward;
create table if not exists sys_email_outward
(
    email_id            varchar(128)not null comment '邮件ID号 包含邮件创建日期字段用UUID生成' primary key,
    email_sender        varchar(30) not null comment '发送者邮箱地址',
    email_sender_passwd varchar(30) not null comment '发送者邮箱密码',
    email_receiver      varchar(30) not null comment '收件人邮箱地址',
    email_create_time   timestamp   not null comment '邮件创建日期 ',
    email_sender_time   timestamp   not null comment '邮件发送日期',
    email_title         varchar(120)not null comment '邮件标题',
    email_text_body     text        not null comment '邮件正文内容 或者可以这里存储一个id 使用es搜索引擎存储文本数据',
    email_size          bigint      not null comment '邮件大小 单位Byte',
    email_smtp          varchar(40) not null comment '发送邮件所用SMTP服务器',
    email_encryption_type varchar(50) not null comment '邮件加密类型',
    foreign key (email_sender) references sys_user(user_sys_email)

    ) charset = UTF8 engine = innodb comment '邮件表';