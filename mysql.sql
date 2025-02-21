drop database if exists his_clinic;
create database his_clinic;
use his_clinic;
create table medical_record
(
    id            bigint primary key auto_increment,
    register_id   bigint not null,
    readme        varchar(512),
    present       varchar(512),
    present_treat varchar(512),
    history       varchar(512),
    allergy       varchar(512),
    physique      varchar(512),
    proposal      varchar(512),
    careful       varchar(512),
    diagnosis     varchar(512),
    cure          varchar(512)
);
create table undo_log
(
    id            serial primary key,
    branch_id     bigint       not null,
    xid           varchar(100) not null,
    context       varchar(128) not null,
    rollback_info longblob     not null,
    log_status    int          not null,
    log_created   timestamp    not null,
    log_modified  timestamp    not null,
    ext           varchar(100) default null,
    unique (xid, branch_id)
);
drop database if exists his_inspect;
create database his_inspect;
use his_inspect;
create table inspect
(
    id    bigint primary key auto_increment,
    code  varchar(32)    not null,
    name  varchar(32)    not null,
    money numeric(16, 2) not null default 0
);
create table record
(
    id       bigint primary key auto_increment,
    inspect  bigint not null,
    register bigint not null,
    state    char(3) default '已安排',
    result   varchar(64),
    foreign key (inspect) references inspect (id)
);
create table undo_log
(
    id            serial primary key,
    branch_id     bigint       not null,
    xid           varchar(100) not null,
    context       varchar(128) not null,
    rollback_info longblob     not null,
    log_status    int          not null,
    log_created   timestamp    not null,
    log_modified  timestamp    not null,
    ext           varchar(100) default null,
    unique (xid, branch_id)
);
drop database if exists his_dispose;
create database his_dispose;
use his_dispose;
create table dispose
(
    id    bigint primary key auto_increment,
    code  varchar(32)    not null,
    name  varchar(32)    not null,
    money numeric(16, 2) not null default 0
);
create table record
(
    id       bigint primary key auto_increment,
    dispose  bigint not null,
    register bigint not null,
    state    char(3) default '已安排',
    result   varchar(64),
    foreign key (dispose) references dispose (id)
);
create table undo_log
(
    id            serial primary key,
    branch_id     bigint       not null,
    xid           varchar(100) not null,
    context       varchar(128) not null,
    rollback_info longblob     not null,
    log_status    int          not null,
    log_created   timestamp    not null,
    log_modified  timestamp    not null,
    ext           varchar(100) default null,
    unique (xid, branch_id)
);