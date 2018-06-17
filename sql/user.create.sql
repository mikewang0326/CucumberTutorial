CREATE TABLE user
(
  user_id   INTEGER PRIMARY KEY,
  firstname TEXT NOT NULL,
  lastname  TEXT NOT NULL,
  email     TEXT NOT NULL
    UNIQUE,
  password  TEXT NOT NULL
);