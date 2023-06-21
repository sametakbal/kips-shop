CREATE TABLE IF NOT EXISTS brand
(
    id         serial PRIMARY KEY,
    name       VARCHAR(255) NOT NULL,
    created_at timestamp default now(),
    updated_at timestamp default now()
);

INSERT INTO brand (name) VALUES ('Apple');
INSERT INTO brand (name) VALUES ('Microsoft');
INSERT INTO brand (name) VALUES ('Amazon');
INSERT INTO brand (name) VALUES ('Samsung');
INSERT INTO brand (name) VALUES ('Asus');
INSERT INTO brand (name) VALUES ('Lenovo');
INSERT INTO brand (name) VALUES ('Canon');

CREATE TABLE IF NOT EXISTS product
(
    id             serial PRIMARY KEY,
    name           VARCHAR(255)  NOT NULL,
    description    VARCHAR(1000) NOT NULL,
    brand_id       INT           NOT NULL,
    category_id    INT           NOT NULL,
    price          REAL         NOT NULL,
    count_in_stock INT           NOT NULL,
    rating         REAL          NOT NULL,
    num_reviews    INT           NOT NULL,
    CONSTRAINT fk_brand
        FOREIGN KEY (brand_id)
            REFERENCES brand (id)
);