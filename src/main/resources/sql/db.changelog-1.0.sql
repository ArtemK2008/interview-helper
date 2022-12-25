--liquibase formatted sql


--changeset anna:1
CREATE TABLE IF NOT EXISTS Answer (
                                      id 		    serial PRIMARY KEY,
                                      answer 		text NOT NULL,
                                      voice_count 	integer NOT NULL
);

--changeset anna:2
CREATE TABLE IF NOT EXISTS Topic (
                                     id 		serial PRIMARY KEY,
                                     name 		varchar(40) NOT NULL
    );
--validCheckSum: 8:0a723302232fe2ce12750ab5dfd541a7