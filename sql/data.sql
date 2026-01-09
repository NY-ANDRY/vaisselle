-- Vider et réinitialiser chaque table individuellement

TRUNCATE t_products_categories RESTART IDENTITY CASCADE;
TRUNCATE t_models RESTART IDENTITY CASCADE;
TRUNCATE t_products RESTART IDENTITY CASCADE;
TRUNCATE t_matieres RESTART IDENTITY CASCADE;
TRUNCATE t_categories RESTART IDENTITY CASCADE;
TRUNCATE t_users RESTART IDENTITY CASCADE;

-- t_categories
INSERT INTO t_categories(name) VALUES
('Assiettes'),
('Couverts'),
('Verres');

-- t_matieres
INSERT INTO t_matieres(name) VALUES
('Porcelaine'),
('Acier inoxydable'),
('Verre'),
('Bois');

-- t_products
INSERT INTO t_products(name, id_matiere) VALUES
('Assiette plate', 1),
('Assiette creuse', 1),
('Couteau', 2),
('Fourchette', 2),
('Cuillère', 2),
('Verre à eau', 3),
('Verre à vin', 3),
('Cuillère en bois', 4),
('Tasse', 1),
('Carafe', 3);

-- t_products_categories
INSERT INTO t_products_categories(id_category, id_product) VALUES
(1, 1), -- Assiette plate -> Assiettes
(1, 2), -- Assiette creuse -> Assiettes
(2, 3), -- Couteau -> Couverts
(2, 4), -- Fourchette -> Couverts
(2, 5), -- Cuillère -> Couverts
(3, 6), -- Verre à eau -> Verres
(3, 7), -- Verre à vin -> Verres
(2, 8), -- Cuillère en bois -> Couverts
(3, 9), -- Tasse -> Verres
(3, 10); -- Carafe -> Verres

-- t_models
INSERT INTO t_models(location, name, url, id_product) VALUES
(1.0, 'Assiette plate Classique', '/uploads/test/assiette.jpg', 1),
(1.1, 'Assiette plate Design', '/uploads/test/assiette-sm.jpg', 1),
(1.2, 'Assiette creuse Profonde', '/uploads/test/assiette.jpg', 2),
(2.0, 'Couteau Inox', '/uploads/test/couteau.jpg', 3),
(2.1, 'Couteau Grand', '/uploads/test/couteau-xl.jpg', 3),
(2.2, 'Fourchette Table', '/uploads/test/fourchette.jpg', 4),
(2.3, 'Fourchette Dessert', '/uploads/test/fourchette-sm.jpg', 4),
(2.4, 'Cuillère Soupe', '/uploads/test/cuillere-sm.jpg', 5),
(3.0, 'Verre à eau Standard', '/uploads/test/verre.jpg', 6),
(3.1, 'Verre à eau Petit', '/uploads/test/verre-sm.jpg', 6),
(3.2, 'Verre à vin Élégant', '/uploads/test/verre-xs.jpg', 7),
(2.5, 'Cuillère bois Cuisine', '/uploads/test/cuillere-sm.jpg', 8),
(3.3, 'Tasse Café', '/uploads/test/tasse.jpg', 9),
(3.4, 'Tasse Thé', '/uploads/test/tasse-sm.jpg', 9),
(3.5, 'Carafe Eau', '/uploads/test/caraffe.jpg', 10),
(3.6, 'Carafe Vin', '/uploads/test/carafe-sm.jpg', 10);

-- t_users
INSERT INTO t_users(img, name) VALUES
('img/user1.png', 'Alice'),
('img/user2.png', 'Bob'),
('img/user3.png', 'Charlie');
