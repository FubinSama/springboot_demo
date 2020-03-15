drop table if exists user;
create table user(
    id int not null primary key auto_increment,
    username varchar(32),
    password varchar(255),
    enabled tinyint(1),
    locked tinyint(1)
);
drop table if exists role;
create table role (
    id int not null primary key auto_increment,
    name varchar(32),
    nameZh varchar(32)
);
drop table if exists user_role;
create table user_role (
    id int not null primary key auto_increment,
    uid int,
    rid int
);
drop table if exists menu;
create table menu(
     id int not null primary key auto_increment,
     pattern varchar(255)
);
drop table if exists menu_role;
create table menu_role(
    id int not null primary key auto_increment,
    mid int,
    rid int
);