CREATE TABLE t_users (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE t_sizes (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE t_colors (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE t_products_colors (
    id SERIAL PRIMARY KEY,
    id_product BIGINT NOT NULL,
    id_color BIGINT NOT NULL,
    FOREIGN KEY (id_product) REFERENCES t_products(id),
    FOREIGN KEY (id_color) REFERENCES t_colors(id)
);

ALTER TABLE t_products ADD COLUMN id_size BIGINT REFERENCES t_sizes(id);

ALTER TABLE t_models ADD COLUMN description TEXT;
