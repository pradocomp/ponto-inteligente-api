CREATE TABLE EMPRESA (
  id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  cnpj varchar(255) NOT NULL,
  data_atualizacao datetime NOT NULL,
  data_criacao datetime NOT NULL,
  razao_social varchar(255) NOT NULL
);

CREATE TABLE FUNCIONARIO (
  id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  cpf varchar(255) NOT NULL,
  data_atualizacao datetime NOT NULL,
  data_criacao datetime NOT NULL,
  email varchar(255) NOT NULL,
  nome varchar(255) NOT NULL,
  perfil varchar(255) NOT NULL,
  qtd_horas_almoco float DEFAULT NULL,
  qtd_horas_trabalho_dia float DEFAULT NULL,
  senha varchar(255) NOT NULL,
  valor_hora decimal(19,2) DEFAULT NULL,
  empresa_id INT DEFAULT NULL,
  CONSTRAINT empresa_fk FOREIGN KEY (empresa_id) REFERENCES EMPRESA (id)
);

CREATE TABLE LANCAMENTO (
  id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  data datetime NOT NULL,
  data_atualizacao datetime NOT NULL,
  data_criacao datetime NOT NULL,
  descricao varchar(255) DEFAULT NULL,
  localizacao varchar(255) DEFAULT NULL,
  tipo varchar(255) NOT NULL,
  funcionario_id INT DEFAULT NULL,
  CONSTRAINT funcionario_fk FOREIGN KEY (funcionario_id) REFERENCES FUNCIONARIO (id)
);

ALTER TABLE EMPRESA AUTO_INCREMENT = 1;
ALTER TABLE FUNCIONARIO AUTO_INCREMENT = 1;
ALTER TABLE LANCAMENTO AUTO_INCREMENT = 1;
