CREATE DATABASE escola;

use escola;

CREATE TABLE alunos(
    `id` INT NOT NULL AUTO_INCREMENT,
    `uuid` VARCHAR(45) NOT NULL,
    `nome` VARCHAR(255) NOT NULL,
    `endereco` VARCHAR(255),
    `turma_id` INT NOT NULL,
    PRIMARY KEY(id, uuid)
);

CREATE TABLE professores(
    `id` INT NOT NULL AUTO_INCREMENT,
    `uuid` VARCHAR(45) NOT NULL,
    `nome` VARCHAR(255) NOT NULL,
    `endereco` VARCHAR(255),
    `salario` DECIMAL NOT NULL,
    `escola_id` INT NOT NULL,
    PRIMARY KEY(id, uuid)
);

CREATE TABLE turmas(
    `id` INT NOT NULL AUTO_INCREMENT,
    `uuid` VARCHAR(45) NOT NULL,
    `serie` VARCHAR(255) NOT NULL,
    `escola_id` INT NOT NULL,
    PRIMARY KEY(id, uuid)
);

CREATE TABLE escolas(
    `id` INT NOT NULL AUTO_INCREMENT,
    `uuid` VARCHAR(45) NOT NULL,
    `nome` VARCHAR(255) NOT NULL,
    `endereco` VARCHAR(255),
    PRIMARY KEY(id, uuid)
);

ALTER TABLE alunos ADD FOREIGN KEY (turma_id) REFERENCES turmas(id) ON UPDATE cascade ON DELETE cascade;

ALTER TABLE professores ADD FOREIGN KEY (escola_id) REFERENCES escolas(id) ON UPDATE cascade ON DELETE cascade;

ALTER TABLE turmas ADD FOREIGN KEY (escola_id) REFERENCES escolas(id) ON UPDATE cascade ON DELETE cascade;