--liquibase formatted sql

--changeset dkomshina:vacancy
CREATE TABLE IF NOT EXISTS vacancy
(
    id            BIGSERIAL   NOT NULL PRIMARY KEY,
    thumbnail_url TEXT        NOT NULL,
    title         TEXT        NOT NULL,
    location      TEXT        NOT NULL,
    company_name  TEXT        NOT NULL,
    link          TEXT        NOT NULL,
    start_date    DATE,
    end_date      DATE,
    created_at    TIMESTAMPTZ NOT NULL,
    updated_at    TIMESTAMPTZ NOT NULL
);

--changeset dkomshina:add_vacancy_new_columns
ALTER TABLE vacancy
    ADD COLUMN IF NOT EXISTS ml_send         BOOLEAN NOT NULL default false,
    ADD COLUMN IF NOT EXISTS description     TEXT    NOT NULL default '',
    ADD COLUMN IF NOT EXISTS company_id      TEXT    NOT NULL default '',
    ADD COLUMN IF NOT EXISTS work_mode       TEXT    NOT NULL default '',
    ADD COLUMN IF NOT EXISTS work_experience TEXT    NOT NULL default '';

--changeset dkomshina:add_vacancy_new_columns_more
ALTER TABLE vacancy
    ADD COLUMN IF NOT EXISTS subtitle TEXT NOT NULL default '';
