CREATE TABLE tb_registro (
    id                 BIGINT        NOT NULL AUTO_INCREMENT,
    estacao_nome       VARCHAR(100)  NOT NULL,
    data_registro      DATE          NOT NULL,
    temperatura        DECIMAL(5,2)  NOT NULL,
    percentual_umi     DECIMAL(5,2)  NOT NULL,
    precipitacao       DECIMAL(6,2)  NOT NULL,
    condicao_climatica VARCHAR(20)    NOT NULL,
    PRIMARY KEY (id)
);