--liquibase formatted sql


--changeset anna:11
ALTER TABLE Person
    ALTER COLUMN password TYPE varchar(200);

--changeset anna:12
UPDATE Person
SET password = '$2a$12$hpMLjUf9yXL8OBpFSYOgDuz7l.loliUZ.F2JC0S7FCNwqiQlFWRwW'
WHERE email = 'nikolay@mail.ru';

UPDATE Person
SET password = '$2a$12$LjL6MgufVOrscyuya57o5uIuqsyd4R2Gn6SlL6KNu6uZyKMnXfW5S'
WHERE email = 'roman@gmail.com';

UPDATE Person
SET password = '$2a$12$ceM6e9zn9FGZ.1lzGdZF1O.avRhe8B7Y9csieGNfoeiiYjrM9UVGi'
WHERE email = 'anna@gmail.com';

UPDATE Person
SET password = '$2a$12$8zTFnFMWEWDVeMlxP5EjVO8ce1ZbPq/Sl7.F3.uDNR7gwZV7R3EJa'
WHERE email = 'ivan@yandex.ru';

UPDATE Person
SET password = '$2a$12$wjbVkBQL8roKn5GKsgr.Ze1b5LOHMjttZqW.272uxpUgQfAXdDufS'
WHERE email = 'sveta@gmail.com';

UPDATE Person
SET password = '$2a$12$w1pkzFCMix/UJJGy9ay80Olh9yCCaIK5FXGy6rDYCYuMOHTZxy29y'
WHERE email = 'lena@gmail.com';

UPDATE Person
SET password = '$2a$12$PT8YzXp/DHIqM/4H/57F/.H5SXwHUB8kZSZ1YB2VFzq2qZp3jAVRK'
WHERE email = 'vladimir@mail.ru';

UPDATE Person
SET password = '$2a$12$mbu/m8ui5pzvBpDKwWVhZOlMq9MygKiRvwDXaBtAjHb2PXbUyoI7O'
WHERE email = 'dima@gmail.com';

UPDATE Person
SET password = '$2a$12$nsCmj7w7lDXto3CppbkdfeWurNKg81BI0weaXkHyeCze6TLj9dTfi'
WHERE email = 'anna@mail.ru';

UPDATE Person
SET password = '$2a$12$usbh4/QBCyCUM95gx9Vnx.MCtL9Z6BNmbRgB3O4PMKTdaLM65YqYi'
WHERE email = 'roman@yandex.ru';

--changeset anna:13
INSERT INTO Person (name, email, password)
VALUES
    ('Admin', 'admin@gmail.com', '$2a$12$BiQFR0Fzi3DU889bHs515.CdkWcbdASF4KRn5iWDg4ok4WbEu.S3a'),
    ('User', 'user@gmail.com', '$2a$12$b/MVIbOSOGq4NHg4Uuu0beRcZcfz8859lLZ6Izhn6711XXhXLr.La');

--changeset anna:14
INSERT INTO person_to_roles (person_id, role_id)
VALUES
    (11, 1),
    (12, 2);


