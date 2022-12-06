CREATE TABLE public.driver
(
    id uuid NOT NULL DEFAULT uuid_generate_v4 (),
    age integer NOT NULL,
    gender character varying(50) NOT NULL,
    status character varying(50) NOT NULL,
    latitude double precision,
    longitude double precision,
    price_per_km double precision,
    rating double precision DEFAULT 0.0,
    user_id uuid NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_driver_user_id FOREIGN KEY (user_id)
        REFERENCES public."user" (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
);

ALTER TABLE IF EXISTS public.driver
    OWNER to "user";