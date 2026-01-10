CREATE OR REPLACE VIEW v_products AS
SELECT
    p.id           AS product_id,
    p.name         AS product_name,
    p.location     AS product_location,
    p.url          AS product_url,

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

LEFT JOIN t_models_types mt
       ON m.id = mt.id_model

LEFT JOIN t_types t
       ON mt.id_type = t.id

LEFT JOIN t_sizes s
       ON p.id_size = s.id

LEFT JOIN t_products_colors pc
       ON p.id = pc.id_product

LEFT JOIN t_colors cl
       ON pc.id_color = cl.id;
