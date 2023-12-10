create table if not exists book
(
    id             serial primary key,
    name           varchar(50) not null,
    author         varchar(50) not null,
    isbn           varchar(13) not null,
    source         varchar(30) not null,
    book_status    varchar(30) not null,
    current_stock  integer,
    total_stock    integer,
    published_date timestamp
    );