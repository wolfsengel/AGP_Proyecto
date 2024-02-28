-- Sample data for CATEGORY table
INSERT INTO CATEGORY (ID, NAME, DESCRIPTION) VALUES
(1, 'Electronics', 'Electronic gadgets and devices'),
(2, 'Clothing', 'Fashionable clothing items'),
(3, 'Books', 'Various genres of books'),
(4, 'Home Decor', 'Decorative items for home'),
(5, 'Sports', 'Sports equipment and accessories'),
(6, 'Beauty', 'Beauty and grooming products'),
(7, 'Toys', 'Toys for all ages'),
(8, 'Furniture', 'Furniture for home and office'),
(9, 'Outdoor', 'Outdoor and recreational items'),
(10, 'Jewelry', 'Elegant jewelry pieces');

-- Sample data for CUSTOMER table
INSERT INTO CUSTOMER (ID, NAME, PASSWORD, LAST_UPDATED, REGISTRATION_DATE) VALUES
(101, 'John Doe', 'password123', '2024-02-01 14:00:00', '2024-01-15'),
(102, 'Jane Smith', 'securepass456', '2024-02-02 16:30:00', '2024-02-10'),
(103, 'Michael Johnson', 'customerpass789', '2024-02-03 18:45:00', '2024-02-18'),
(104, 'Emily White', 'letmeinpass', '2024-02-04 21:00:00', '2024-03-05'),
(105, 'David Brown', 'mypassword', '2024-02-05 09:15:00', '2024-03-20'),
(106, 'Sarah Miller', 'sarahpass', '2024-02-06 11:30:00', '2024-04-02'),
(107, 'Robert Davis', 'secure1234', '2024-02-07 13:45:00', '2024-04-15'),
(108, 'Olivia Harris', 'olivia123', '2024-02-08 16:00:00', '2024-05-01'),
(109, 'Daniel Wilson', 'danielpass', '2024-02-09 18:15:00', '2024-05-15'),
(110, 'Emma Turner', 'emma456', '2024-02-10 20:30:00', '2024-06-01');

-- Sample data for PRODUCT table
INSERT INTO PRODUCT (ID, NAME, DESCRIPTION, PRICE, STOCK_QTY, LAST_UPDATED, CATEGORY_ID) VALUES
(1, 'Smartphone', 'Latest model with advanced features', 599.99, 50, '2024-02-01 08:30:00', 1),
(2, 'Laptop', 'Powerful laptop for professional use', 399.99, 30, '2024-02-02 10:45:00', 1),
(3, 'T-Shirt', 'Comfortable cotton T-shirt', 19.99, 100, '2024-02-03 12:15:00', 2),
(4, 'Jeans', 'Classic denim jeans', 49.99, 80, '2024-02-04 14:20:00', 2),
(5, 'Mystery Novel', 'Thrilling mystery novel', 14.99, 120, '2024-02-05 16:40:00', 3),
(6, 'Cookbook', 'Collection of delicious recipes', 24.99, 80, '2024-02-06 18:00:00', 3),
(7, 'Cushion', 'Soft and decorative cushion', 9.99, 50, '2024-02-07 20:30:00', 4),
(8, 'Table Lamp', 'Elegant table lamp for home', 29.99, 40, '2024-02-08 22:45:00', 4),
(9, 'Soccer Ball', 'High-quality soccer ball', 19.99, 30, '2024-02-09 09:10:00', 5),
(10, 'Yoga Mat', 'Comfortable yoga mat for workouts', 14.99, 40, '2024-02-10 11:25:00', 5);

-- Sample data for CART table
INSERT INTO CART (ID, CUSTOMER_ID, NAME) VALUES
(1, 101, 'Shopping Cart 1'),
(2, 102, 'Shopping Cart 2'),
(3, 103, 'Shopping Cart 3'),
(4, 104, 'Shopping Cart 4'),
(5, 105, 'Shopping Cart 5'),
(6, 106, 'Shopping Cart 6'),
(7, 107, 'Shopping Cart 7'),
(8, 108, 'Shopping Cart 8'),
(9, 109, 'Shopping Cart 9'),
(10, 110, 'Shopping Cart 10');

-- Sample data for CART_ITEM table
INSERT INTO PUBLIC.CART_ITEM (ID, CART_ID, PRODUCT_ID, ITEM_QTY, LAST_UPDATED) VALUES
(1, 1, 2, 3, '2024-02-01 08:30:00'),
(2, 2, 1, 2, '2024-02-02 10:45:00'),
(3, 3, 3, 1, '2024-02-03 12:15:00'),
(4, 4, 2, 4, '2024-02-04 14:20:00'),
(5, 5, 1, 2, '2024-02-05 16:40:00'),
(6, 6, 4, 3, '2024-02-06 18:00:00'),
(7, 7, 2, 1, '2024-02-07 20:30:00'),
(8, 8, 3, 2, '2024-02-08 22:45:00'),
(9, 9, 1, 5, '2024-02-09 09:10:00'),
(10, 10, 2, 2, '2024-02-10 11:25:00');
