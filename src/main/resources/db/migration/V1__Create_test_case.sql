create table test_cases
(
  id   SERIAL PRIMARY KEY,
  name varchar(100) not null,
  steps json
);