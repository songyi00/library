create table if not exists account
(
    id       serial primary key,
    email    varchar(50) not null,
    password text        not null,
    name     varchar(50) not null
);