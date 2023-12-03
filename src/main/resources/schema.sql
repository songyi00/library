CREATE TABLE IF NOT EXISTS BOOK(
    id              serial          PRIMARY KEY,
    name            varchar(50)     NOT NULL,
    author          varchar(50)     NOT NULL,
    isbn            long            NOT NULL,
    published_time  timestamp       NOT NULL
);