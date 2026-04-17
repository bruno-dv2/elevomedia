CREATE TABLE tb_planejamento (
  id            BIGSERIAL PRIMARY KEY,
  cliente_id    BIGINT NOT NULL REFERENCES tb_cliente(id),
  mes           INTEGER NOT NULL,
  ano           INTEGER NOT NULL,
  status               VARCHAR(20) DEFAULT 'RASCUNHO',
  observacoes_geracao  TEXT,
  criado_em            TIMESTAMP DEFAULT NOW(),
  atualizado_em        TIMESTAMP,
  UNIQUE (cliente_id, mes, ano)
);