--liquibase formatted sql


--changeset nik:8
INSERT INTO statistics(answer_wrong_count, answer_correct_count)
	VALUES
	(1, 4),
	(2, 0),
	(3, 4),
	(1, 4),
	(2, 9),
	(6, 2),
	(7, 0),
	(0, 8),
	(0, 0),
	(2, 4);


--changeset nik:9
INSERT INTO statistics_to_question (statistic_id, question_id)
	VALUES
	(1, 4),
	(1, 12),
	(1, 2),
	(1, 3),
	(3, 7),
	(3, 8),
	(3, 1),
	(3, 14),
	(4, 2),
	(4, 17),
	(4, 16),
	(4, 4),
	(5, 2),
	(5, 1),
	(5, 3),
	(5, 4),
	(5, 5),
	(5, 6),
	(5, 7),
	(5, 8),
	(5, 9),
	(6, 2),
	(6, 11),
	(8, 18),
	(8, 17),
	(8, 16),
	(8, 15),
	(8, 14),
	(8, 13),
	(8, 12),
	(8, 11),
	(10, 5),
	(10, 6),
	(10, 7),
	(10, 8);

--changeset nik:10
INSERT INTO person_to_question (person_id, question_id)
	VALUES
	(1, 17),
	(2, 4),
	(2, 3),
	(3, 4),
	(3, 2),
	(3, 3),
	(4, 5),
	(5, 10),
	(5, 11),
	(6, 1),
	(6, 3),
	(6, 4),
	(6, 5),
	(6, 6),
	(6, 7),
	(7, 18),
	(7, 17),
	(7, 16),
	(7, 15),
	(7, 14),
	(7, 13),
	(7, 12),
	(10, 13),
	(10, 12);