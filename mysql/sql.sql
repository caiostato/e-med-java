drop database if exists emedjava;
create database emedjava;
use emedjava;

#tabela medico
create table medico (
	crm integer primary key unique not null,
	medico_nome varchar(50) not null,
	medico_email varchar(40) unique not null,
    medico_cpf varchar(15) unique not null,
	medico_senha varchar(30) not null
);

#tabela farmaceutico
create table farmaceutico (
	crf integer primary key unique not null,
	farmaceutico_nome varchar(50) not null,
	farmaceutico_email varchar(40) unique not null,
    farmaceutico_cpf varchar(15) unique not null,
	farmaceutico_senha varchar(30) not null
);

#tabela admin
create table adm (
	adm_login varchar(20) primary key unique not null,
	adm_senha varchar(30) not null,
    adm_nome varchar(50) not null,
    adm_email varchar(40) unique not null,
    adm_cpf varchar(15) unique not null
);

#tabela medicamento
create table medicamento (
	medicamento_id int auto_increment primary key unique not null,
	medicamento_nome varchar(30) not null,
    medicamento_quantidade int not null default 0,
    medicamento_cas varchar(45)
);

#tabela solicita(medico~medicamento)
drop table if exists solicita_medicamento;
create table solicita_medicamento (
	pedido_id serial primary key unique not null,
	medicamento_id integer,
	medico_id integer,
	pedido_data date not null,
	
	constraint FK_IdMedicamento
	foreign key (medicamento_id)
	references medicamento(medicamento_id)
    on delete cascade,
	
	constraint FK_IdMedico
	foreign key (medico_id)
	references medico(crm)
	on delete cascade
);

#tabela cadastro(farmaceutico e medicamento)
drop table if exists cadastro_medicamento;
create table cadastro_medicamento (
	cadastro_id serial primary key unique not null,
	medicamento_id integer,
	farmaceutico_id integer,
	cadastro_data date not null,
	
	constraint FK_IdMedicamento2
	foreign key (medicamento_id)
	references medicamento(medicamento_id)
    on delete cascade,
	
	constraint FK_IdFarmaceutico
	foreign key (farmaceutico_id)
	references farmaceutico(crf)	
    on delete cascade
);