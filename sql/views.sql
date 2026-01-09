CREATE OR REPLACE VIEW v_models AS
SELECT 
    mod.id AS model_id,
    mod.name AS model_name,
    mod.location AS model_location,
    mod.url AS model_url,
    p.id AS product_id,
    p.name AS product_name,
    m.id AS matiere_id,
    m.name AS matiere_name,
    c.id AS category_id,
    c.name AS category_name
FROM t_models mod
LEFT JOIN t_products p ON mod.id_product = p.id
LEFT JOIN t_matieres m ON p.id_matiere = m.id
LEFT JOIN t_products_categories pc ON p.id = pc.id_product
LEFT JOIN t_categories c ON pc.id_category = c.id;
