CREATE TABLE alunos(
    `id` INTEGER PRIMARY KEY,
    `uuid` VARCHAR(45) NOT NULL,
    `nome` VARCHAR(255) NOT NULL,
    `endereco` VARCHAR(255),
    `turma_id` INT NOT NULL,
    FOREIGN KEY(turma_id) REFERENCES turmas(id)
);

CREATE TABLE professores(
    `id` INTEGER PRIMARY KEY,
    `uuid` VARCHAR(45) NOT NULL,
    `nome` VARCHAR(255) NOT NULL,
    `endereco` VARCHAR(255),
    `salario` DECIMAL NOT NULL,
    `escola_id` INT NOT NULL,
    FOREIGN KEY(escola_id) REFERENCES escolas(id)
);

CREATE TABLE turmas(
    `id` INTEGER PRIMARY KEY,
    `uuid` VARCHAR(45) NOT NULL,
    `serie` VARCHAR(255) NOT NULL,
    `escola_id` INT NOT NULL,
    FOREIGN KEY(escola_id) REFERENCES escolas(id)
);

CREATE TABLE escolas(
    `id` INTEGER PRIMARY KEY,
    `nome` VARCHAR(255) NOT NULL,
    `endereco` VARCHAR(255)
);
