
DROP TABLE IF EXISTS `cliente`;
DROP TABLE IF EXISTS `custodia_contrato`;
DROP TABLE IF EXISTS `simulacao_emprestimo`;

CREATE TABLE `cliente` (
  `cpf` varchar(14) NOT NULL,
  `nome` varchar(255) NOT NULL,
  `correntista` tinyint NOT NULL,
  `segmento` char(1) DEFAULT NULL,
  `convenio` varchar(4) NOT NULL,
  PRIMARY KEY (`cpf`)
);

CREATE TABLE `simulacao_emprestimo` (
  `id` varchar(36) NOT NULL,
  `data_simulacao` datetime NOT NULL,
  `cpf` varchar(14) NOT NULL,
  `convenio` varchar(4) NOT NULL,
  `valor_solicitado` decimal(15,2) NOT NULL,
  `taxa_aplicada` decimal(10,6) NOT NULL,
  `quantidade_parcelas` int NOT NULL,
  `valor_parcela` decimal(15,2) NOT NULL,
  `valor_total` decimal(15,2) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `cliente_idx` (`cpf`),
  CONSTRAINT `cliente` FOREIGN KEY (`cpf`) REFERENCES `cliente` (`cpf`)
);

CREATE TABLE `custodia_contrato` (
  `id` varchar(36) NOT NULL,
  `data_contrato` datetime NOT NULL,
  `id_simulacao` varchar(36) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_simulacao_idx` (`id_simulacao`),
  CONSTRAINT `simulacao` FOREIGN KEY (`id_simulacao`) REFERENCES `simulacao_emprestimo` (`id`)
);



INSERT INTO `cliente` VALUES 
('111.111.111-11','Michael Jackson',1,'V','EP')
,('222.222.222-22','Lebron James',1,'U','OP'),
('333.333.333-33','Madonna',1,'P','INSS'),
('444.444.444-44','Marta Vieira da Silva',0,NULL,'EP'),
('555.555.555-55','Messi',0,NULL,'INSS');