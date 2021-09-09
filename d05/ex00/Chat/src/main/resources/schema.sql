DROP SCHEMA IF EXISTS public CASCADE;
CREATE SCHEMA public;

CREATE TABLE public.user
(
    id bigint NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1 ),
    login character varying(128) COLLATE pg_catalog."default" NOT NULL,
    password character varying(128) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT user_pkey PRIMARY KEY (id),
    CONSTRAINT user_login_key UNIQUE (login)
);

CREATE TABLE public.chatroom
(
    id bigint NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1 ),
    name character varying(256) COLLATE pg_catalog."default" NOT NULL,
    owner bigint NOT NULL,
    CONSTRAINT chatroom_pkey PRIMARY KEY (id),
    CONSTRAINT chatroom_owner_fkey FOREIGN KEY (owner)
        REFERENCES public.user (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE CASCADE
);

CREATE TABLE public.message
(
    id bigint NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1 ),
    author bigint NOT NULL,
    chatroom bigint NOT NULL,
    text text COLLATE pg_catalog."default" NOT NULL,
    datetime timestamp with time zone DEFAULT now(),
    CONSTRAINT message_pkey PRIMARY KEY (id),
    CONSTRAINT message_author_fkey FOREIGN KEY (author)
        REFERENCES public.user (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE CASCADE,
    CONSTRAINT message_chatroom_fkey FOREIGN KEY (chatroom)
        REFERENCES public.chatroom (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE CASCADE
);