
CREATE TABLE IF NOT EXISTS Answer (
id 		serial PRIMARY KEY,
answer 		text NOT NULL,
voice_count 	integer NOT NULL
);

CREATE TABLE IF NOT EXISTS Topic (
id 		serial PRIMARY KEY,
name 		varchar(40) NOT NULL
);

CREATE TABLE IF NOT EXISTS Statistics (
id 			serial PRIMARY KEY,
count_of_wrong_answers 	integer NOT NULL
);

CREATE TABLE IF NOT EXISTS Question (
id 		serial PRIMARY KEY,
question 	varchar(100) NOT NULL,
topic_id 	integer NOT NULL,
FOREIGN KEY (topic_id) REFERENCES Topic (id)
	ON DELETE CASCADE
	ON UPDATE CASCADE,
answer_id 	integer NOT NULL,
FOREIGN KEY (answer_id) REFERENCES Answer (id)
	ON DELETE CASCADE
	ON UPDATE CASCADE
);

CREATE TYPE role AS ENUM ('ADMIN', 'USER');

CREATE TABLE IF NOT EXISTS Person (
id 		serial PRIMARY KEY,
name 		varchar(20) NOT NULL,
email 		varchar(20) NOT NULL,
password 	varchar(20) NOT NULL,
statistic_id 	integer NOT NULL,
FOREIGN KEY (statistic_id) REFERENCES Statistics (id)
	ON DELETE CASCADE
	ON UPDATE CASCADE,
topic_id integer NOT NULL,
FOREIGN KEY (topic_id) REFERENCES Topic (id)
	ON DELETE CASCADE
	ON UPDATE CASCADE,
role 		role
);




CREATE TABLE IF NOT EXISTS Unanswered_question_to_statistics (
statistic_id 	integer NOT NULL,
question_id 	integer NOT NULL,
primary key (statistic_id, question_id),
FOREIGN KEY (statistic_id) REFERENCES Statistics (id)
	ON DELETE CASCADE
	ON UPDATE CASCADE,
FOREIGN KEY (question_id) REFERENCES Question (id)
	ON DELETE CASCADE
	ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS Save_question_to_person (
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










