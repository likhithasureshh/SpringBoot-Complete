-- data.sql

INSERT INTO orders (total_price, order_status)
VALUES (1500.00, 'PENDING');

INSERT INTO orders (total_price, order_status)
VALUES (2500.00, 'CONFIRMED');

INSERT INTO orders (total_price, order_status)
VALUES (999.00, 'DELIVERED');


INSERT INTO order_items (quantity, product_id, orders_id)
VALUES (2, 101, 1);

INSERT INTO order_items (quantity, product_id, orders_id)
VALUES (1, 102, 1);

INSERT INTO order_items (quantity, product_id, orders_id)
VALUES (3, 103, 2);

INSERT INTO order_items (quantity, product_id, orders_id)
VALUES (1, 104, 3);

INSERT INTO orders (total_price, order_status)
VALUES (1500.00, 'PENDING');

INSERT INTO orders (total_price, order_status)
VALUES (2500.00, 'CONFIRMED');

INSERT INTO orders (total_price, order_status)
VALUES (999.00, 'DELIVERED');


INSERT INTO order_items (quantity, product_id, orders_id)
VALUES (2, 101, 1);

INSERT INTO order_items (quantity, product_id, orders_id)
VALUES (1, 102, 1);

INSERT INTO order_items (quantity, product_id, orders_id)
VALUES (3, 103, 2);

INSERT INTO order_items (quantity, product_id, orders_id)
VALUES (1, 104, 3);