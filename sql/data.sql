    TRUNCATE TABLE 
        t_product_images,
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

    INSERT INTO t_models (name, id_category, id_type, description) VALUES
    ('Assiette blanche classique', 1, 1, 'Une assiette classique en porcelaine blanche, parfaite pour tous les jours.'),
    ('Assiette dorée', 3, 1, 'Assiette avec bordu dorée, idéale pour les grandes occasions.'),
    ('Verre à jus transparent', 1, 2, 'Verre simple et élégant pour vos jus de fruits.'),
    ('Verre whisky cristal', 3, 3, 'Verre à whisky en cristal taillé main.'),
    ('Tasse en porcelaine', 2, 4, 'Tasse raffinée pour vos thés et cafés.'),
    ('Carafe en verre', 2, 5, 'Carafe design pour servir l''eau ou le vin.');


    INSERT INTO t_sizes (name) VALUES
    ('Small'),
    ('Medium'),
    ('Large');

    INSERT INTO t_colors (name) VALUES
    ('White'),
    ('Gold'),
    ('Transparent'),
    ('Silver');

    INSERT INTO t_products (name, location, id_product, id_size, id_color) VALUES
    ('Assiette blanche – Série A', 12.5, 1, 2, 1),
    ('Assiette dorée – Série Luxe', 45.0, 2, 3, 2),
    ('Verre jus – Lot 6', 18.0, 3, 1, 3),
    ('Verre whisky cristal – Unité', 60.0, 4, 1, 4),
    ('Tasse porcelaine – Café', 22.0, 5, 1, 1),
    ('Carafe en verre – Cuisine', 16.5, 6, 3, 2),
    ('Assiette blanche – Série B', 10.0, 1, 1, 3),
    ('Assiette blanche – Série C', 15.0, 1, 3, 4);

    INSERT INTO t_product_images (id_product, url, is_default) VALUES
    (1, '/uploads/test/assiette.jpg', TRUE),
    (2, '/uploads/test/assiette.jpg', TRUE),
    (3, '/uploads/test/verre.jpg', TRUE),
    (4, '/uploads/test/verre-sm.jpg', TRUE),
    (5, '/uploads/test/tasse.jpg', TRUE),
    (6, '/uploads/test/caraffe.jpg', TRUE),
    (7, '/uploads/test/assiette.jpg', TRUE),
    (8, '/uploads/test/assiette.jpg', TRUE);

    INSERT INTO t_users (name, img) VALUES
    ('Admin', 'admin.png'),
    ('Client Test', 'client.png');
