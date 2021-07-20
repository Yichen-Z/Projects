BEGIN TRANSACTION;

-- Ensures database is created with brand-new tables
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS books;
DROP TABLE IF EXISTS authors;
DROP TABLE IF EXISTS authors_books;
DROP TABLE IF EXISTS tags;
DROP TABLE IF EXISTS books_tags;

-- Ensures tables have brand new sequences for all IDs. To improve: change to generating UUIDs instead
DROP SEQUENCE IF EXISTS seq_user_id;
DROP SEQUENCE IF EXISTS seq_book_id;
DROP SEQUENCE IF EXISTS seq_author_id;
DROP SEQUENCE IF EXISTS seq_tag_id;

-- Create serial sequences for IDs
CREATE SEQUENCE seq_user_id
  INCREMENT BY 1
  NO MAXVALUE
  NO MINVALUE
  CACHE 1;

CREATE SEQUENCE seq_book_id
  INCREMENT BY 1
  NO MAXVALUE
  NO MINVALUE
  CACHE 1;

CREATE SEQUENCE seq_author_id
  INCREMENT BY 1
  NO MAXVALUE
  NO MINVALUE
  CACHE 1;

CREATE SEQUENCE seq_tag_id
  INCREMENT BY 1
  NO MAXVALUE
  NO MINVALUE
  CACHE 1;

-- Create tables
CREATE TABLE users (
	user_id int DEFAULT nextval('seq_user_id'::regclass) NOT NULL,
	username varchar(50) NOT NULL,
	password_hash varchar(200) NOT NULL,
	role varchar(50) NOT NULL,
	CONSTRAINT PK_user PRIMARY KEY (user_id)
);

CREATE TABLE authors (
	author_id int DEFAULT nextval('seq_author_id'::regclass) NOT NULL,
	last_name varchar(50),
	first_name varchar(50) NOT NULL,
	CONSTRAINT PK_author PRIMARY KEY (author_id)
);

CREATE TABLE books (
	book_id int DEFAULT nextval('seq_book_id'::regclass) NOT NULL,
	title varchar(100) NOT NULL,
	year int NOT NULL,
	CONSTRAINT PK_book PRIMARY KEY (book_id)
);

CREATE TABLE authors_books (
	author_id int NOT NULL,
	book_id int NOT NULL,
	is_primary boolean NOT NULL DEFAULT TRUE,
	is_editor boolean NOT NULL DEFAULT FALSE,
	CONSTRAINT PK_author_id_book_id PRIMARY KEY (author_id, book_id),
	CONSTRAINT FK_author_id FOREIGN KEY (author_id) REFERENCES authors (author_id),
	CONSTRAINT FK_book_id FOREIGN KEY (book_id) REFERENCES books (book_id)
);

CREATE TABLE tags (
	tag_id int DEFAULT nextval('seq_tag_id'::regclass) NOT NULL,
	tag_name varchar(50) NOT NULL,
	CONSTRAINT PK_tag_id PRIMARY KEY (tag_id)
);

CREATE TABLE books_tags (
	book_id int NOT NULL,
	tag_id int NOT NULL,
	tag_weight int NOT NULL DEFAULT 1,
	CONSTRAINT PK_book_id_tag_id PRIMARY KEY (book_id, tag_id),
	CONSTRAINT FK_book_id FOREIGN KEY (book_id) REFERENCES books (book_id),
	CONSTRAINT FK_tag_id FOREIGN KEY (tag_id) REFERENCES tags (tag_id)
);

--Populate data

--users table
INSERT INTO users (username,password_hash,role) VALUES ('user','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('admin','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN');

--authors table
INSERT INTO authors(author_id, last_name, first_name) VALUES (1,'Kayode','Femi');
INSERT INTO authors(author_id, last_name, first_name) VALUES (2,'Stabenow','Dana');

--books table
INSERT INTO books(book_id, title, year) VALUES (1,'Lightseekers',2021);
INSERT INTO books(book_id, title, year) VALUES (2,'A Cold Day for Murder',1992);
-- INSERT INTO books(book_id, title, year) VALUES ();

--authors_books table
INSERT INTO authors_books(author_id, book_id) VALUES (1,1);
INSERT INTO authors_books(author_id, book_id) VALUES (2,2);
-- INSERT INTO authors_books(author_id, book_id) VALUES ();

--tags table
INSERT INTO tags(tag_id, tag_name) VALUES (1,'Paradigm Shift');
INSERT INTO tags(tag_id, tag_name) VALUES (2,'Female Lead');
INSERT INTO tags(tag_id, tag_name) VALUES (3,'LGBTQ Lead');
INSERT INTO tags(tag_id, tag_name) VALUES (4,'Tribal Groups/Indigenous');
INSERT INTO tags(tag_id, tag_name) VALUES (5,'Childhood Trauma');
INSERT INTO tags(tag_id, tag_name) VALUES (6,'Friendship');
INSERT INTO tags(tag_id, tag_name) VALUES (7,'Parenting');
INSERT INTO tags(tag_id, tag_name) VALUES (8,'African Diaspora');
INSERT INTO tags(tag_id, tag_name) VALUES (9,'Aftermath of War');
INSERT INTO tags(tag_id, tag_name) VALUES (10,'Religion');
INSERT INTO tags(tag_id, tag_name) VALUES (11,'Technology');
INSERT INTO tags(tag_id, tag_name) VALUES (12,'Family Dynamics');
INSERT INTO tags(tag_id, tag_name) VALUES (13,'Mental Illness');
INSERT INTO tags(tag_id, tag_name) VALUES (14,'Natural Beauty');
-- INSERT INTO tags(tag_id, tag_name) VALUES ();

--books_tags table
INSERT INTO books_tags(book_id, tag_id, tag_weight) VALUES (1,1,1);
INSERT INTO books_tags(book_id, tag_id, tag_weight) VALUES (1,4,1);
INSERT INTO books_tags(book_id, tag_id, tag_weight) VALUES (1,5,2);
INSERT INTO books_tags(book_id, tag_id, tag_weight) VALUES (1,6,2);
INSERT INTO books_tags(book_id, tag_id, tag_weight) VALUES (1,7,2);
INSERT INTO books_tags(book_id, tag_id, tag_weight) VALUES (1,8,2);
INSERT INTO books_tags(book_id, tag_id, tag_weight) VALUES (1,9,1);
INSERT INTO books_tags(book_id, tag_id, tag_weight) VALUES (1,10,1);
INSERT INTO books_tags(book_id, tag_id, tag_weight) VALUES (1,11,2);
INSERT INTO books_tags(book_id, tag_id, tag_weight) VALUES (1,12,1);
INSERT INTO books_tags(book_id, tag_id, tag_weight) VALUES (1,13,1);
INSERT INTO books_tags(book_id, tag_id, tag_weight) VALUES (2,2,1);
INSERT INTO books_tags(book_id, tag_id, tag_weight) VALUES (2,4,1);
INSERT INTO books_tags(book_id, tag_id, tag_weight) VALUES (2,5,1);
INSERT INTO books_tags(book_id, tag_id, tag_weight) VALUES (2,12,2);
INSERT INTO books_tags(book_id, tag_id, tag_weight) VALUES (2,14,1);
-- INSERT INTO books_tags(book_id, tag_id, tag_weight) VALUES ();

COMMIT TRANSACTION;
