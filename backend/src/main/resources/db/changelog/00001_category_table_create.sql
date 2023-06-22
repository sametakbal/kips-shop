CREATE TABLE IF NOT EXISTS category
(
    id         serial primary key  not null,
    name       varchar(255)        not null,
    slug       varchar(255) unique not null,
    parent_id  int,
    created_at timestamp default now(),
    updated_at timestamp default now()
);

INSERT INTO category(id, name, slug)
VALUES (1, 'Electronics', 'electronics');

select setval('category_id_seq', (select max(id) from category));