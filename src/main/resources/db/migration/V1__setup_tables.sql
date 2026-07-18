CREATE TABLE cid (
    id UUID NOT NULL PRIMARY KEY,
    codigo VARCHAR(10) NOT NULL UNIQUE,
    descricao VARCHAR(255) NOT NULL
);

CREATE TABLE evento_medicao (
    id UUID NOT NULL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    unidade_medida VARCHAR(255) NOT NULL,
    referencia_minima REAL,
    referencia_maxima REAL
);

CREATE TABLE tratamento (
   id UUID NOT NULL PRIMARY KEY,
   cpf_paciente VARCHAR(11) NOT NULL,
   crm_medico VARCHAR(20) NOT NULL,
   descricao VARCHAR(255) NOT NULL,
   data_inicio DATE NOT NULL,
   data_fim DATE NOT NULL,
   id_cid UUID NOT NULL,
   CONSTRAINT fk_tratamento_cid FOREIGN KEY (id_cid) REFERENCES cid(id)
);

CREATE TABLE tratamento_evento (
   id UUID NOT NULL PRIMARY KEY,
   frequencia_horas INTEGER NOT NULL,
   tratamento_id UUID,
   evento_medicao_id UUID NOT NULL,
   CONSTRAINT fk_evento_tratamento FOREIGN KEY (tratamento_id) REFERENCES tratamento(id),
   CONSTRAINT fk_evento_medicao FOREIGN KEY (evento_medicao_id) REFERENCES evento_medicao(id)
);