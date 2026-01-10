ALTER TABLE t_models ADD COLUMN description TEXT;
ALTER TABLE t_models ADD COLUMN id_type BIGINT REFERENCES t_types(id);

CREATE TABLE t_product_images (
    id SERIAL PRIMARY KEY,
    id_product BIGINT NOT NULL REFERENCES t_products(id),
    url VARCHAR(255) NOT NULL,
    is_default BOOLEAN DEFAULT FALSE
);

ALTER TABLE t_products DROP COLUMN url;
