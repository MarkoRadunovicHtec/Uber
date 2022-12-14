CREATE TABLE public.transport_request
(
    id uuid NOT NULL DEFAULT uuid_generate_v4 (),
    latitude_location double precision,
    longitude_location double precision,
    latitude_destination double precision,
    longitude_destination double precision,
    created_on timestamp with time zone NOT NULL DEFAULT CURRENT_TIMESTAMP,
    status character varying(50) NOT NULL,
    driver_id uuid NOT NULL,
    passenger_id uuid NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_transport_request_driver_id FOREIGN KEY (driver_id)
        REFERENCES public."driver" (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID,
    CONSTRAINT fk_transport_request_passenger_id FOREIGN KEY (passenger_id)
        REFERENCES public."passenger" (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
);

ALTER TABLE IF EXISTS public.transport_request
    OWNER to "user";