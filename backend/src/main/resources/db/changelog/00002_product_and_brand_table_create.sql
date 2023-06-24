CREATE TABLE IF NOT EXISTS brand
(
    id         serial PRIMARY KEY,
    name       VARCHAR(255) NOT NULL,
    created_at timestamp default now(),
    updated_at timestamp default now()
);

INSERT INTO brand (id,name) VALUES (1,'Apple');
INSERT INTO brand (id,name) VALUES (2,'Canon');
INSERT INTO brand (id,name) VALUES (3,'Sony');
INSERT INTO brand (id,name) VALUES (4,'Logitech');
INSERT INTO brand (id,name) VALUES (5,'Amazon');
INSERT INTO brand (id,name) VALUES (6,'Lenovo');

SELECT setval('brand_id_seq', (SELECT MAX(id) FROM brand));

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

INSERT INTO product (id,name,description,brand_id,category_id,price,count_in_stock,rating,num_reviews) VALUES (1,'Airpods Wireless Bluetooth Headphones','Bluetooth technology lets you connect it with compatible devices wirelessly High-quality AAC audio offers immersive listening experience Built-in microphone allows you to take calls while working',1,1,89.99,10,4.5,12);
INSERT INTO product (id,name,description,brand_id,category_id,price,count_in_stock,rating,num_reviews) VALUES (2,'iPhone 11 Pro 256GB Memory','Introducing the iPhone 11 Pro. A transformative triple-camera system that adds tons of capability without complexity. An unprecedented leap in battery life',1,1,599.99,7,4.0,8);
INSERT INTO product (id,name,description,brand_id,category_id,price,count_in_stock,rating,num_reviews) VALUES (3,'Cannon EOS 80D DSLR Camera','Characterized by versatile imaging specs, the Canon EOS 80D further clarifies itself using a pair of robust focusing systems and an intuitive design',2,1,929.99,5,3.0,12);
INSERT INTO product (id,name,description,brand_id,category_id,price,count_in_stock,rating,num_reviews) VALUES (4,'Sony Playstation 4 Pro White Version','The ultimate home entertainment center starts with PlayStation. Whether you are into gaming, HD movies, television, music',3,1,399.99,11,5,12);
INSERT INTO product (id,name,description,brand_id,category_id,price,count_in_stock,rating,num_reviews) VALUES (5,'Logitech G-Series Gaming Mouse','Get a better handle on your games with this Logitech LIGHTSYNC gaming mouse. The six programmable buttons allow customization for a smooth playing experience',4,1,49.99,7,3.5,10);
INSERT INTO product (id,name,description,brand_id,category_id,price,count_in_stock,rating,num_reviews) VALUES (6,'Amazon Echo Dot 3rd Generation','Meet Echo Dot - Our most popular smart speaker with a fabric design. It is our most compact smart speaker that fits perfectly into small space',5,1,29.99,0,4,12);

SELECT setval('product_id_seq', (SELECT MAX(id) FROM product));