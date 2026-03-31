-- Criação do Banco de Dados
CREATE DATABASE IF NOT EXISTS sistema_funcionarios;
USE sistema_funcionarios;

-- Criação da Tabela de Funcionários
CREATE TABLE IF NOT EXISTS funcionarios (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    cpf VARCHAR(11) NOT NULL UNIQUE,
    salarioBase DECIMAL(10, 2) NOT NULL,
    bonus DECIMAL(10, 2) DEFAULT NULL,
    quantTecnologia INT DEFAULT NULL,
    tipoFuncionario VARCHAR(20) NOT NULL
    );
