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
    description TEXT
);

CREATE TABLE t_colors (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE t_sizes (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE t_products (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    location DOUBLE PRECISION NOT NULL,
    discount DOUBLE PRECISION,
    nb_discount DOUBLE PRECISION,
    id_model INT REFERENCES t_models(id),
    id_color INT REFERENCES t_colors(id),
    id_size INT REFERENCES t_sizes(id),
    deleted_at TIMESTAMP
);

CREATE TABLE t_product_images (
    id SERIAL PRIMARY KEY,
    id_product INT REFERENCES t_products(id),
    url VARCHAR(255) NOT NULL,
    is_default BOOLEAN DEFAULT FALSE
);

CREATE TABLE t_users (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    img VARCHAR(255)
);

CREATE TABLE t_area (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    cost DOUBLE PRECISION NOT NULL
);

CREATE TABLE t_carts (
    id SERIAL PRIMARY KEY,
    id_user INT REFERENCES t_users(id),
    id_area INT REFERENCES t_area(id),
    date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE t_cart_details (
    id SERIAL PRIMARY KEY,
    id_cart INT REFERENCES t_carts(id),
    id_product INT REFERENCES t_products(id),
    qtt INT NOT NULL
);

CREATE TABLE t_movement_types (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    type INT NOT NULL
);

CREATE TABLE t_movements (
    id SERIAL PRIMARY KEY,
    id_product INT REFERENCES t_products(id),
    id_type INT REFERENCES t_movement_types(id),
    qtt DOUBLE PRECISION NOT NULL
);

CREATE TABLE t_colors_up (
    id SERIAL PRIMARY KEY,
    id_color INT REFERENCES t_colors(id),
    value DOUBLE PRECISION NOT NULL
);

CREATE TABLE "t_discountCart" (
    id SERIAL PRIMARY KEY,
    nb INT NOT NULL,
    discount DOUBLE PRECISION NOT NULL
);

CREATE TABLE t_promotions (
    id SERIAL PRIMARY KEY,
    description TEXT,
    date DATE NOT NULL,
    promotion DOUBLE PRECISION NOT NULL
);

CREATE TABLE t_products_promotions (
    id SERIAL PRIMARY KEY,
    id_product INT REFERENCES t_products(id),
    id_promotion INT REFERENCES t_promotions(id)
);