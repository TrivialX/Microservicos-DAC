CREATE TABLE IF NOT EXISTS public.gerentes
(
    id SERIAL PRIMARY KEY,
    nome character varying(60) COLLATE pg_catalog."default",
    email character varying(60) COLLATE pg_catalog."default",
    cpf character varying(11) COLLATE pg_catalog."default" NOT NULL
);

INSERT INTO public.gerentes (nome, email, cpf)
VALUES
  ('João Silva', 'joao.silva@example.com', '12345678901'),
  ('Maria Oliveira', 'maria@example.com', '98765432109'),
  ('Carlos Pereira', 'carlos@example.com', '12378945602');
