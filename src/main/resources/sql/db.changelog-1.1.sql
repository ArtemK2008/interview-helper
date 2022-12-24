--liquibase formatted sql

--changeset anna:3
CREATE TABLE IF NOT EXISTS Roles (
                                     id 			    serial PRIMARY KEY,
                                     position 	 	varchar(20) NOT NULL
);

--changeset anna:4
CREATE TABLE IF NOT EXISTS Question (
                                        id 		    serial PRIMARY KEY,
                                        question 	varchar(100) NOT NULL,
                                        topic_id 	integer NOT NULL,
                                        FOREIGN KEY (topic_id) REFERENCES Topic (id)
                                                ON DELETE CASCADE
                                                ON UPDATE CASCADE
);

--changeset anna:5
CREATE TABLE IF NOT EXISTS Person (
                                        id 		serial PRIMARY KEY,
                                        name 		varchar(20) NOT NULL,
                                        email 	varchar(20) NOT NULL,
                                        password 	varchar(20) NOT NULL
);

--changeset anna:6
CREATE TABLE IF NOT EXISTS Statistics (
                                        person_id		serial PRIMARY KEY,
                                        FOREIGN KEY (person_id) REFERENCES Person (id)
                                                ON DELETE CASCADE
                                                ON UPDATE CASCADE,
                                        answer_wrong_count 	integer NOT NULL,
                                        answer_correct_count 	integer NOT NULL
);

--changeset anna:7
CREATE TABLE IF NOT EXISTS Person_to_topic (
                                        person_id 		integer NOT NULL,
                                        topic_id 		integer NOT NULL,
                                        primary key (person_id, topic_id),
                                        FOREIGN KEY (person_id) REFERENCES Person (id)
                                                ON DELETE CASCADE
                                                ON UPDATE CASCADE,
                                        FOREIGN KEY (topic_id) REFERENCES Topic (id)
                                                ON DELETE CASCADE
                                                ON UPDATE CASCADE
);


--changeset anna:8
CREATE TABLE IF NOT EXISTS Person_to_roles (
                                        person_id 		integer NOT NULL,
                                        role_id 		integer NOT NULL,
                                        primary key (person_id, role_id),
                                        FOREIGN KEY (person_id) REFERENCES Person (id)
                                                ON DELETE CASCADE
                                                ON UPDATE CASCADE,
                                        FOREIGN KEY (role_id) REFERENCES Roles (id)
                                                ON DELETE CASCADE
                                                ON UPDATE CASCADE
);

--changeset anna:9
CREATE TABLE IF NOT EXISTS Statistics_to_question (
                                        statistic_id 	integer NOT NULL,
                                        question_id 	integer NOT NULL,
                                        primary key (statistic_id, question_id),
                                        FOREIGN KEY (statistic_id) REFERENCES Statistics (person_id)
                                                ON DELETE CASCADE
                                                ON UPDATE CASCADE,
                                        FOREIGN KEY (question_id) REFERENCES Question (id)
                                                ON DELETE CASCADE
                                                ON UPDATE CASCADE
);

--changeset anna:10
CREATE TABLE IF NOT EXISTS Person_to_question (
                                        person_id 	integer NOT NULL,
                                        question_id 	integer NOT NULL,
                                        primary key (person_id, question_id),
                                        FOREIGN KEY (person_id) REFERENCES Person (id)
                                                ON DELETE CASCADE
                                                ON UPDATE CASCADE,
                                        FOREIGN KEY (question_id) REFERENCES Question (id)
                                                ON DELETE CASCADE
                                                ON UPDATE CASCADE
);

--changeset anna:11
ALTER TABLE Answer
                                        ADD isDefault 	boolean NOT NULL,
                                        ADD question_id 	integer NOT NULL,
                                        ADD FOREIGN KEY (question_id) REFERENCES Question (id)
                                                ON DELETE CASCADE
                                                ON UPDATE CASCADE;

--changeset anna:12
ALTER TABLE Topic
                                        ADD child_topic_id 	integer NULL,
                                        ADD FOREIGN KEY (child_topic_id) REFERENCES Topic (id)
                                                MATCH SIMPLE
                                                ON DELETE CASCADE
                                                ON UPDATE CASCADE;