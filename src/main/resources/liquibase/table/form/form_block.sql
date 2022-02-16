--liquibase formatted sql

--changeset dkomshina:form_block
CREATE TABLE IF NOT EXISTS form_block
(
    id         BIGSERIAL   NOT NULL PRIMARY KEY,
    form_id    BIGINT      NOT NULL
        CONSTRAINT pp_form_block_form_id_fkey REFERENCES form (id),
    name       TEXT        NOT NULL,
    outdated   BOOLEAN     NOT NULL,
    created_at TIMESTAMPTZ NOT NULL
);
