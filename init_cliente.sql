CREATE TABLE IF NOT EXISTS public.clientes
(
    id SERIAL PRIMARY KEY,
    nome text COLLATE pg_catalog."default",
    email text COLLATE pg_catalog."default",
    cpf text COLLATE pg_catalog."default",
    endereco text COLLATE pg_catalog."default",
    telefone text COLLATE pg_catalog."default",
    salario text COLLATE pg_catalog."default",
    id_gerente integer
)

-- Inserting a client with a specific manager
INSERT INTO public.clientes (nome, email, cpf, endereco, telefone, salario, id_gerente)
VALUES ('João Silva', 'joao.silva@email.com', '123.456.789-01', 'Rua A, 123', '(11) 98765-4321', '5000.00', 1);

-- Inserting another client with a different manager
INSERT INTO public.clientes (nome, email, cpf, endereco, telefone, salario, id_gerente)
VALUES ('Maria Oliveira', 'maria.oliveira@email.com', '987.654.321-01', 'Rua B, 456', '(21) 87654-3210', '7000.00', 2);

-- Inserting a client without specifying a manager
INSERT INTO public.clientes (nome, email, cpf, endereco, telefone, salario, id_gerente)
VALUES ('Carlos Santos', 'carlos.santos@email.com', '111.222.333-44', 'Rua C, 789', '(31) 76543-2109', '6000.00', 2);
