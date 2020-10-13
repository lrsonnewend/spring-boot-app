drop user 'user'@'localhost';

drop database spring;

create database spring;

use spring;

create user 'user'@'localhost' identified by 'pass123';

grant select, insert, delete, update on spring.* to user@'localhost';

create table usuario (
	id_usuario bigint unsigned not null auto_increment,
	nome varchar(20) not null,
	senha varchar(100) not null,
	primary key (id_usuario),
	unique key uni_nome (nome)
);

create table autorizacao(
	id_aut bigint unsigned not null auto_increment,
	aut_nome varchar(20) not null,
	primary key (id_aut),
	unique key uni_aut_nome (aut_nome)
);

create table usuario_autorizacao(
	id_usuario bigint unsigned not null,
	id_aut bigint unsigned not null,
	primary key (id_usuario, id_aut),
	foreign key usuario_fk (id_usuario) references usuario (id_usuario),
	foreign key aut_fk (id_aut) references autorizacao (id_aut)
);

insert into usuario (nome, senha) values ('admin', '$2a$10$i3.Z8Yv1Fwl0I5SNjdCGkOTRGQjGvHjh/gMZhdc3e7LIovAklqM6C');

insert into autorizacao (aut_nome) values ('ROLE_ADMIN');

insert into usuario_autorizacao values (1,1);