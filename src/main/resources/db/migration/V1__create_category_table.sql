CREATE TABLE category
(
    id         BIGSERIAL PRIMARY KEY,
    name       VARCHAR(255) NOT NULL,
    parent_id  BIGINT,

    created_at TIMESTAMP    NOT NULL,
    updated_at TIMESTAMP,

    CONSTRAINT fk_category_parent
        FOREIGN KEY (parent_id)
            REFERENCES category (id)
            ON DELETE CASCADE
);
