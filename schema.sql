CREATE TABLE customers(
    customer_id VARCHAR(36) PRIMARY KEY,
    first_name VARCHAR(50),
    last_name VARCHAR(50),
    email VARCHAR(100),
    phone VARCHAR(20),
    city VARCHAR(100),
    created_at TIMESTAMP
);

CREATE TABLE menu_items(
    item_id VARCHAR(36) PRIMARY KEY,
    name VARCHAR(255),
    category VARCHAR(255),
    price DECIMAL,
    created_at TIMESTAMP
);

CREATE TABLE staff(
    staff_id VARCHAR(36) PRIMARY KEY,
    first_name VARCHAR(50),
    last_name VARCHAR(50),
    role VARCHAR(50),
    email VARCHAR(100),
    created_at TIMESTAMP
);

CREATE TABLE orders(
    order_id VARCHAR(36) PRIMARY KEY,
    customer_id VARCHAR(36) REFERENCES customers(customer_id),
    status VARCHAR(20),
    total_amount DECIMAL,
    created_at TIMESTAMP
);

CREATE TABLE order_items(
    order_item_id VARCHAR(36) PRIMARY KEY,
    order_id VARCHAR(36) REFERENCES orders(order_id),
    menu_item_id VARCHAR(36) REFERENCES menu_items(item_id),
    quantity INT,
    price DECIMAL,
    created_at TIMESTAMP
);