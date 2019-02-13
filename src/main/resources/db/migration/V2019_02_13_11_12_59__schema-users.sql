create table users
(
  id                varchar(64)  not null primary key,
  username          varchar(100) not null unique,
  password          varchar(100) not null,
  enabled           boolean               default false,
  created_date      timestamp    not null default now(),
  created_by        varchar(100) not null,
  last_updated_date datetime,
  last_updated_by   varchar(100)
) engine = InnoDB;

create table roles
(
  id          int          not null primary key auto_increment,
  role        varchar(100) not null unique,
  description text
) engine = InnoDB;

create table user_roles
(
  id      varchar(64) not null primary key,
  user_id varchar(64) not null,
  role_id int         not null
) engine = InnoDB;

alter table user_roles
  add constraint fk_user_roles_user_id foreign key (user_id)
    references users (id) on update cascade on delete cascade;

alter table user_roles
  add constraint fk_user_roles_role_id foreign key (role_id)
    references roles (id) on update cascade on delete cascade;
