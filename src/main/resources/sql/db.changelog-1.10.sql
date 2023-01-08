--liquibase formatted sql


--changeset nik:1
update topic set name = 'Java OOP' where id = '4';
