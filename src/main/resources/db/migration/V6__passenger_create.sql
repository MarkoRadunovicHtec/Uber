CREATE TABLE public.passenger
(
    id uuid NOT NULL DEFAULT uuid_generate_v4 (),
    first_name character varying (255) NOT NULL,
    last_name character varying (255) NOT NULL,
    age integer NOT NULL,
    gender character varying(50) NOT NULL,
    latitude double precision,
    longitude double precision,
    user_id uuid NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_driver_user_id FOREIGN KEY (user_id)
        REFERENCES public."user" (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
);

ALTER TABLE IF EXISTS public.passenger
    OWNER to "user";