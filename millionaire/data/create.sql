CREATE DATABASE millionaire;
USE millionaire;

CREATE TABLE genre
(
	id INT PRIMARY KEY AUTO_INCREMENT,
	label VARCHAR(256) NOT NULL
);

INSERT INTO genre(id, label) VALUES(1, "US_Default");

CREATE TABLE question
(
	id INT PRIMARY KEY AUTO_INCREMENT,
	text VARCHAR(2048) NOT NULL,
	difficulty INT NOT NULL,
	info  VARCHAR(2048) NULL,
	genre INT NOT NULL,
	FOREIGN KEY (genre) REFERENCES genre(id)
);

CREATE TABLE answer
(
	id INT PRIMARY KEY AUTO_INCREMENT,
	question_id INT NOT NULL,
	text VARCHAR(2048) NOT NULL,
	correct BOOL,
	FOREIGN KEY (question_id) REFERENCES question(id)
)