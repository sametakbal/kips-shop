CREATE TABLE product_category
(
    id         BIGSERIAL PRIMARY KEY,
    name       VARCHAR(255) NOT NULL,
    parent_id  BIGINT,

    created_at TIMESTAMP    NOT NULL,
    updated_at TIMESTAMP,

    CONSTRAINT fk_product_category_parent
        FOREIGN KEY (parent_id)
            REFERENCES product_category (id)
            ON DELETE CASCADE
);
