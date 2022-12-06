CREATE TABLE public."role"
(
    id uuid NOT NULL DEFAULT uuid_generate_v4 (),
    name character varying (255) NOT NULL,
    PRIMARY KEY (id)
);

ALTER TABLE IF EXISTS public."role"
    OWNER to "user";
