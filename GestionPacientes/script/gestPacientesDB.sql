DROP DATABASE IF EXISTS gestPacientesDB;
CREATE DATABASE gestPacientesDB;
USE gestPacientesDB;


CREATE TABLE Paciente
       (id integer primary key auto_increment,
        nombre varchar(20) NOT NULL,
        apellidos varchar(50) NOT NULL,
        edad numeric(3),
        telefono numeric(9) NOT NULL,
        email varchar(60) NOT NULL,
        direccion varchar(50),
        poblacion varchar(20),
        ciudad varchar(15),
        fecha_alta date,
        terapeuta varchar(10));