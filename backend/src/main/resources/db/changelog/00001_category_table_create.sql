CREATE TABLE IF NOT EXISTS category(
    id serial primary key not null,
    name varchar(255) not null,
    slug varchar(255) unique not null,
    parent_id int,
    created_at timestamp default now(),
    updated_at timestamp default now()
);

INSERT INTO category(name,slug) VALUES ('Clothing','clothing');
INSERT INTO category(name,slug) VALUES ('Shoes','shoes');
INSERT INTO category(name,slug) VALUES ('Electronics','electronics');
INSERT INTO category(name,slug) VALUES ('Books','books');
INSERT INTO category(name,slug) VALUES ('Movies','movies');
INSERT INTO category(name,slug) VALUES ('Music','music');
INSERT INTO category(name,slug) VALUES ('Games','games');