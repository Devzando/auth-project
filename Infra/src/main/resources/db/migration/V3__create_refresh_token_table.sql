CREATE TABLE refresh_token (
    id UUID PRIMARY KEY,
    token VARCHAR(100) NOT NULL UNIQUE,
    user_id UUID NOT NULL,
    revoked BOOLEAN NOT NULL DEFAULT FALSE,
    expiry_date TIMESTAMP NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP,

    CONSTRAINT fk_refresh_token_user FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

CREATE INDEX idx_refresh_token_value ON refresh_token(token);
CREATE INDEX idx_refresh_token_user ON refresh_token(user_id);