    -- TRUNCATE TABLE 
    --     t_categories,
    --     t_colors,
    --     t_models,
    --     t_products,
    --     t_product_images,
    --     t_sizes,
    --     t_types,
    --     t_users
    -- RESTART IDENTITY CASCADE;

    -- INSERT INTO t_categories (name) VALUES
    -- ('Standard'),
    -- ('Premium'),
    -- ('Luxe');

    -- INSERT INTO t_types (name) VALUES
    -- ('Assiette'),
    -- ('Verre à jus'),
    -- ('Verre whisky'),
    -- ('Tasse'),
    -- ('Carafe');

    -- INSERT INTO t_models (name, id_category, id_type, description) VALUES
    -- ('Assiette blanche classique', 1, 1, 'Une assiette classique en porcelaine blanche, parfaite pour tous les jours.'),
    -- ('Assiette dorée', 3, 1, 'Assiette avec bordu dorée, idéale pour les grandes occasions.'),
    -- ('Verre à jus transparent', 1, 2, 'Verre simple et élégant pour vos jus de fruits.'),
    -- ('Verre whisky cristal', 3, 3, 'Verre à whisky en cristal taillé main.'),
    -- ('Tasse en porcelaine', 2, 4, 'Tasse raffinée pour vos thés et cafés.'),
    -- ('Carafe en verre', 2, 5, 'Carafe design pour servir l''eau ou le vin.');


    -- INSERT INTO t_sizes (name) VALUES
    -- ('Small'),
    -- ('Medium'),
    -- ('Large');

    -- INSERT INTO t_colors (name) VALUES
    -- ('White'),
    -- ('Gold'),
    -- ('Transparent'),
    -- ('Silver');

    -- INSERT INTO t_products (name, location, id_model) VALUES
    -- ('Assiette blanche – Série A', 12.5, 1),
    -- ('Assiette dorée – Série Luxe', 45.0, 2),
    -- ('Verre jus – Lot 6', 18.0, 3),
    -- ('Verre whisky cristal – Unité', 60.0, 4),
    -- ('Tasse porcelaine – Café', 22.0, 5),
    -- ('Carafe en verre – Cuisine', 16.5, 6),
    -- ('Assiette blanche – Série B', 10.0, 1),
    -- ('Assiette blanche – Série C', 15.0, 1);

    -- INSERT INTO t_product_images (id_product, url, is_default) VALUES
    -- (1, '/uploads/test/assiette.jpg', TRUE),
    -- (2, '/uploads/test/assiette.jpg', TRUE),
    -- (3, '/uploads/test/verre.jpg', TRUE),
    -- (4, '/uploads/test/verre-sm.jpg', TRUE),
    -- (5, '/uploads/test/tasse.jpg', TRUE),
    -- (6, '/uploads/test/caraffe.jpg', TRUE),
    -- (7, '/uploads/test/assiette.jpg', TRUE),
    -- (8, '/uploads/test/assiette.jpg', TRUE);

    -- INSERT INTO t_users (name, img) VALUES
    -- ('abc', '/uploads/test/user.jpg'),
    -- ('Admin', 'admin.png'),
    -- ('Client Test', 'client.png');

TRUNCATE TABLE 
    t_categories,
    t_colors,
    t_models,
    t_products,
    t_product_images,
    t_sizes,
    t_types,
    t_users
RESTART IDENTITY CASCADE;

-- Categories & Types
INSERT INTO t_categories (name) VALUES ('Standard'), ('Premium'), ('Luxe');

INSERT INTO t_types (name) VALUES ('Assiette'), ('Verre à jus'), ('Verre whisky'), ('Tasse'), ('Carafe');

-- Models
INSERT INTO t_models (name, id_category, id_type, description) VALUES
('Assiette blanche classique', 1, 1, 'Une assiette classique en porcelaine blanche, parfaite pour tous les jours.'),
('Assiette dorée', 3, 1, 'Assiette avec bordure dorée, idéale pour les grandes occasions.'),
('Verre à jus transparent', 1, 2, 'Verre simple et élégant pour vos jus de fruits.'),
('Verre whisky cristal', 3, 3, 'Verre à whisky en cristal taillé main.'),
('Tasse en porcelaine', 2, 4, 'Tasse raffinée pour vos thés et cafés.'),
('Carafe en verre', 2, 5, 'Carafe design pour servir l''eau ou le vin.');

-- Sizes & Colors
INSERT INTO t_sizes (name) VALUES ('Small'), ('Medium'), ('Large');

INSERT INTO t_colors (name) VALUES ('White'), ('Gold'), ('Transparent'), ('Silver');

-- Products : un produit par modèle+taille+couleur (id_color / id_model / id_size)
INSERT INTO t_products (name, location, id_color, id_model, id_size) VALUES
('Assiette blanche classique - Small', 10.0, 1, 1, 1),
('Assiette blanche classique - Medium', 12.5, 1, 1, 2),
('Assiette blanche classique - Large', 15.0, 1, 1, 3),

('Assiette dorée - Medium', 45.0, 2, 2, 2),
('Assiette dorée - Large', 55.0, 2, 2, 3),

('Verre à jus transparent - Small', 16.0, 3, 3, 1),
('Verre à jus transparent - Medium', 18.0, 3, 3, 2),

('Verre whisky cristal - Medium', 60.0, 3, 4, 2),

('Tasse en porcelaine - Small', 20.0, 1, 5, 1),
('Tasse en porcelaine - Medium', 22.0, 1, 5, 2),

('Carafe en verre - Medium', 16.5, 3, 6, 2);

-- Images (depuis /uploads/test)
INSERT INTO t_product_images (id_product, url, is_default) VALUES
(1, '/uploads/test/assiette-sm.jpg', TRUE),
(2, '/uploads/test/assiette.jpg', TRUE),
(3, '/uploads/test/assiette.jpg', TRUE),

(4, '/uploads/test/assiette.jpg', TRUE),
(5, '/uploads/test/assiette.jpg', TRUE),

(6, '/uploads/test/verre-xs.jpg', TRUE),
(7, '/uploads/test/verre-sm.jpg', TRUE),

(8, '/uploads/test/verre-sm.jpg', TRUE),

(9, '/uploads/test/tasse-sm.jpg', TRUE),
(10, '/uploads/test/tasse.jpg', TRUE),

(11, '/uploads/test/caraffe.jpg', TRUE);

-- Users
INSERT INTO t_users (name, img) VALUES
('abc', '/uploads/test/user.jpg'),
('Admin', 'admin.png'),
('Client Test', 'client.png');