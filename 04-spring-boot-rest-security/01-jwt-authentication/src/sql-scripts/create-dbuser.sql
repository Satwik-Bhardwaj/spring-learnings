CREATE DATABASE IF NOT EXISTS `dbuser`;

USE `dbuser`;

CREATE TABLE IF NOT EXISTS `user` (
    `username` VARCHAR(255) PRIMARY KEY,
    `password` VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS `role` (
	`username` VARCHAR(255),
    FOREIGN KEY (`username`) REFERENCES `user`(`username`),
    `roles` VARCHAR(255)
);
