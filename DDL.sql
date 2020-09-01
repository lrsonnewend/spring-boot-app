use spring;

create table usuario (
	id_usuario bigint unsigned not null auto_increment,
	nome varchar(20) not null,
	senha varhar(50) not null,
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