drop schema if exists his_authorization cascade;
create schema his_authorization;
set
search_path = his_authorization;
create table users
(
    username  char(8) primary key,
    password  char(60)    not null,
    authority varchar(32) not null
);
insert into users (username, password, authority)
values ('00000000', '$2a$10$5akX3lQH0qvX5.C11AAVy.dh/Jxff4/0zRiEc/P3niTtvrkoDJWd2', 'ROLE_ADMIN'),
       ('10000000', '$2a$10$5akX3lQH0qvX5.C11AAVy.dh/Jxff4/0zRiEc/P3niTtvrkoDJWd2', 'ROLE_REGISTRATION'),
       ('10000001', '$2a$10$5akX3lQH0qvX5.C11AAVy.dh/Jxff4/0zRiEc/P3niTtvrkoDJWd2', 'ROLE_REGISTRATION'),
       ('10000002', '$2a$10$5akX3lQH0qvX5.C11AAVy.dh/Jxff4/0zRiEc/P3niTtvrkoDJWd2', 'ROLE_REGISTRATION'),
       ('10000003', '$2a$10$5akX3lQH0qvX5.C11AAVy.dh/Jxff4/0zRiEc/P3niTtvrkoDJWd2', 'ROLE_REGISTRATION'),
       ('10000004', '$2a$10$5akX3lQH0qvX5.C11AAVy.dh/Jxff4/0zRiEc/P3niTtvrkoDJWd2', 'ROLE_REGISTRATION'),
       ('10000005', '$2a$10$5akX3lQH0qvX5.C11AAVy.dh/Jxff4/0zRiEc/P3niTtvrkoDJWd2', 'ROLE_REGISTRATION'),
       ('10000006', '$2a$10$5akX3lQH0qvX5.C11AAVy.dh/Jxff4/0zRiEc/P3niTtvrkoDJWd2', 'ROLE_REGISTRATION'),
       ('10000007', '$2a$10$5akX3lQH0qvX5.C11AAVy.dh/Jxff4/0zRiEc/P3niTtvrkoDJWd2', 'ROLE_REGISTRATION'),
       ('10000008', '$2a$10$5akX3lQH0qvX5.C11AAVy.dh/Jxff4/0zRiEc/P3niTtvrkoDJWd2', 'ROLE_REGISTRATION'),
       ('10000009', '$2a$10$5akX3lQH0qvX5.C11AAVy.dh/Jxff4/0zRiEc/P3niTtvrkoDJWd2', 'ROLE_REGISTRATION'),
       ('20000000', '$2a$10$5akX3lQH0qvX5.C11AAVy.dh/Jxff4/0zRiEc/P3niTtvrkoDJWd2', 'ROLE_OUTPATIENT'),
       ('20000001', '$2a$10$5akX3lQH0qvX5.C11AAVy.dh/Jxff4/0zRiEc/P3niTtvrkoDJWd2', 'ROLE_OUTPATIENT'),
       ('30000000', '$2a$10$5akX3lQH0qvX5.C11AAVy.dh/Jxff4/0zRiEc/P3niTtvrkoDJWd2', 'ROLE_INSPECT'),
       ('30000001', '$2a$10$5akX3lQH0qvX5.C11AAVy.dh/Jxff4/0zRiEc/P3niTtvrkoDJWd2', 'ROLE_INSPECT'),
       ('50000000', '$2a$10$5akX3lQH0qvX5.C11AAVy.dh/Jxff4/0zRiEc/P3niTtvrkoDJWd2', 'ROLE_DISPOSAL'),
       ('50000001', '$2a$10$5akX3lQH0qvX5.C11AAVy.dh/Jxff4/0zRiEc/P3niTtvrkoDJWd2', 'ROLE_DISPOSAL'),
       ('60000000', '$2a$10$5akX3lQH0qvX5.C11AAVy.dh/Jxff4/0zRiEc/P3niTtvrkoDJWd2', 'ROLE_PHARMACY'),
       ('60000001', '$2a$10$5akX3lQH0qvX5.C11AAVy.dh/Jxff4/0zRiEc/P3niTtvrkoDJWd2', 'ROLE_PHARMACY');
drop schema if exists his_system cascade;
create schema his_system;
set
search_path = his_system;
create table menus
(
    id       serial primary key,
    type     varchar(8) not null,
    title    varchar(8) not null,
    icon     varchar(8) not null,
    priority bigint     not null,
    parent   bigint,
    role     varchar(16),
    foreign key (parent) references menus (id) on delete cascade on update cascade
);
insert into menus(type, title, icon, priority, parent, role)
values ('menu', '系统', 'setting', 1, null, 'ADMIN'),
       ('menu', '挂号', 'common', 2, null, 'REGISTRATION'),
       ('menu', '门诊', 'common', 3, null, 'OUTPATIENT'),
       ('menu', '检查', 'common', 4, null, 'INSPECT'),
       ('menu', '处置', 'common', 5, null, 'DISPOSAL'),
       ('menu', '药房', 'common', 6, null, 'PHARMACY'),
       ('item', '菜单', '', 1, 1, 'ADMIN'),
       ('item', '挂号', '', 1, 2, 'REGISTRATION'),
       ('item', '退号', '', 2, 2, 'REGISTRATION'),
       ('item', '信息', '', 3, 2, 'REGISTRATION'),
       ('item', '诊疗', '', 1, 3, 'OUTPATIENT'),
       ('item', '检查', '', 2, 3, 'OUTPATIENT'),
       ('item', '开药', '', 3, 3, 'OUTPATIENT'),
       ('item', '处置', '', 4, 3, 'OUTPATIENT'),
       ('item', '记录', '', 1, 4, 'INSPECT'),
       ('item', '记录', '', 1, 5, 'DISPOSAL'),
       ('item', '药库', '', 1, 6, 'PHARMACY'),
       ('item', '发药', '', 2, 6, 'PHARMACY'),
       ('item', '退药', '', 3, 6, 'PHARMACY'),
       ('item', '信息', '', 4, 6, 'PHARMACY');
drop schema if exists his_register cascade;
create schema his_register;
set
search_path = his_register;
create table departments
(
    id   serial primary key,
    code varchar(64) not null,
    name varchar(64) not null
);
insert into departments (code, name)
values ('EK', '儿科'),
       ('NK', '男科'),
       ('FK', '妇科'),
       ('HXK', '呼吸科'),
       ('XHK', '消化科');
create table levels
(
    id   serial primary key,
    name varchar(64)   not null,
    fee  numeric(8, 2) not null
);
insert into levels (name, fee)
values ('普通号', 10),
       ('专家号', 50);
create table employees
(
    id         char(8) primary key,
    name       varchar(64) not null,
    department bigint      not null,
    level      bigint      not null,
    scheduling bigint      not null,
    foreign key (department) references departments (id),
    foreign key (level) references levels (id)
);
insert into employees (id, name, department, level, scheduling)
values ('10000000', 'A', 1, 2, 546),
       ('10000001', 'B', 1, 1, 682),
       ('10000002', 'C', 1, 1, 341),
       ('10000003', 'D', 1, 1, 561),
       ('10000004', 'E', 2, 2, 546),
       ('10000005', 'F', 3, 2, 546),
       ('10000006', 'G', 4, 2, 546),
       ('10000007', 'H', 4, 1, 585),
       ('10000008', 'I', 5, 2, 546),
       ('10000009', 'J', 5, 1, 585);
create table registers
(
    id       serial primary key,
    name     varchar(64) not null,
    gender   varchar(6)  not null,
    birthday timestamp   not null,
    employee char(8)     not null,
    time     timestamp   not null,
    state    char(3)     not null default '已挂号',
    foreign key (employee) references employees (id)
);
create table undo_log
(
    id            serial primary key,
    branch_id     bigint       not null,
    xid           varchar(100) not null,
    context       varchar(128) not null,
    rollback_info bytea        not null,
    log_status    int          not null,
    log_created   timestamp    not null,
    log_modified  timestamp    not null,
    ext           varchar(100) default null,
    unique (xid, branch_id)
);
drop schema if exists his_medicine cascade;
create schema his_medicine;
set
search_path = his_medicine;
create table medicines
(
    id           serial primary key,
    code         varchar(256)  not null,
    name         varchar(256)  not null,
    format       varchar(256)  not null,
    manufacturer varchar(256)  not null,
    price        numeric(8, 2) not null,
    number       numeric(8, 0) not null
);
insert into medicines (code, name, format, manufacturer, price, number)
values ('JWXSP', '健胃消食片', '64片', '江中', 10, 1000),
       ('AMXLJN', '阿莫西林胶囊', '48粒', '联邦阿莫仙', 30, 1000),
       ('BLFHSKL', '布洛芬缓释胶囊', '24粒', '芬必得', 20, 1000),
       ('LTSMJJP', '铝碳酸镁咀嚼片', '24片', '双贝', 28, 1000),
       ('MDYDSZSY', '门冬胰岛素注射液', '3毫升', '诺和锐', 260, 1000),
       ('DYXAJFHSJN', '对乙酰氨基酚缓释胶囊', '6粒', '泰诺', 8, 1000);
create table records
(
    id       serial primary key,
    medicine bigint        not null,
    change   numeric(8, 0) not null,
    before   numeric(8, 0) not null,
    after    numeric(8, 0) not null,
    time     timestamp     not null,
    foreign key (medicine) references medicines (id)
);
create table undo_log
(
    id            serial primary key,
    branch_id     bigint       not null,
    xid           varchar(100) not null,
    context       varchar(128) not null,
    rollback_info bytea        not null,
    log_status    int          not null,
    log_created   timestamp    not null,
    log_modified  timestamp    not null,
    ext           varchar(100) default null,
    unique (xid, branch_id)
);
drop schema if exists his_wallet cascade;
create schema his_wallet;
set
search_path = his_wallet;
create table wallets
(
    id    serial primary key,
    owner varchar(64) unique not null,
    money numeric(16, 2)     not null default 0
);
insert into wallets (owner, money)
values ('00000000', 0);
create table records
(
    id     serial primary key,
    wallet bigint         not null,
    spend  numeric(16, 2) not null,
    before numeric(16, 2) not null,
    after  numeric(16, 2) not null,
    time   timestamp      not null,
    foreign key (wallet) references wallets (id)
);