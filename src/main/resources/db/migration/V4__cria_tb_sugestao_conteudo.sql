CREATE TABLE tb_sugestao_conteudo (
  id               BIGSERIAL PRIMARY KEY,
  planejamento_id  BIGINT NOT NULL REFERENCES tb_planejamento(id),
  data_publicacao  DATE NOT NULL,
  titulo_data      VARCHAR(200),
  legenda          TEXT,
  hashtags         TEXT,
  chamada_acao     VARCHAR(300),
  roteiro_video    TEXT,
  status           VARCHAR(20) DEFAULT 'SUGERIDO',
  criado_em        TIMESTAMP DEFAULT NOW(),
  atualizado_em    TIMESTAMP
);