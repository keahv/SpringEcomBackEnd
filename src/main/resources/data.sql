INSERT INTO Product ( name, description, brand, price, category, release_date, product_available, stock_quantity, image_url)
VALUES
( 'Smartphone X', 'A powerful smartphone with excellent features.', 'TechBrand', 799.99, 'Electronics', '2023-12-01', TRUE, 150, 'https://images.unsplash.com/photo-1610945265064-0e34e5519bbf?q=80&w=2070&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D'),
		( 'Gaming Laptop Pro', 'High-performance gaming laptop with advanced cooling.', 'GameTech', 1599.50, 'Electronics', '2023-11-15', TRUE, 50, 'https://media.istockphoto.com/id/505227671/photo/gamer-laptop-with-video-game.jpg?s=1024x1024&w=is&k=20&c=BHcIasAjaYCSxSfEdeuIa_p1kX91ki1o0QVNwXr-154='),
		( 'Wireless Headphones', 'Noise-canceling headphones with superior sound quality.', 'SoundMax', 199.99, 'Accessories', '2024-01-10', TRUE, 200, 'https://media.istockphoto.com/id/913851526/photo/white-headphones.jpg?s=612x612&w=is&k=20&c=Yj7GKmwpgb2bzZiws9_FnRtq3iRVdw2trwI-ALpJ1iA='),
		( 'Smartwatch Z', 'Stylish smartwatch with health tracking and notifications.', 'WearTech', 299.00, 'Wearables', '2023-10-05', TRUE, 120, 'https://images.unsplash.com/photo-1617625802912-cde586faf331?w=1000&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8M3x8c21hcnR3YXRjaHxlbnwwfHwwfHx8MA%3D%3D'),
		( '4K Ultra HD TV', 'Large screen 4K TV with smart features.', 'VisionCorp', 1199.99, 'Home Appliances', '2023-09-20', TRUE, 30, 'https://images.unsplash.com/flagged/photo-1572609239482-d3a83f976aa0?w=1000&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Mnx8aGQlMjB0dnxlbnwwfHwwfHx8MA%3D%3D');


INSERT INTO Users (user_name,user_email,is_active,password,access_role)
 VALUES ('keshav khekade','keshavkhekade@gmail.com',true,'$2a$12$R5Q9j.BTH9CKUj.TF3WjMezZeG24v798UXyW3LLAEGV0iNB77cg1y','user'),
        ('Admin','admin@gmail.com',true,'$2a$12$FroFgJiv2OihEPQlU50lsOxPWUAPpRC0VGn3ZTvFJyxtf9t2xascS','admin')
