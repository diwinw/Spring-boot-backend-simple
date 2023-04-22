CREATE TABLE packages (
  package_id SERIAL PRIMARY KEY,
  package_name VARCHAR(255),
  price_per_unit INTEGER,
  create_date TIMESTAMP DEFAULT NOW()
);

INSERT INTO packages (package_name, price_per_unit)
VALUES
  ('Package 1', 10),
  ('Package 2', 20),
  ('Package 3', 30),
  ('Package 4', 40),
  ('Package 5', 50),
  ('Package 6', 60),
  ('Package 7', 70),
  ('Package 8', 80),
  ('Package 9', 90),
  ('Package 10', 100),
  ('Package 11', 110),
  ('Package 12', 120),
  ('Package 13', 130),
  ('Package 14', 140),
  ('Package 15', 150),
  ('Package 16', 160),
  ('Package 17', 170),
  ('Package 18', 180),
  ('Package 19', 190),
  ('Package 20', 200);
