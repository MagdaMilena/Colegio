drop database if exists colegio;
create database colegio;
use colegio;

create table estudiante (
docEst  varchar(11) not null, 
nomEst  varchar(30) not null,
apeEst  varchar(30) not null,
dirEst  varchar(50) not null,
telEst  varchar(11) not null,
primary key(docEst)
);

create table profesor (
docPro	varchar(11) not null,
nomPro	varchar(30) not null,
apePro	varchar(30)	not null,
dirPro	varchar(50)	not null,
telPro	varchar(11)	not null,
emaPro	varchar(30)	not null,
titPro	varchar(30)	not null,
primary key(docPro)
);

create table acudiente (
docAcu	varchar(11)	not null,
nomAcu	varchar(30)	not null,
apeAcu	varchar(30)	not null,
dirAcu	varchar(50)	not null,
telAcu	varchar(11)	not null,
emaAcu	varchar(30)	not null,
primary key(docAcu)
);

create table acudienteXEstudiante (
consAcu 	int auto_increment,
docAcu		varchar(11)	not null,
docEstAcu	varchar(11)	not null,
primary key(consAcu),
foreign key(docAcu) references acudiente(docAcu),
foreign key(docEstAcu) references estudiante(docEst)
);

create table materia (
codMat	int auto_increment,
nomMat	varchar(30)	not null,
graMat	varchar(20)	not null,
primary key(codMat)
);

create table materiaXProfesor (
conMatxPro	int auto_increment,
codMatMatxPro	integer	not null,
docProMatxPro	varchar(11)	not null,
graMatxPro		varchar(20)	not null,
primary key(conMatxPro),
foreign key(codMatMatxPro) references materia(codMat),
foreign key(docProMatxPro) references profesor(docPro)
);
