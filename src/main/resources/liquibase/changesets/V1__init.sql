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
  own_assessment json null,
  expert_assessment json null,
  manager_assessment json null,
  final_assessment json null,
  date_of_assessment timestamp null,
  is_active boolean not null
);

create table if not exists skills
(
  id serial primary key,
  name varchar(255) not null
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
