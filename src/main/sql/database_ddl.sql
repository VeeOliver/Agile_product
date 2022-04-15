-- Create the database and an administration user
CREATE DATABASE myrmidon;

USE myrmidon;

CREATE USER myrmidon_admin IDENTIFIED BY 'myr_ADM123';

GRANT ALL ON myrmidon.* TO myrmidon_admin WITH GRANT OPTION;

-- Create tables
CREATE TABLE User (
    personnummer CHAR(13) NOT NULL UNIQUE,
    name VARCHAR(30) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    password CHAR(40) NOT NULL,
    PRIMARY KEY (personnummer)
);

CREATE TABLE Tension (
    tension_id INT AUTO_INCREMENT,
    rating INT NOT NULL,
    date DATETIME NOT NULL,
    personnummer CHAR(13) NOT NULL,
    PRIMARY KEY (tension_id),
    FOREIGN KEY (personnummer) REFERENCES User(personnummer)
);

CREATE TABLE Mood (
    mood_id INT AUTO_INCREMENT,
    rating INT NOT NULL,
    date DATETIME NOT NULL,
    personnummer CHAR(13) NOT NULL,
    PRIMARY KEY (mood_id),
    FOREIGN KEY (personnummer) REFERENCES User(personnummer)
);

CREATE TABLE Journal_entry (
    journal_entry_id INT AUTO_INCREMENT,
    journal_entry VARCHAR(255) NOT NULL,
    date DATETIME NOT NULL,
    personnummer CHAR(13) NOT NULL,
    PRIMARY KEY (journal_entry_id),
    FOREIGN KEY (personnummer) REFERENCES User(personnummer)
);

CREATE TABLE Facts (
    fact VARCHAR(255) NOT NULL UNIQUE,
    last_shown DATETIME DEFAULt NULL,
    PRIMARY KEY (fact)
);

-- Function to determine time of the day from datetime => Morning, noon, afternoon, evening

DELIMITER $$
CREATE FUNCTION determineTimeOfDay(
entry_time DATETIME
)
RETURNS VARCHAR(20)
DETERMINISTIC
BEGIN
IF TIME(entry_time) >= '05:00:00' && TIME(entry_time) < '10:00:00' THEN
    RETURN ("Morning");
ELSEIF TIME(entry_time) >= '10:00:00' && TIME(entry_time) < '14:00:00' THEN
    RETURN ("Noon");
ELSEIF TIME(entry_time) >= '14:00:00' && TIME(entry_time) < '19:00:00' THEN
    RETURN ("Afternoon");
ELSEIF TIME(entry_time) >= '19:00:00' || TIME(entry_time) < '5:00:00' THEN
    RETURN ("Evening");
END IF;
END$$
DELIMITER ;
