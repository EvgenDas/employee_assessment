insert into employees (name, surname, manager_id, expert_id, login, password)
values ('John','Doe', 0, 0, 'johndoe@gmail.com', '$2a$10$Xl0yhvzLIaJCDdKBS0Lld.ksK7c2Zytg/ZKFdtIYYQUv8rUfvCR4W'),
       ('Mike', 'Smith', 0, 0, 'mikesmith@yahoo.com', '$2a$10$fFLij9aYgaNCFPTL9WcA/uoCRukxnwf.vOQ8nrEEOskrCNmGsxY7m'),
       ('Jake', 'Tas', 2, 0, 'jaketas@yahoo.com', '$2a$12$wLiTgI2cV/8DaMP1VugxVOZg6nNxDuQdp3ZK9kZBIarZN9Sh2FcuG'),
       ('Emir', 'Ivanov ', 2, 5, 'emirivanov@gmail.com', '$2a$12$p0lOWz5zsCOgy5nTi8lrPOD2m00UXIOZlKGutA4w7kdCCpsauIHPG'),
       ('Evgeny', 'Dashencko ', 2, 0, 'evgenydashencko@yahoo.com', '$2a$12$IqTJ44REQzUBokBScTr7h.CDK3HZ18m9nMOa1GuibJKsCMXvgk5CK');

insert into assessments (own_assessment, expert_assessment, manager_assessment, date_of_assessment, is_active)
values (3, 4, 4, '2023-01-29 12:00:00', 'true'),
       (5, 3, 4, '2023-01-31 00:00:00', 'true'),
       (5, 3, 3, '2023-01-31 00:00:00', 'true');


insert into matrix (who, data_base, build_tools, testing, vcs, kubernetes, docker, java_core, jdbc, jvm, security, spring, kafka, orm, ci_cd, helm, microservice)
values ('own', 4, 4, 3, 4, 5, 2, 5, 5, 4, 3, 4, 4, 3, 2, 2, 3),
       ('own', 4, 1, 3, 5, 5, 2, 5, 5, 4, 1, 4, 1, 5, 2, 2, 3),
       ('own', 0, 4, 5, 4, 2, 2, 3, 2, 1, 3, 4, 5, 3, 4, 2, 3),
       ('manager', 2, 3, 3, 4, 3, 4, 4, 5, 4, 2, 4, 4, 3, 1, 2, 3),
       ('manager', 5, 4, 3, 1, 5, 2, 5, 3, 4, 3, 4, 4, 5, 2, 2, 1),
       ('manager', 3, 4, 2, 1, 5, 2, 5, 3, 4, 3, 5, 4, 3, 2, 5, 3),
       ('expert', 2, 3, 3, 4, 3, 4, 4, 5, 4, 2, 4, 4, 3, 1, 2, 3),
       ('expert', 5, 4, 3, 1, 5, 2, 5, 3, 4, 3, 4, 4, 5, 2, 2, 1),
       ('expert', 3, 4, 2, 1, 5, 2, 5, 3, 4, 3, 5, 4, 3, 2, 5, 3),
       ('final', 2, 3, 3, 4, 3, 4, 4, 5, 4, 2, 4, 4, 3, 1, 2, 3),
       ('final', 5, 4, 3, 1, 5, 2, 5, 3, 4, 3, 4, 4, 5, 2, 2, 1),
       ('final', 3, 4, 2, 1, 5, 2, 5, 3, 4, 3, 5, 4, 3, 2, 5, 3),

insert into employees_assessments (assessment_id, employee_id)
values (1, 3),
       (2, 4),
       (3, 5);

insert into employees_roles (employee_id, role)
values (1, 'ROLE_ADMIN'),
       (2, 'ROLE_MANAGER'),
       (3, 'ROLE_USER'),
       (4, 'ROLE_USER'),
       (5, 'ROLE_USER'),
       (5, 'ROLE_EXPERT');

insert into assessments_matrix (assessment_id, matrix_id)
values (1, 1),
       (1, 4),
       (1, 7),
       (1, 10),
       (2, 2),
       (2, 5),
       (2, 8),
       (2, 11),
       (3, 3),
       (3, 6),
       (3, 9),
       (3, 12);