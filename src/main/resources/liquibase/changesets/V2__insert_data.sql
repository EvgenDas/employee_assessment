insert into employees (name, surname, manager_id, expert_id, login, password)
values ('John','Doe', 0, 0, 'johndoe@gmail.com', '$2a$10$Xl0yhvzLIaJCDdKBS0Lld.ksK7c2Zytg/ZKFdtIYYQUv8rUfvCR4W'),
       ('Mike', 'Smith', 0, 0, 'mikesmith@yahoo.com', '$2a$10$fFLij9aYgaNCFPTL9WcA/uoCRukxnwf.vOQ8nrEEOskrCNmGsxY7m'),
       ('Jake', 'Tas', 2, 0, 'jaketas@yahoo.com', '$2a$12$wLiTgI2cV/8DaMP1VugxVOZg6nNxDuQdp3ZK9kZBIarZN9Sh2FcuG'),
       ('Emir', 'Ivanov ', 2, 5, 'emirivanov@gmail.com', '$2a$12$p0lOWz5zsCOgy5nTi8lrPOD2m00UXIOZlKGutA4w7kdCCpsauIHPG'),
       ('Evgeny', 'Dashencko ', 2, 0, 'evgenydashencko@yahoo.com', '$2a$12$IqTJ44REQzUBokBScTr7h.CDK3HZ18m9nMOa1GuibJKsCMXvgk5CK');

insert into assessments (own_assessment, expert_assessment, manager_assessment, date_of_assessment, is_active)
values ('{"data_base": 4, "build_tools": 4, "testing": 3, "vcs": 2, "kubernetes": 5, "docker": 4, "java_core": 3, "jdbc": 2, "jvm": 5, "security": 3, "spring": 5, "kafka": 3, "orm": 2, "ci_cd": 2, "helm": 1, "microservice": 5}',
 '{"data_base": 4, "build_tools": 4, "testing": 3, "vcs": 2, "kubernetes": 5, "docker": 4, "java_core": 3, "jdbc": 2, "jvm": 5, "security": 3, "spring": 5, "kafka": 3, "orm": 2, "ci_cd": 2, "helm": 1, "microservice": 5}',
  '{"data_base": 4, "build_tools": 4, "testing": 3, "vcs": 2, "kubernetes": 5, "docker": 4, "java_core": 3, "jdbc": 2, "jvm": 5, "security": 3, "spring": 5, "kafka": 3, "orm": 2, "ci_cd": 2, "helm": 1, "microservice": 5}',
   '2023-01-29 12:00:00', 'true'),
       ('{"data_base": 4, "build_tools": 4, "testing": 3, "vcs": 2, "kubernetes": 5, "docker": 4, "java_core": 3, "jdbc": 2, "jvm": 5, "security": 3, "spring": 5, "kafka": 3, "orm": 2, "ci_cd": 2, "helm": 1, "microservice": 5}',
       '{"data_base": 4, "build_tools": 4, "testing": 3, "vcs": 2, "kubernetes": 5, "docker": 4, "java_core": 3, "jdbc": 2, "jvm": 5, "security": 3, "spring": 5, "kafka": 3, "orm": 2, "ci_cd": 2, "helm": 1, "microservice": 5}',
       '{"data_base": 4, "build_tools": 4, "testing": 3, "vcs": 2, "kubernetes": 5, "docker": 4, "java_core": 3, "jdbc": 2, "jvm": 5, "security": 3, "spring": 5, "kafka": 3, "orm": 2, "ci_cd": 2, "helm": 1, "microservice": 5}',
        '2023-01-31 00:00:00', 'true'),
       ('{"data_base": 4, "build_tools": 4, "testing": 3, "vcs": 2, "kubernetes": 5, "docker": 4, "java_core": 3, "jdbc": 2, "jvm": 5, "security": 3, "spring": 5, "kafka": 3, "orm": 2, "ci_cd": 2, "helm": 1, "microservice": 5}',
       '{"data_base": 4, "build_tools": 4, "testing": 3, "vcs": 2, "kubernetes": 5, "docker": 4, "java_core": 3, "jdbc": 2, "jvm": 5, "security": 3, "spring": 5, "kafka": 3, "orm": 2, "ci_cd": 2, "helm": 1, "microservice": 5}',
       '{"data_base": 4, "build_tools": 4, "testing": 3, "vcs": 2, "kubernetes": 5, "docker": 4, "java_core": 3, "jdbc": 2, "jvm": 5, "security": 3, "spring": 5, "kafka": 3, "orm": 2, "ci_cd": 2, "helm": 1, "microservice": 5}',
        '2023-01-31 00:00:00', 'true');

insert into skills (name)
values ('data_base'),
       ('build_tools'),
       ('testing'),
       ('vcs'),
       ('kubernetes'),
       ('docker'),
       ('java_core'),
       ('jdbc'),
       ('jvm'),
       ('security'),
       ('spring'),
       ('kafka'),
       ('orm'),
       ('ci_cd'),
       ('helm'),
       ('microservice');

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