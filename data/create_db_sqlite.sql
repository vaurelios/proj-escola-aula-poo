CREATE TABLE alunos(
    `id` INTEGER PRIMARY KEY,
    `nome` VARCHAR(255) NOT NULL,
    `endereco` VARCHAR(255),
    `turma_id` INT NOT NULL,
    FOREIGN KEY(turma_id) REFERENCES turmas(id)
);

CREATE TABLE professores(
    `id` INTEGER PRIMARY KEY,
    `nome` VARCHAR(255) NOT NULL,
    `endereco` VARCHAR(255),
    `salario` DECIMAL NOT NULL
);

CREATE TABLE turmas(
    `id` INTEGER PRIMARY KEY,
    `serie` VARCHAR(255) NOT NULL
);
