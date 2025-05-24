CREATE TABLE app_user (
                          id BIGSERIAL PRIMARY KEY,
                          created_at TIMESTAMP NOT NULL,
                          updated_at TIMESTAMP,
                          username VARCHAR(255) NOT NULL UNIQUE,
                          email VARCHAR(255) NOT NULL UNIQUE,
                          password VARCHAR(255) NOT NULL
);

CREATE TABLE app_user_roles (
                                id BIGSERIAL PRIMARY KEY,
                                created_at TIMESTAMP NOT NULL,
                                updated_at TIMESTAMP,
                                name VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE user_roles (
                            user_id BIGINT NOT NULL,
                            role_id BIGINT NOT NULL,
                            PRIMARY KEY (user_id, role_id),
                            CONSTRAINT fk_user_roles_user FOREIGN KEY (user_id) REFERENCES app_user(id) ON DELETE CASCADE,
                            CONSTRAINT fk_user_roles_role FOREIGN KEY (role_id) REFERENCES app_user_roles(id) ON DELETE CASCADE
);
