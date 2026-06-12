-- =================================================================
-- BANCO DE DADOS — LUCAS SILVA OFFICIAL SITE
-- Script de criação manual (opcional — o JPA cria tudo automaticamente
-- na primeira execução graças à propriedade spring.jpa.hibernate.ddl-auto=update).
-- =================================================================

CREATE DATABASE IF NOT EXISTS lucassilva_db
    DEFAULT CHARACTER SET utf8mb4
    DEFAULT COLLATE utf8mb4_unicode_ci;

USE lucassilva_db;

-- -----------------------------------------------------------------
-- Tabela: noticias
-- Armazena as notícias publicadas no site.
-- -----------------------------------------------------------------
CREATE TABLE IF NOT EXISTS noticias (
    id               BIGINT AUTO_INCREMENT PRIMARY KEY,
    titulo           VARCHAR(150)  NOT NULL,
    resumo           VARCHAR(300),
    conteudo         TEXT          NOT NULL,
    imagem           VARCHAR(255),
    data_publicacao  DATETIME      NOT NULL
) ENGINE = InnoDB;

-- -----------------------------------------------------------------
-- Tabela: estatisticas
-- Registra os números por temporada do atleta.
-- -----------------------------------------------------------------
CREATE TABLE IF NOT EXISTS estatisticas (
    id                  BIGINT AUTO_INCREMENT PRIMARY KEY,
    temporada           VARCHAR(20)  NOT NULL,
    clube               VARCHAR(80),
    jogos               INT          NOT NULL DEFAULT 0,
    gols                INT          NOT NULL DEFAULT 0,
    assistencias        INT          NOT NULL DEFAULT 0,
    cartoes_amarelos    INT          NOT NULL DEFAULT 0,
    cartoes_vermelhos   INT          NOT NULL DEFAULT 0
) ENGINE = InnoDB;

-- -----------------------------------------------------------------
-- Tabela: mensagens_contato
-- Armazena as mensagens enviadas pelo formulário público de contato.
-- -----------------------------------------------------------------
CREATE TABLE IF NOT EXISTS mensagens_contato (
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome        VARCHAR(100)  NOT NULL,
    email       VARCHAR(120)  NOT NULL,
    mensagem    TEXT          NOT NULL,
    data_envio  DATETIME      NOT NULL
) ENGINE = InnoDB;

-- -----------------------------------------------------------------
-- Dados de exemplo (opcionais)
-- -----------------------------------------------------------------
INSERT INTO estatisticas (temporada, clube, jogos, gols, assistencias, cartoes_amarelos, cartoes_vermelhos) VALUES
    ('2020/2021', 'Santos FC',     32, 11,  7, 4, 0),
    ('2021/2022', 'Santos FC',     38, 18,  9, 5, 1),
    ('2022/2023', 'SE Palmeiras',  41, 23, 12, 3, 0),
    ('2023/2024', 'SE Palmeiras',  45, 27, 14, 6, 0),
    ('2024/2025', 'SE Palmeiras',  39, 24, 11, 4, 1);

INSERT INTO noticias (titulo, resumo, conteudo, imagem, data_publicacao) VALUES
    ('Lucas Silva marca hat-trick na vitória do Palmeiras',
     'Atacante brilha em noite histórica no Allianz Parque.',
     'Em uma noite memorável no Allianz Parque, Lucas Silva foi o grande nome da partida ao marcar três gols na vitória por 4 a 1.',
     '/images/noticia1.jpg', NOW()),
    ('Convocação para a Seleção Brasileira',
     'Lucas é chamado para os próximos jogos das Eliminatórias.',
     'O técnico da Seleção anunciou a convocação de Lucas Silva. É a terceira convocação consecutiva.',
     '/images/noticia2.jpg', NOW());
