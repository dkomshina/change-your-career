--liquibase formatted sql

--changeset dkomshina:course
CREATE TABLE IF NOT EXISTS course
(
    id            BIGSERIAL   NOT NULL PRIMARY KEY,
    thumbnail_url TEXT        NOT NULL,
    title         TEXT        NOT NULL,
    subtitle      TEXT        NOT NULL,
    description   TEXT        NOT NULL,
    date          DATE        NOT NULL,
    link          TEXT        NOT NULL,
    start_date    DATE,
    end_date      DATE,
    created_at    TIMESTAMPTZ NOT NULL,
    updated_at    TIMESTAMPTZ NOT NULL
);

--changeset dkomshina:add_course_new_columns
ALTER TABLE course
    ADD COLUMN IF NOT EXISTS ml_send        BOOLEAN NOT NULL default false,
    ADD COLUMN IF NOT EXISTS city           TEXT NOT NULL default '';