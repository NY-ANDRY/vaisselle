CREATE OR REPLACE VIEW v_products AS
SELECT
    p.id           AS product_id,
    p.name         AS product_name,
    p.location     AS product_location,
    pi.url         AS product_url,

    m.id           AS model_id,
    m.name         AS model_name,
    m.description  AS model_description,

    c.id           AS category_id,
    c.name         AS category_name,

    t.id           AS type_id,
    t.name         AS type_name,

    s.id           AS size_id,
    s.name         AS size_name,

    cl.id          AS color_id,
    cl.name        AS color_name

FROM t_products p
LEFT JOIN t_models m
       ON p.id_product = m.id

LEFT JOIN t_categories c
       ON m.id_category = c.id

LEFT JOIN t_types t
       ON m.id_type = t.id

LEFT JOIN t_sizes s
       ON p.id_size = s.id

LEFT JOIN t_colors cl
       ON p.id_color = cl.id

LEFT JOIN t_product_images pi
       ON pi.id_product = p.id AND pi.is_default = TRUE;
