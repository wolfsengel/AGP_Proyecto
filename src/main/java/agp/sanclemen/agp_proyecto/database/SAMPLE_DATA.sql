-- Sample data for CATEGORY table
INSERT INTO CATEGORY (NAME, DESCRIPTION) VALUES
('Electronics', 'Electronic gadgets and devices'),
('Clothing', 'Fashionable clothing items'),
('Books', 'Various genres of books'),
('Home Decor', 'Decorative items for home'),
('Sports', 'Sports equipment and accessories'),
('Beauty', 'Beauty and grooming products'),
('Toys', 'Toys for all ages'),
('Furniture', 'Furniture for home and office'),
('Outdoor', 'Outdoor and recreational items'),
('Jewelry', 'Elegant jewelry pieces');

-- Sample data for CUSTOMER table
INSERT INTO CUSTOMER (NAME, PASSWORD, LAST_UPDATED, REGISTRATION_DATE) VALUES
('John Doe', 'password123', '2024-02-01 14:00:00', '2024-01-15'),
('Jane Smith', 'securepass456', '2024-02-02 16:30:00', '2024-02-10'),
('Michael Johnson', 'customerpass789', '2024-02-03 18:45:00', '2024-02-18'),
('Emily White', 'letmeinpass', '2024-02-04 21:00:00', '2024-03-05'),
('David Brown', 'mypassword', '2024-02-05 09:15:00', '2024-03-20'),
('Sarah Miller', 'sarahpass', '2024-02-06 11:30:00', '2024-04-02'),
('Robert Davis', 'secure1234', '2024-02-07 13:45:00', '2024-04-15'),
('Olivia Harris', 'olivia123', '2024-02-08 16:00:00', '2024-05-01'),
('Daniel Wilson', 'danielpass', '2024-02-09 18:15:00', '2024-05-15'),
('Emma Turner', 'emma456', '2024-02-10 20:30:00', '2024-06-01');

-- Sample data for PRODUCT table
INSERT INTO PRODUCT (NAME, DESCRIPTION, PRICE, STOCK_QTY, LAST_UPDATED, CATEGORY_ID) VALUES
('Smartphone', 'Latest model with advanced features', 599.99, 50, '2024-02-01 08:30:00', 1),
('Laptop', 'Powerful laptop for professional use', 399.99, 30, '2024-02-02 10:45:00', 1),
('T-Shirt', 'Comfortable cotton T-shirt', 19.99, 100, '2024-02-03 12:15:00', 2),
('Jeans', 'Classic denim jeans', 49.99, 80, '2024-02-04 14:20:00', 2),
('Mystery Novel', 'Thrilling mystery novel', 14.99, 120, '2024-02-05 16:40:00', 3),
('Cookbook', 'Collection of delicious recipes', 24.99, 80, '2024-02-06 18:00:00', 3),
('Cushion', 'Soft and decorative cushion', 9.99, 50, '2024-02-07 20:30:00', 4),
('Table Lamp', 'Elegant table lamp for home', 29.99, 40, '2024-02-08 22:45:00', 4),
('Soccer Ball', 'High-quality soccer ball', 19.99, 30, '2024-02-09 09:10:00', 5),
('Yoga Mat', 'Comfortable yoga mat for workouts', 14.99, 40, '2024-02-10 11:25:00', 5);

-- Sample data for CART table
INSERT INTO CART (CUSTOMER_ID, NAME) VALUES
(1, 'Shopping Cart 1'),
(2, 'Shopping Cart 2'),
(3, 'Shopping Cart 3'),
(4, 'Shopping Cart 4'),
(5, 'Shopping Cart 5'),
(6, 'Shopping Cart 6'),
(7, 'Shopping Cart 7'),
(8, 'Shopping Cart 8'),
(9, 'Shopping Cart 9'),
(10, 'Shopping Cart 10');

-- Sample data for CART_ITEM table
INSERT INTO CART_ITEM (CART_ID, PRODUCT_ID, ITEM_QTY, LAST_UPDATED) VALUES
(1, 2, 3, '2024-02-01 08:30:00'),
(2, 1, 2, '2024-02-02 10:45:00'),
(3, 3, 1, '2024-02-03 12:15:00'),
(4, 2, 4, '2024-02-04 14:20:00'),
(5, 1, 2, '2024-02-05 16:40:00'),
(6, 4, 3, '2024-02-06 18:00:00'),
(7, 2, 1, '2024-02-07 20:30:00'),
(8, 3, 2, '2024-02-08 22:45:00'),
(9, 1, 5, '2024-02-09 09:10:00'),
(10, 2, 2, '2024-02-10 11:25:00');
