create table comment (
  id        bigserial not null,
  author    varchar(255),
  comment   varchar(255),
  date      varchar(255),
  level_id  int8,
  parent_id int8,
  theme_id  int8,

primary key (id)
);

create table theme (
  id          bigserial not null,
  date        varchar(255),
  description varchar(255),
  theme_title varchar(255),
  user_id     int8,

  primary key (id)
);

create table user_role (
  user_id int8 not null,
  roles   varchar(255)
);

create table usr (
  id        bigserial not null,
  active    boolean not null,
  email     varchar(255),
  password  varchar(255),
  user_name varchar(255),

  primary key (id)
);

alter table comment
  add constraint FKp6vsi2thuqig86bmnpe095bnj foreign key (theme_id) references theme;
alter table theme
  add constraint FK9xkfafg8aa59l0xb04wu6aess foreign key (user_id) references usr;
alter table user_role
  add constraint FKfpm8swft53ulq2hl11yplpr5 foreign key (user_id) references usr;

INSERT INTO usr (active, password, user_name, email) values (true, '1111', 'admin', 'mail@mail.com');
INSERT INTO user_role (user_id, roles) values (1, 'ADMIN');