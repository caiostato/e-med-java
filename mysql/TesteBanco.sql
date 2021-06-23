drop database if exists emed_project;
create database emed_project;
use emed_project;

#tabela medico
create table medico (
	crm integer primary key unique not null,
	medico_nome varchar(50) not null,
	medico_email varchar(40) unique not null,
	medico_senha varchar(30) not null
);

#tabela farmaceutico
create table farmaceutico (
	crf integer primary key unique not null,
	farmaceutico_nome varchar(50) not null,
	farmaceutico_email varchar(40) unique not null,
	farmaceutico_senha varchar(30) not null
);

#tabela admin
create table adm (
	adm_login varchar(20) primary key unique not null,
	adm_senha varchar(30) not null
);

#tabela medicamento
create table medicamento (
	medicamento_id int auto_increment primary key unique not null,
	medicamento_nome varchar(30) not null,
    medicamento_quantidade int not null default 0,
    medicamento_cas varchar(45),
    medicamento_ultimo_crm int,
    medicamento_ultimo_crf int,
    
	constraint FK_ultimo_crm
	foreign key (medicamento_ultimo_crm)
	references medico(crm),
    
	constraint FK_ultimo_crf
	foreign key (medicamento_ultimo_crf)
	references farmaceutico(crf)	
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

-- PROCEDURES

drop procedure if exists cadastrar;
delimiter $$
create procedure cadastrar(nome varchar(40),quant int,cas varchar(40),crf int)
begin
	declare idmed int;
	if exists (select medicamento_id from medicamento where medicamento_nome =nome) then
		set idmed = (select medicamento_id from medicamento where medicamento_nome = nome);
	end if;
	
	IF EXISTS (select * from medicamento where medicamento_id = idmed) THEN
		update medicamento set medicamento_quantidade = medicamento_quantidade + quant where medicamento_id = idmed;
        update medicamento set medicamento_ultimo_crf = crf where medicamento_id = idmed;
	ELSE 
		insert into medicamento(medicamento_nome,medicamento_quantidade,medicamento_cas) values (nome,quant,cas);
	END IF;
end $$
delimiter ;

-- -----------------

drop procedure if exists solicitar;
delimiter $$
create procedure solicitar(nome varchar(40),quant int,crm int)
begin
	declare idmed int;
	if exists (select medicamento_id from medicamento where medicamento_nome =nome) then
		set idmed = (select medicamento_id from medicamento where medicamento_nome = nome);
	end if;
	
	IF EXISTS (select * from medicamento where medicamento_id = idmed) THEN
		update medicamento set medicamento_quantidade = medicamento_quantidade - quant where medicamento_id = idmed;
        update medicamento set medicamento_ultimo_crm = crm where medicamento_id = idmed;
	END IF;
end $$
delimiter ;

-- -----------------

drop procedure if exists insert_adm;
DELIMITER $$
create procedure insert_adm(tipo int,crm_crf int, nome varchar(45),email varchar(100),senha varchar(45))
#tipo 1 igual medido/ tipo 2 igual farmaceutico
begin
	if tipo = 1 then
		insert into medico values (crm_crf,nome,email,senha);
    else 
		insert into farmaceutico values (crm_crf,nome,email,senha);
    end if;
end;
$$
DELIMITER ;
-- --------------------------------------------------------------------------------------
-- TRIGGERS
-- --------------------------------------------------------------------------------------
DELIMITER $$
create trigger triggerCadastro
	after insert on medicamento
for each row
begin
	insert into cadastro_medicamento (medicamento_id,farmaceutico_id,cadastro_data) values (new.medicamento_id,new.medicamento_ultimo_crf,curdate());
end;
$$
DELIMITER ;
-- -----------------
DELIMITER $$
create trigger triggerSolicita
	after update on medicamento
for each row
begin
	if new.medicamento_quantidade < old.medicamento_quantidade then
		insert into solicita_medicamento (medicamento_id,medico_id,pedido_data) values (new.medicamento_id,new.medicamento_ultimo_crm,curdate());
    end if;
end;
$$
DELIMITER ;

-- --------------------------------------------------------------------------------------
#INSERTS
-- --------------------------------------------------------------------------------------
call insert_adm(1,519648,'Claudio Varanda','claudio@email.com','senha');
call insert_adm(1,985316,'Tiago Lifer','tiago@email.com','senha');
call insert_adm(1,124865,'Jertrudes','trudes@email.com','senha');
call insert_adm(1,913287,'Roberval','rober@email.com','senha');
call insert_adm(1,324845,'alicia','alicia@email.com','senha');

select * from medico;
delete from medico where crm = 131511;
-- -----------------
call insert_adm(2,625454,'julia','julia@email.com','senha');
call insert_adm(2,156952,'Abner','abner@email.com','senha');
call insert_adm(2,923184,'Lucas','lucas@email.com','senha');
call insert_adm(2,517612,'Vitor','vitor@email.com','senha');
call insert_adm(2,187163,'Carlinho','carlinho@email.com','senha');

select * from farmaceutico;
-- -----------------
call cadastrar("Dipirona",100,"68-89-3",187163);
call cadastrar("Paracetamol",100,"103-90-2",187163);
call cadastrar("Codeina",100,"76-57-3",187163);
call cadastrar("Rivotril",100,"1622-61-3",187163);
call cadastrar("Orfenadrina",100,"83-98-7",187163);

select * from medicamento;
select * from cadastro_medicamento;
-- -----------------
call solicitar("Rivotril",50,124865);

select * from medicamento;
select * from solicita_medicamento;
-- -----------------
insert into adm values ("adm","senha");

select * from adm;