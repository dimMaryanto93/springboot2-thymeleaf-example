create table master_agama
(
  id               int          not null primary key auto_increment,
  name             varchar(64)  not null,
  description      text,
  create_date      timestamp    not null default now(),
  create_by        varchar(100) not null,
  last_update_date datetime,
  last_update_by   varchar(100)
) engine = InnoDB;


insert into master_agama(name, create_by)
values ('ISLAM', 'migration'),
       ('KRISTEN', 'migration'),
       ('HINDU', 'migration'),
       ('BUDHA', 'migration');
