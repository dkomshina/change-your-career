--liquibase formatted sql

--changeset dkomshina:form_section
CREATE TABLE IF NOT EXISTS form_section
(
    id           BIGSERIAL   NOT NULL PRIMARY KEY,
    block_id     BIGINT      NOT NULL
        CONSTRAINT pp_form_section_block_id_fkey REFERENCES form_block (id),
    name         TEXT        NOT NULL,
    button_title TEXT,
    outdated     BOOLEAN     NOT NULL,
    created_at   TIMESTAMPTZ NOT NULL
);

--changeset dkomshina:add_ml_name_column
ALTER TABLE form_section
    ADD COLUMN IF NOT EXISTS ml_name TEXT NOT NULL default 'default';