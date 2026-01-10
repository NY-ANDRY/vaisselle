TRUNCATE TABLE 
    t_models_types,
    t_products_colors,
    t_products,
    t_models,
    t_types,
    t_categories,
    t_users,
    t_sizes,
    t_colors
RESTART IDENTITY CASCADE;

INSERT INTO t_categories (name) VALUES
('Standard'),
('Premium'),
('Luxe');

INSERT INTO t_types (name) VALUES
('Assiette'),
('Verre à jus'),
('Verre whisky'),
('Tasse'),
('Carafe');

INSERT INTO t_models (name, id_category, description) VALUES
('Assiette blanche classique', 1, 'Une assiette classique en porcelaine blanche, parfaite pour tous les jours.'),
('Assiette dorée', 3, 'Assiette avec bordu dorée, idéale pour les grandes occasions.'),
('Verre à jus transparent', 1, 'Verre simple et élégant pour vos jus de fruits.'),
('Verre whisky cristal', 3, 'Verre à whisky en cristal taillé main.'),
('Tasse en porcelaine', 2, 'Tasse raffinée pour vos thés et cafés.'),
('Carafe en verre', 2, 'Carafe design pour servir l''eau ou le vin.');

INSERT INTO t_models_types (id_model, id_type) VALUES
(1, 1), -- Assiette blanche → Assiette
(2, 1), -- Assiette dorée → Assiette
(3, 2), -- Verre jus → Verre à jus
(4, 3), -- Verre whisky cristal → Verre whisky
(5, 4), -- Tasse porcelaine → Tasse
(6, 5); -- Carafe en verre → Carafe

-- INSERT INTO t_products (name, location, url, id_product) VALUES
-- ('Assiette blanche – Série A', 12.5, '/uploads/test/assiette-removebg-preview.png', 1),
-- ('Assiette dorée – Série Luxe', 45.0, '/uploads/test/assiette-removebg-preview.png', 2),
-- ('Verre jus – Lot 6', 18.0, '/uploads/test/verre-removebg-preview.png', 3),
-- ('Verre whisky cristal – Unité', 60.0, '/uploads/test/verre-sm-removebg-preview.png', 4),
-- ('Tasse porcelaine – Café', 22.0, '/uploads/test/tasse-removebg-preview.png', 5),
-- ('Carafe en verre – Cuisine', 16.5, '/uploads/test/caraffe-removebg-preview.png', 6);

INSERT INTO t_sizes (name) VALUES
('Small'),
('Medium'),
('Large');

INSERT INTO t_colors (name) VALUES
('White'),
('Gold'),
('Transparent'),
('Silver');

INSERT INTO t_products (name, location, url, id_product, id_size) VALUES
('Assiette blanche – Série A', 12.5, '/uploads/test/assiette.jpg', 1, 2),
('Assiette dorée – Série Luxe', 45.0, '/uploads/test/assiette.jpg', 2, 3),
('Verre jus – Lot 6', 18.0, '/uploads/test/verre.jpg', 3, 1),
('Verre whisky cristal – Unité', 60.0, '/uploads/test/verre-sm.jpg', 4, 1),
('Tasse porcelaine – Café', 22.0, '/uploads/test/tasse.jpg', 5, 1),
('Carafe en verre – Cuisine', 16.5, '/uploads/test/caraffe.jpg', 6, 3),
('Assiette blanche – Série B', 10.0, '/uploads/test/assiette.jpg', 1, 1), -- Variente Small
('Assiette blanche – Série C', 15.0, '/uploads/test/assiette.jpg', 1, 3); -- Variente Large

INSERT INTO t_products_colors (id_product, id_color) VALUES
(1, 1), -- Assiette blanche -> White
(2, 2), -- Assiette dorée -> Gold
(3, 3), -- Verre jus -> Transparent
(4, 3), -- Verre whisky -> Transparent
(5, 1), -- Tasse -> White
(6, 3), -- Carafe -> Transparent
(7, 1), -- Assiette blanche Série B -> White
(8, 1), -- Assiette blanche Série C -> White
(8, 4); -- Assiette blanche Série C -> Silver (mixed)


INSERT INTO t_users (name, img) VALUES
('Admin', 'admin.png'),
('Client Test', 'client.png');
