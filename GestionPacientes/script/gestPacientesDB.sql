DROP DATABASE IF EXISTS gestPacientesDB;
CREATE DATABASE gestPacientesDB;
USE gestPacientesDB;


CREATE TABLE Paciente
       (id integer primary key auto_increment,
        nombre varchar(20) NOT NULL,
        apellidos varchar(50) NOT NULL,
        edad numeric(3),
        telefono numeric(9) NOT NULL,
        email varchar(80) NOT NULL,
        direccion varchar(50),
        poblacion varchar(20),
        ciudad varchar(15),
        fecha_alta date,
        terapeuta varchar(10));
        
INSERT INTO Paciente VALUES
 (null,'Ana', 'Gómez Castilla', 35, 645789123, 'ana.gomez@gmail.com', 'Ronda Oeste, 1', 'Villanueva del campo', 'Zamora', '2017-06-23', 'Cristina'),
 (null, 'Paco', 'Pérez Martínez', 59, 678945612, 'paco.perez@gmail.com', 'Plaza Mayor, 23', 'Graus', 'Huesca', '2022-03-07', 'Andrea'),
 (null, 'Marina', 'de la Osa Campo', 18, 685412856, 'moc@gmail.com', 'Calle Alta, 1', 'Grañén', 'Huesca', '2020-11-17', 'Andrea'),
 (null, 'Juan', 'Mas Puig', 28, 974568956, 'juan.mas@gmail.com', 'Calle Nueva, 15', 'Huesca', 'Huesca', '2019-10-21', 'Cristina'),
 (null, 'Óscar', 'Molina Santos', 36, 678896589, 'oscar.molina@hotmail.com', 'Plaza Zaragoza, 11', 'Huesca', 'Huesca', '2021-06-09', 'Cristina'),
 (null, 'Marta', 'Sánchez García', 48, 974568963, 'marta.sg@gmail.com', 'Avenida de la Cruz Roja, 3', 'Barbastro', 'Huesca', '2019-08-07', 'Andrea'),
 (null, 'Ana', 'Torres Casbas', 26, 685963326, 'ana.torres@hotmail.com', 'Avenida de la Música, 45', 'Graus', 'Huesca', '2018-12-12', 'Cristina'),
 (null, 'Juan José', 'Moreno Muro', 37, 974569896, 'jjmm@gmail.com', 'Calle Tenerías, 22', 'Huesca', 'Huesca', '2020-03-31', 'Andrea');