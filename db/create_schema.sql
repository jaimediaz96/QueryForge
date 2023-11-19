-- users table
CREATE TABLE IF NOT EXISTS users (
    user_id SERIAL PRIMARY KEY,
    username VARCHAR(255) UNIQUE NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    last_login TIMESTAMP
);

-- queries table
CREATE TABLE IF NOT EXISTS queries (
    query_id SERIAL PRIMARY KEY,
    user_id INT NOT NULL,
    name VARCHAR(100) NOT NULL,
    query VARCHAR(100) NOT NULL,
    comment TEXT,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    last_update TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users (user_id)
);

-- comments table
CREATE TABLE IF NOT EXISTS comments (
    comment_id SERIAL PRIMARY KEY,
    query_id INT NOT NULL,
    user_id INT NOT NULL,
    comment_text TEXT NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (query_id) REFERENCES queries (query_id),
    FOREIGN KEY (user_id) REFERENCES users (user_id)
);
