create table users (
  id serial,
  username varchar,
  fio varchar
);

create table logins (
  id serial,
  access_date timestamp,
  user_id integer,
  application varchar
);
