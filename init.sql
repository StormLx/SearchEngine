DROP DATABASE IF EXISTS ass6;

CREATE DATABASE ass6;

CREATE USER IF NOT EXISTS 'admin'@'localhost' IDENTIFIED BY 'aaa';

GRANT ALL ON `ass6`.* TO 'admin'@'localhost';
