--liquibase formatted sql

--changeset dkomshina:form_element
CREATE TABLE IF NOT EXISTS form_element
(
    id          BIGSERIAL   NOT NULL PRIMARY KEY,
    section_id  BIGINT      NOT NULL
        CONSTRAINT pp_form_element_section_id_fkey REFERENCES form_section (id),
    type        TEXT        NOT NULL,
    title       TEXT        NOT NULL,
    ml_name     TEXT        NOT NULL,
    description TEXT,
    options     TEXT[],
    outdated    BOOLEAN     NOT NULL,
    created_at  TIMESTAMPTZ NOT NULL
);