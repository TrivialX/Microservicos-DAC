CREATE TABLE IF NOT EXISTS tbl_conta (
    id SERIAL PRIMARY KEY,        -- Coluna de ID auto-incrementável
    id_cliente BIGINT,            -- Coluna para o ID do cliente (tipo de dados depende dos requisitos)
    gerente_id BIGINT,            -- Coluna para o ID do gerente (tipo de dados depende dos requisitos)
    saldo DOUBLE PRECISION,      -- Coluna para o saldo em precisão dupla
    limite DOUBLE PRECISION,     -- Coluna para o limite em precisão dupla
    situacao VARCHAR(255),        -- Coluna para a situação (ajuste o tamanho conforme necessário)
    observacao TEXT               -- Coluna para observações longas (texto)
);

CREATE TABLE IF NOT EXISTS tbl_movimentacoes (
    id SERIAL PRIMARY KEY,
    tipo VARCHAR(255),
    value DOUBLE PRECISION,
    conta_id BIGINT,
    conta_destiny BIGINT,
    saldo_anterior_conta_id DOUBLE PRECISION,
    saldo_final_conta_id DOUBLE PRECISION,
    saldo_anterior_conta_destiny DOUBLE PRECISION,
    saldo_final_conta_destiny DOUBLE PRECISION,
    dataHora TIMESTAMP DEFAULT NOW()
);

INSERT INTO tbl_conta (id_cliente, gerente_id, saldo, limite, situacao, observacao)
VALUES
  (1, 1, 5000.00, 1000.00, 'Ativa', 'Conta corrente para gastos diários'),
  (2, 2, 8000.00, 2000.00, 'Ativa', 'Conta de poupança para emergências'),
  (3, 3, 12000.00, 3000.00, 'Inativa', 'Conta bloqueada devido a transações suspeitas');


