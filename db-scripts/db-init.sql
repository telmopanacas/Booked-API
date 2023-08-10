-- noinspection SqlDialectInspectionForFile

-- noinspection SqlNoDataSourceInspectionForFile

CREATE DATABASE IF NOT EXISTS `booked-db`;

USE `booked-db`;

CREATE TABLE IF NOT EXISTS `livro` (
        `id` INT(20) AUTO_INCREMENT,
        `titulo` VARCHAR(255) NOT NULL,
        `autor` VARCHAR(70) NOT NULL,
        `isbn` VARCHAR(255) NOT NULL,
        `created_at` VARCHAR(255) NOT NULL,
        PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `avaliacao` (
        `id` INT(20) NOT NULL AUTO_INCREMENT,
        `titulo` VARCHAR(255) NOT NULL,
        `autor` VARCHAR(255) NOT NULL,
        `review` VARCHAR(1000) NOT NULL,
        `rating` TINYINT NOT NULL,
        `votos` INT(20) NOT NULL,
        `created_at` VARCHAR(255) NOT NULL,
        `livro_id` INT(20) NOT NULL, -- Chave estrangeira para fazer referência à tabela "livro"
        PRIMARY KEY (`id`),
        FOREIGN KEY (`livro_id`) REFERENCES `livro` (`id`) ON DELETE CASCADE -- Definindo a chave estrangeira
);

CREATE TABLE IF NOT EXISTS `comentario` (
      `id` INT(20) NOT NULL AUTO_INCREMENT,
      `autor` VARCHAR(255) NOT NULL,
      `votos` INT(20) NOT NULL,
      `created_at` VARCHAR(255) NOT NULL,
      `avaliacao_id` INT(20) NOT NULL, -- Chave estrangeira para fazer referência à tabela "avaliacao"
      PRIMARY KEY (`id`),
      FOREIGN KEY (`avaliacao_id`) REFERENCES `avaliacao` (`id`) ON DELETE CASCADE -- Definindo a chave estrangeira
);
