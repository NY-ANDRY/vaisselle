
CREATE TABLE t_users (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);


INSERT INTO t_users(name) VALUES('abc');
INSERT INTO t_users(name) VALUES('def');
INSERT INTO t_users(name) VALUES('ghi');
