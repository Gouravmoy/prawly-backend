-- Active: 1712257106330@@127.0.0.1@3306@prawly
CREATE SCHEMA `prawly` ;

CREATE TABLE `prawly`.`posts` (
  `id` INT NOT NULL,
  `title` VARCHAR(2000) NOT NULL,
  `desc` VARCHAR(2000) NULL,
  `created_by` INT NOT NULL,
  `created_date` DATETIME NOT NULL,
  `updated_date` DATETIME NOT NULL,
  PRIMARY KEY (`id`));

  ALTER TABLE posts
CHANGE COLUMN `id` `id` INT(11) NOT NULL AUTO_INCREMENT;

ALTER TABLE posts
RENAME COLUMN `desc` to descs;

  select * from posts;