CREATE TABLE t_categories (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE t_types (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE t_models (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    id_category INT REFERENCES t_categories(id),
    id_type INT REFERENCES t_types(id),
    description TEXT,
    url VARCHAR(255)
);

CREATE TABLE t_products (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    location DOUBLE PRECISION,
    id_product INT REFERENCES t_models(id),
    deleted_at TIMESTAMP
);

CREATE TABLE t_product_images (
    id SERIAL PRIMARY KEY,
    id_product INT REFERENCES t_products(id),
    url VARCHAR(255) NOT NULL,
    is_default BOOLEAN DEFAULT FALSE
);

CREATE TABLE t_sizes (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE t_colors (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE t_product_variants (
    id SERIAL PRIMARY KEY,
    id_product INT REFERENCES t_products(id),
    id_color INT REFERENCES t_colors(id),
    id_size INT REFERENCES t_sizes(id)
);

CREATE TABLE t_users (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    img VARCHAR(255)
);