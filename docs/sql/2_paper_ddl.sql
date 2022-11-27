-- 日报：工作、项目等
create table journey
(
    id          bigint                 not null auto_increment comment '主键',
    data_date   date                   not null comment '日期（年月日）',
    summary     varchar(2048)          not null comment '概要',
    expected    varchar(2048)          null comment '预期',
    actual      varchar(2048)          null comment '实际',
    remark      varchar(2048)          null comment '备注',
    del_flag    tinyint  default 0     not null comment '删除标识：0正常；1删除；',
    create_time datetime default now() not null comment '创建日期',
    update_time datetime default now() not null comment '修改时间',
    constraint pk_journey
        primary key (id)
) comment '日报：工作、项目等';

create index journey_date
    on journey (data_date desc);
