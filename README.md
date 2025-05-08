# Excluir dados do Banco com Java
Aula 08/05/2025

CREATE DATABASE IF NOT EXISTS agenciaviagens;
USE agenciaviagens;

CREATE TABLE IF NOT EXISTS turista (
    codigo INT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    origem VARCHAR(100) NOT NULL,
    destino VARCHAR(100) NOT NULL
);

INSERT INTO turista (codigo, nome, origem, destino) VALUES
(1, 'Maria Souza', 'Rio de Janeiro', 'Salvador'),
(2, 'Carlos Lima', 'Brasília', 'Curitiba'),
(3, 'Ana Martins', 'São Paulo', 'Fortaleza'),
(4, 'João Silva', 'Recife', 'Belo Horizonte');
