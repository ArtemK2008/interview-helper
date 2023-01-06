--liquibase formatted sql


--changeset nik:6
INSERT INTO question (question, topic_id)
	VALUES
	('Question about Java ОПП', 4),
	('Question about Java Collections', 7),
	('Question about Java Core', 5),
	('Question about Java Core', 5),
	('Question about Java Core', 5),
	('Question about Java JDBC', 8),
	('Question about Java Spring MVC', 13),
	('Question about Java Spring MVC', 13),
	('Question about Java Spring Boot', 14),
	('Question about Java JSP, Servlets, JSTL', 9),
	('Question about Java Collections', 7),
	('Question about Java ОПП', 4),
	('Question about Java Core', 5),
	('Question about Java Multithreading', 6),
	('Question about SQL Database', 10),
	('Question about SQL Syntax', 11),
	('Question about SQL Syntax', 11),
	('Question about SQL Syntax', 11);


--changeset nik:7
INSERT INTO answer (answer, voice_count, isdefault, question_id)
	VALUES
	('Java ОПП question answer1', 5, true, 1),
	('Java ОПП question answer2', 1, false, 1),
	('Java ОПП question answer3', 8, false, 1),
	('Java Collections question answer1', 0, true, 2),
	('Java Core question answer1', 1, true, 3),
	('Java Core question answer1', 4, true, 4),
	('Java Core question answer1', 9, true, 5),
	('Java Core question answer2', 5, false, 5),
	('Java Core question answer3', -1, false, 5),
	('Java Core question answer4', 0, false, 5),
	('Java JDBC question answer1', 0, true, 6),
	('Java Spring MVC question answer1', 5, true, 7),
	('Java Spring MVC question answer1', 3, true, 8),
	('Java Spring Boot question answer1', 5, true, 9),
	('Java JSP, Servlets, JSTL question answer1', 0, true, 10),
	('Java Collections question answer1', 0, true, 11),
	('Java ОПП question answer1', 0, true, 12),
	('Java Core question answer1', 5, true, 13),
	('Java SQL Database question answer1', 12, true, 15),
	('Java SQL Syntax question answer1', 5, true, 16),
	('Java SQL Syntax question answer1', 2, true, 17),
	('Java SQL Syntax question answer1', -2, true, 18),
	('Java JDBC question answer2', 5, false, 1),
	('Java ОПП question answer4', -3, false, 1),
	('Java SQL Syntax question answer1', 16, false, 18);