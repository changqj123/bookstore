CREATE TABLE IF NOT EXISTS users (user_id bigint PRIMARY KEY,
user_name VARCHAR(255) NOT NULL UNIQUE,
password VARCHAR(255) NOT NULL, phone_num VARCHAR(11) NOT NULL, email VARCHAR(255) NOT NULL);
INSERT INTO users VALUES(1, "test01", "11", "12345678901", "123@test.com");
INSERT INTO users VALUES(2, "test02", "22", "12345678901", "123@test.com");

CREATE TABLE IF NOT EXISTS books (
category_id VARCHAR(255) PRIMARY KEY,
book_id bigint NOT NULL,
book_name VARCHAR(255) NOT NULL,
author VARCHAR(255) NOT NULL,
publish_house VARCHAR(255) NOT NULL,
description VARCHAR(255) NOT NULL,
price DOUBLE NOT NULL);

insert into books VALUES("computer", 1, "java", "Jack", "house01", "good book to learn java", 36.0);
insert into books VALUES("cook", 2, "cooker", "Lucy", "house01", "good book to learn cooking", 26.0);
insert into books VALUES("music", 3, "jazz", "Rose", "house01", "good tool to learn music", 38.0);

CREATE TABLE IF NOT EXISTS inventory (
book_id bigint NOT NULL PRIMARY KEY,
book_name VARCHAR(255) NOT NULL,
book_stock bigint NOT NULL);

insert into inventory VALUES(1, "java", 300);
insert into inventory VALUES(2, "cook", 360);
insert into inventory VALUES(3, "music", 280);

CREATE TABLE IF NOT EXISTS orders(
order_id bigint NOT NULL PRIMARY KEY,
order_time DATE NOT NULL,
order_price DOUBLE NOT NULL,
order_state VARCHAR(255) NOT NULL,
user VARCHAR(255) NOT NULL,
order_items text NOT NULL
);




