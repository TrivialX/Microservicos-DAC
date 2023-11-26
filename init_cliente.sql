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
);

INSERT INTO public.clientes (nome, email, cpf, endereco, telefone, salario, id_gerente)
VALUES
  ('Maria Oliveira', 'maria@example.com', '987.654.321-00', 'Avenida Principal, 456', '(11) 9876-5432', '5500.00', 1),
  ('Carlos Pereira', 'carlos@example.com', '123.789.456-00', 'Rua Secundária, 789', '(11) 8765-4321', '6000.00', 2),
  ('Ana Souza', 'ana@example.com', '456.123.789-00', 'Praça Central, 123', '(11) 7654-3210', '4800.00', 3);
