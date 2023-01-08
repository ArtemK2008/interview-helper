--liquibase formatted sql


--changeset artem:1
INSERT INTO question (question, topic_id)
VALUES
    ('Question 1', 4),
    ('Question 2', 7),
    ('Question 3', 5),
    ('Question 4', 5),
    ('Question 5', 5),
    ('Question 6', 8),
    ('Question 7', 13),
    ('Question 8', 13),
    ('Question 9', 14),
    ('Question 10', 9);

--changeset artem:2
INSERT INTO answer (answer, voice_count, isdefault, question_id)
VALUES
    ('Answer 11', 5, true, 19),
    ('Answer 12', 1, false, 19),
    ('Answer 13', 8, false, 19),
    ('Answer 21', 5, true, 20),
    ('Answer 22', 1, false, 20),
    ('Answer 23', 8, false, 20),
    ('Answer 31', 5, true, 21),
    ('Answer 32', 1, false, 21),
    ('Answer 33', 8, false, 21),
    ('Answer 41', 5, true, 22),
    ('Answer 42', 1, false, 22),
    ('Answer 43', 8, false, 22),
    ('Answer 51', 5, true, 23),
    ('Answer 52', 1, false, 23),
    ('Answer 53', 8, false, 23),
    ('Answer 61', 5, true, 24),
    ('Answer 62', 1, false, 24),
    ('Answer 63', 8, false, 24),
    ('Answer 71', 5, true, 25),
    ('Answer 72', 1, false, 25),
    ('Answer 73', 8, false, 25),
    ('Answer 81', 5, true, 26),
    ('Answer 82', 1, false, 26),
    ('Answer 83', 8, false, 26);
