--liquibase formatted sql


--changeset anna:13
ALTER table  statistics rename column person_id to statistics_person_id;