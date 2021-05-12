-- Table: public.verification_attempts

-- DROP TABLE public.verification_attempts;

CREATE TABLE public.verification_attempts
(
    id integer NOT NULL DEFAULT nextval('verification_attempts_id_seq'::regclass),
    exam_result character varying(10) COLLATE pg_catalog."default",
    exam_date date,
    CONSTRAINT verification_attempts_pkey PRIMARY KEY (id)
)

TABLESPACE pg_default;

ALTER TABLE public.verification_attempts
    OWNER to postgres;