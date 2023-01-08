--liquibase formatted sql


--changeset nik:1
update answer set isdefault = 'false' where id = '26';
--changeset nik:2
update answer set isdefault = 'false' where id = '29';
--changeset nik:3
update answer set isdefault = 'false' where id = '32';
--changeset nik:4
update answer set isdefault = 'false' where id = '35';
--changeset nik:5
update answer set isdefault = 'false' where id = '38';
--changeset nik:6
update answer set isdefault = 'false' where id = '41';
--changeset nik:7
update answer set isdefault = 'false' where id = '44';
--changeset nik:8
update answer set isdefault = 'false' where id = '47';
