CREATE TABLE public."user"
(
    id uuid NOT NULL DEFAULT uuid_generate_v4 (),
    first_name character varying (255) NOT NULL,
    last_name character varying (255) NOT NULL,
    email character varying (255) NOT NULL,
    encrypted_password character varying (255) NOT NULL,
    created_on timestamp with time zone NOT NULL DEFAULT CURRENT_TIMESTAMP,
    is_active boolean NOT NULL DEFAULT false,
    role_id uuid NOT NULL,
    PRIMARY KEY (id)
);

ALTER TABLE IF EXISTS public."user"
    OWNER to "user";