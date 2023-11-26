CREATE TABLE IF NOT EXISTS public.gerentes
(
    id SERIAL PRIMARY KEY,
    nome character varying(60) COLLATE pg_catalog."default",
    email character varying(60) COLLATE pg_catalog."default",
    cpf character varying(11) COLLATE pg_catalog."default" NOT NULL
);

TABLESPACE pg_default;