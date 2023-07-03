CREATE TABLE IF NOT EXISTS app_user
(
    id         serial,
    first_name varchar(255) not null,
    last_name  varchar(255) not null,
    email      varchar(255) not null unique,
    password   varchar(255) not null unique,
    urole      varchar(16),
    locked     boolean                  default false,
    enabled    boolean                  default false,
    login_error_count smallint default 0,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP,
    primary key (id)
);

CREATE TABLE IF NOT EXISTS token
(
    id         serial,
    token      varchar(255) not null unique,
    token_type varchar(255) not null,
    revoked    boolean                  default false,
    expired    boolean                  default false,
    user_id    int,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP,
    CONSTRAINT fk_user_id FOREIGN KEY (user_id)
        REFERENCES app_user (id),
    primary key (id)
);

CREATE TABLE IF NOT EXISTS confirmation_token
(
    id         serial,
    token  varchar(6) not null,
    user_id      int,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    enabled_at TIMESTAMP,
    CONSTRAINT fk_user_user_id FOREIGN KEY (user_id)
        REFERENCES app_user (id),
    primary key (id)
);
