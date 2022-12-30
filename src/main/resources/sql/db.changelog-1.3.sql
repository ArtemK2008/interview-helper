--liquibase formatted sql


--changeset nik:1
INSERT INTO person (name, email, password)
	VALUES
	('Nikolay', 'nikolay@mail.ru', 1),
	('Roman', 'roman@gmail.com', 1),
	('Anna', 'anna@gmail.com', 1),
	('Ivan', 'ivan@yandex.ru', 1),
	('Sveta', 'sveta@gmail.com', 1),
	('Lena', 'lena@gmail.com', 1),
	('Vladimir', 'vladimir@mail.ru', 1),
	('Dima', 'dima@gmail.com', 1),
	('Anna', 'anna@mail.ru', 1),
	('Roman', 'roman@yandex.ru', 1);

--changeset nik:2
INSERT INTO roles ("position")
	VALUES
	('ADMIN'),
	('USER');

--changeset nik:3
INSERT INTO person_to_roles (person_id, role_id)
	VALUES
	(1, 2),
	(1, 1),
	(2, 2),
	(3, 2),
	(4, 2),
	(8, 2),
	(7, 2),
	(6, 2),
	(5, 2),
	(9, 1),
	(10, 2);

--changeset nik:4
INSERT INTO topic (name, child_topic_id)
	VALUES
	('Java', null),
	('Python', null),
	('SQL', null),
	('Java ОПП', 1),
	('Java Core', 1),
	('Java Multithreading', 1),
	('Java Collections', 1),
	('Java JDBC', 1),
	('Java JSP, Servlets, JSTL', 1),
	('SQL Database', 3),
	('SQL Syntax', 3),
	('Java Spring Framework', 1),
	('Java Spring MVC', 12),
	('Java Spring Boot', 12),
	('Java Spring Security', 12);

--changeset nik:5
INSERT INTO person_to_topic (person_id, topic_id)
	VALUES
	(1, 13),
	(1, 15),
	(1, 3),
	(2, 1),
	(3, 4),
	(3, 7),
	(4, 12),
	(6, 6),
	(6, 7),
	(7, 2),
	(7, 3),
	(8, 4),
	(8, 13),
	(8, 14),
	(9, 10),
	(10, 9),
	(10, 8),
	(8, 6);

