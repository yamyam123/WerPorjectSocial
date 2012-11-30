CREATE DATABASE web2012 DEFAULT CHARACTER SET utf8 DEFAULT COLLATE utf8_general_ci;

GRANT ALL ON web2012.* TO 'web'@'localhost' IDENTIFIED BY 'asdf';

use web2012;


CREATE TABLE users (
	id INT AUTO_INCREMENT PRIMARY KEY, 
	userid VARCHAR(100) NOT NULL UNIQUE,
	name VARCHAR(100),
	pwd VARCHAR(255) NOT NULL, 
	email VARCHAR(100) UNIQUE,
	gender VARCHAR(100) NOT NULL,
	birth DATE
);

INSERT INTO users VALUES (3, 'dongseop', 'Dongseop Kwon', '12345', 'dongseop@gmail.com','M', null);

