--liquibase formatted sql


--changeset nik:1
update answer set answer = 'Java SQL Syntax question answer12', isdefault = 'false' where id = '20';
--changeset nik:2
update answer set answer = 'Java SQL Syntax question answer13', isdefault = 'false' where id = '21';
--changeset nik:3
update answer set answer = 'Java SQL Syntax question answer14', isdefault = 'false' where id = '22';
--changeset nik:4
update answer set answer = 'Java Core question answer12', isdefault = 'false' where id = '5';
--changeset nik:5
update answer set answer = 'Java Core question answer13', isdefault = 'false' where id = '6';
--changeset nik:6
update answer set answer = 'Java Core question answer14', isdefault = 'false' where id = '7';
--changeset nik:7
update answer set answer = 'Java Spring MVC question answer12', isdefault = 'false' where id = '13';
--changeset nik:8
update answer set answer = 'Java Collections question answer12', isdefault = 'false' where id = '16';
