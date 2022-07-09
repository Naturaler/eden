create table antidote(
    id bigint not null auto_increment comment '主键',
    title varchar(64) not null comment '标题',
    `key` varchar(256) not null comment 'key',
    val varchar(1024) not null comment 'value',
    remark varchar(2048) not null comment '备注',
    create_time datetime not null default now() comment '创建时间',
    update_time datetime not null default now() comment '修改时间',
    constraint primary key PK_ANTIDOTE(id)
);