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