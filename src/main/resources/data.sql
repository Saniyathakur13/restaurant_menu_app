USE restaurant_db;

INSERT IGNORE INTO menu_items (name, description, price, category, is_vegetarian, image_url) VALUES
('Margherita Pizza', 'Classic tomato and mozzarella pizza with fresh basil', 12.99, 'Pizza', true, 'https://images.unsplash.com/photo-1565299624946-b28f40a0ae38?w=400'),
('Pepperoni Pizza', 'Spicy pepperoni with extra cheese', 14.99, 'Pizza', false, 'https://images.unsplash.com/photo-1628840042765-356cda07504e?w=400'),
('BBQ Chicken Pizza', 'Grilled chicken with BBQ sauce and onions', 15.99, 'Pizza', false, 'https://images.unsplash.com/photo-1595708684082-a173bb3a06c5?w=400'),
('Caesar Salad', 'Fresh romaine lettuce with Caesar dressing and croutons', 8.99, 'Salad', true, 'https://images.unsplash.com/photo-1546793665-c74683f339c1?w=400'),
('Greek Salad', 'Tomatoes, cucumbers, onions, feta cheese, olives', 9.99, 'Salad', true, 'https://images.unsplash.com/photo-1546069901-ba9599a7e63c?w=400'),
('Classic Burger', 'Beef patty with lettuce, tomato, and special sauce', 10.99, 'Burger', false, 'https://images.unsplash.com/photo-1568901346375-23c9450c58cd?w=400'),
('Veggie Burger', 'Plant-based patty with avocado and sprouts', 9.99, 'Burger', true, 'https://images.unsplash.com/photo-1586190848861-99aa4a171e90?w=400'),
('Chicken Burger', 'Grilled chicken breast with mayo and lettuce', 11.99, 'Burger', false, 'https://images.unsplash.com/photo-1521390188846-ea6b5d21b8d3?w=400'),
('Chocolate Lava Cake', 'Warm chocolate cake with molten center, served with ice cream', 6.99, 'Dessert', true, 'https://images.unsplash.com/photo-1624353365286-3f8d62daad51?w=400'),
('Tiramisu', 'Classic Italian dessert with coffee and mascarpone', 7.99, 'Dessert', true, 'https://images.unsplash.com/photo-1571877227200-a0d98ea607e9?w=400'),
('Mango Smoothie', 'Fresh mango blended with yogurt and honey', 5.99, 'Drinks', true, 'https://images.unsplash.com/photo-1628992682633-bf2d40cb595f?w=400'),
('Fresh Lemonade', 'Homemade lemonade with mint', 4.99, 'Drinks', true, 'https://images.unsplash.com/photo-1621506289937-a8e4df240d0b?w=400'),
('Iced Coffee', 'Cold brew coffee with milk and vanilla syrup', 5.49, 'Drinks', true, 'https://images.unsplash.com/photo-1461023058943-07fcbe16d735?w=400'),
('Spaghetti Carbonara', 'Pasta with eggs, cheese, pancetta, and black pepper', 13.99, 'Pasta', false, 'https://images.unsplash.com/photo-1598866594230-a7c12756260f?w=400'),
('Vegetable Pasta', 'Pasta with mixed vegetables in tomato sauce', 12.99, 'Pasta', true, 'https://images.unsplash.com/photo-1563379926898-05f4575a45d8?w=400');