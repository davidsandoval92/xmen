-- SEQUENCE: public.verification_attempts_id_seq

-- DROP SEQUENCE public.verification_attempts_id_seq;

CREATE SEQUENCE public.verification_attempts_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 2147483647
    CACHE 1;

ALTER SEQUENCE public.verification_attempts_id_seq
    OWNER TO postgres;