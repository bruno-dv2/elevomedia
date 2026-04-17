CREATE TABLE tb_usuario (
  id            BIGSERIAL PRIMARY KEY,
  nome          VARCHAR(100) NOT NULL,
  email         VARCHAR(255) NOT NULL UNIQUE,
  senha_hash    VARCHAR(255) NOT NULL,
  ativo         BOOLEAN DEFAULT TRUE,
  criado_em     TIMESTAMP DEFAULT NOW(),
  atualizado_em TIMESTAMP
);