CREATE TABLE IF NOT EXISTS product_images(
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) UNIQUE NOT NULL,
    type VARCHAR(255) NOT NULL,
    path VARCHAR(255) NOT NULL,
    product_id INT NOT NULL,
    created_at TIMESTAMP DEFAULT now(),
    updated_at TIMESTAMP DEFAULT now(),
    FOREIGN KEY (product_id) REFERENCES product(id) ON DELETE CASCADE
);

INSERT INTO product_images(name, type, path, product_id) VALUES('airpods.jpg', 'image/jpeg', './uploads/product/1/airpods.jpg', 1);
INSERT INTO product_images(name, type, path, product_id) VALUES('phone.jpg', 'image/jpeg', './uploads/product/2/phone.jpg', 2);
INSERT INTO product_images(name, type, path, product_id) VALUES('camera.jpg', 'image/jpeg', './uploads/product/3/camera.jpg', 3);
INSERT INTO product_images(name, type, path, product_id) VALUES('playstation.jpg', 'image/jpeg', './uploads/product/4/playstation.jpg', 4);
INSERT INTO product_images(name, type, path, product_id) VALUES('mouse.jpg', 'image/jpeg', './uploads/product/5/mouse.jpg', 5);
INSERT INTO product_images(name, type, path, product_id) VALUES('alexa.jpg', 'image/jpeg', './uploads/product/6/alexa.jpg', 6);
