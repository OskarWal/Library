DROP TABLE IF EXISTS "categories" CASCADE;
CREATE TABLE "categories" (
"id"  SERIAL PRIMARY KEY,
"name" VARCHAR(40) NOT NULL
);


DROP TABLE IF EXISTS "books" CASCADE;
CREATE TABLE "books" (
"id"  SERIAL PRIMARY KEY,
"title" VARCHAR(100) NOT NULL,
"publisher" VARCHAR(50) NOT NULL,
"price" float NOT NULL default 0,
"description" VARCHAR(500) NOT NULL,
"image_url" VARCHAR(255) NOT NULL,
"category_id" INT NULL references categories(id)
);


DROP TABLE IF EXISTS "authors" CASCADE;
CREATE TABLE "authors" (
"id"  SERIAL PRIMARY KEY,
"name" VARCHAR(30) NOT NULL,
"surname" VARCHAR(40) NOT NULL
);


DROP TABLE IF EXISTS "authors_to_books";
CREATE TABLE "authors_to_books" (
"book_id"  INT NOT NULL references books(id),
"author_id" INT NOT NULL references authors(id),
primary key("book_id", "author_id")
);


DROP TABLE IF EXISTS users CASCADE;
CREATE TABLE users (
 username VARCHAR(25) NOT NULL,
 password VARCHAR(60) NOT NULL,
 enabled boolean NOT NULL DEFAULT TRUE,
 PRIMARY KEY (username)
);

DROP TABLE IF EXISTS authorities;
CREATE TABLE authorities (
username varchar(50) NOT NULL,
authority varchar(50) NOT NULL,
CONSTRAINT authorities_ibfk_1
foreign key (username)
REFERENCES users (username)
);
CREATE unique index authorieties_idx_1 ON authorities("username", "authority");

DROP TABLE IF EXISTS carts;
CREATE TABLE carts(
user_id VARCHAR(25) NOT NULL references users(username),
books_id INT NOT NULL references books(id),
quantity INT NOT NULL,
primary key("user_id", "books_id")
);

DROP TABLE IF EXISTS orders CASCADE;
CREATE TABLE orders(
"id"  SERIAL PRIMARY KEY,
"status" VARCHAR(25) DEFAULT 'Nowe',
"username" VARCHAR(25) NOT NULL references users(username)
);

DROP TABLE IF EXISTS order_items;
CREATE TABLE order_items(
"id"  SERIAL PRIMARY KEY,
"book_id" INT NOT NULL references books(id),
"quantity" INT NOT NULL,
"order_id" INT NOT NULL references orders(id)
);






