--liquibase formatted sql

--changeset dkomshina:article
CREATE TABLE IF NOT EXISTS article
(
    id            BIGSERIAL   NOT NULL PRIMARY KEY,
    thumbnail_url TEXT        NOT NULL,
    title         TEXT        NOT NULL,
    subtitle      TEXT        NOT NULL,
    description   TEXT        NOT NULL,
    link          TEXT        NOT NULL,
    start_date    DATE,
    end_date      DATE,
    created_at    TIMESTAMPTZ NOT NULL,
    updated_at    TIMESTAMPTZ NOT NULL
);

--changeset dkomshina:add_boolean_columns
ALTER TABLE article
    ADD COLUMN IF NOT EXISTS ml_send        BOOLEAN NOT NULL default false,
    ADD COLUMN IF NOT EXISTS shocked_editor BOOLEAN NOT NULL default false,
    ADD COLUMN IF NOT EXISTS news           BOOLEAN NOT NULL default false,
    ADD COLUMN IF NOT EXISTS description_2  TEXT NOT NULL default '';

--changeset dkomshina:remove_city_column
ALTER TABLE article
    DROP COLUMN IF EXISTS city;
