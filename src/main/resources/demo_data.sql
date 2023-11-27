insert into employees (name, login, password)
values ('John Doe', 'johndoe@gmail.com', '$2a$10$Xl0yhvzLIaJCDdKBS0Lld.ksK7c2Zytg/ZKFdtIYYQUv8rUfvCR4W'),
       ('Mike Smith', 'mikesmith@yahoo.com', '$2a$10$fFLij9aYgaNCFPTL9WcA/uoCRukxnwf.vOQ8nrEEOskrCNmGsxY7m');

insert into assessments (own_assessment, expert_assessment, manager_assessment, date_of_assessment, is_active)
values (3, 4, 4, '2023-01-29 12:00:00', 'true'),
       (5, 3, 4, '2023-01-31 00:00:00', 'false'),
       (5, 3, 3, null, 'false'),
       (4, 2, 1, '2023-02-01 00:00:00', 'true');

insert into employees_assessments (assessment_id, employee_id)
values (1, 2),
       (2, 2),
       (3, 2),
       (4, 1);

insert into employees_roles (employee_id, role)
values (1, 'ROLE_ADMIN'),
       (1, 'ROLE_USER'),
       (2, 'ROLE_MANAGER');