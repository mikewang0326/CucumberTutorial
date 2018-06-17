CREATE TABLE vehicle
(
  plate TEXT UNIQUE PRIMARY KEY,
  type TEXT NOT NULL,
  make  TEXT NOT NULL,
  model  TEXT NOT NULL,
  manufacture_date DATE NOT NULL,
  fuel_type TEXT NOT NULL,
  user_id INTEGER NOT NULL,
  FOREIGN KEY(user_id) REFERENCES user(user_id)
);