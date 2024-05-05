create table if not exists employees
(
  id serial primary key,
  name varchar(255) not null,
  surname varchar(255) null,
  manager_id integer null,
  expert_id integer null,
  date_of_next_assessment timestamp null,
  login varchar(255) not null unique,
  password varchar(255) not null
);

create table if not exists assessments
(
  id serial primary key,
  own_assessment integer null,
  expert_assessment integer null,
  manager_assessment integer null,
  final_assessment integer null,
  date_of_assessment timestamp null,
  is_active boolean not null
);

create table if not exists matrix
(
  id serial primary key,
  who varchar(255) not null,
  data_base integer null,
  build_tools integer null,
  testing integer null,
  vcs integer null,
  kubernetes integer null,
  docker integer null,
  java_core integer null,
  jdbc integer null,
  jvm integer null,
  security integer null,
  spring integer null,
  kafka integer null,
  orm integer null,
  ci_cd integer null,
  helm integer null,
  microservice integer null,
);


create table if not exists employees_assessments
(
  employee_id integer not null,
  assessment_id integer not null,
  primary key (employee_id, assessment_id),
  constraint fk_employees_assessment_employees foreign key (employee_id) references employees (id) on delete cascade on update no action,
  constraint fk_employees_assessment_assessment foreign key (assessment_id) references assessments (id) on delete cascade on update no action
);


create table if not exists employees_roles
(
  employee_id integer not null,
  role varchar(255) not null,
  primary key(employee_id, role),
  constraint fk_employees_roles_employees foreign key (employee_id) references employees (id) on delete cascade on update no action
);

create table if not exists assessments_matrix
(
  assessment_id integer not null,
  matrix_id integer not null,
  primary key(assessment_id, matrix_id),
  constraint fk_assessments_matrix_assessment foreign key (assessment_id) references assessments (id) on delete cascade on update no action
  constraint fk_assessments_matrix_matrix foreign key (matrix_id) references matrix (id) on delete cascade on update no action
);