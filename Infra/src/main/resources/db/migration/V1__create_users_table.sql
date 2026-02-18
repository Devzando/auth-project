CREATE TABLE profiles (
    id UUID PRIMARY KEY,
    role VARCHAR(20) NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP
);

CREATE TABLE users (
    id UUID PRIMARY KEY,
    email VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(100),
    profile_id UUID NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP,

    CONSTRAINT fk_user_profile
        FOREIGN KEY (profile_id)
        REFERENCES profiles (id)
);

INSERT INTO profiles (id, role, created_at) VALUES
(gen_random_uuid(), 'ADMIN', CURRENT_TIMESTAMP),
(gen_random_uuid(), 'BASIC', CURRENT_TIMESTAMP);