DROP TABLE IF EXISTS "kategorie";
CREATE TABLE "kategorie" (
"id"  SERIAL PRIMARY KEY,
"nazwa" VARCHAR(40) NOT NULL
);


DROP TABLE IF EXISTS "ksiazki";
CREATE TABLE "ksiazki" (
"id"  SERIAL PRIMARY KEY,
"nazwa" VARCHAR(100) NOT NULL,
"wydawnictwo" VARCHAR(50) NOT NULL,
"cena" float NOT NULL default 0,
"opis" VARCHAR(500) NOT NULL,
"image_url" VARCHAR(255) NOT NULL,
"kategoria_id" INT NULL references kategorie(id)
);


DROP TABLE IF EXISTS "autorzy";
CREATE TABLE "autorzy" (
"id"  SERIAL PRIMARY KEY,
"imie" VARCHAR(30) NOT NULL,
"nazwisko" VARCHAR(40) NOT NULL
);


DROP TABLE IF EXISTS "autorzy_to_ksiazki";
CREATE TABLE "autorzy_to_ksiazki" (
"ksiazka_id"  INT NOT NULL references ksiazki(id),
"autor_id" INT NOT NULL references autorzy(id),
primary key("ksiazka_id", "autor_id")
);


DROP TABLE IF EXISTS users;
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
ksiazki_id INT NOT NULL references ksiazki(id),
quantity INT NOT NULL,
primary key("user_id", "ksiazki_id")
);

DROP TABLE IF EXISTS orders;
CREATE TABLE orders(
"id"  SERIAL PRIMARY KEY,
"status" VARCHAR(25) DEFAULT 'Nowe',
"username" VARCHAR(25) NOT NULL references users(username)
);

DROP TABLE IF EXISTS order_items;
CREATE TABLE order_items(
"id"  SERIAL PRIMARY KEY,
"book_id" INT NOT NULL references ksiazki(id),
"quantity" INT NOT NULL,
"order_id" INT NOT NULL references orders(id)
);






