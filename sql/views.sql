CREATE OR REPLACE VIEW v_products AS
SELECT
    p.id                      AS product_id,
    p.name                    AS product_name,
    p.description             AS product_description,
    p.location                AS product_location,
    p.deleted_at,

    m.id                      AS model_id,
    m.name                    AS model_name,
    m.description             AS model_description,

    c.id                      AS category_id,
    c.name                    AS category_name,

    t.id                      AS type_id,
    t.name                    AS type_name,


    col.id                    AS color_id,
    col.name                  AS color_name,

    s.id                      AS size_id,
    s.name                    AS size_name,

    -- image par défaut de la variante
    pi.url                   AS product_image_url

FROM t_products p   
LEFT JOIN t_models m ON m.id = p.id_model
LEFT JOIN t_categories c ON c.id = m.id_category
LEFT JOIN t_types t ON t.id = m.id_type

LEFT JOIN t_colors col ON col.id = p.id_color
LEFT JOIN t_sizes s ON s.id = p.id_size

LEFT JOIN t_product_images pi ON pi.id_product = p.id AND pi.is_default = true;
