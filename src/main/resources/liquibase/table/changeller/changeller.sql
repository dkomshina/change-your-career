--liquibase formatted sql

--changeset dkomshina:changeller
CREATE TABLE IF NOT EXISTS changeller
(
    id         BIGSERIAL   NOT NULL PRIMARY KEY,
    created_at TIMESTAMPTZ NOT NULL
);

--changeset dkomshina:add_features_column
ALTER TABLE changeller
    ADD COLUMN IF NOT EXISTS features JSONB;

--changeset dkomshina:add_suggests_columns
ALTER TABLE changeller
    ADD COLUMN IF NOT EXISTS articles  JSONB,
    ADD COLUMN IF NOT EXISTS courses   JSONB,
    ADD COLUMN IF NOT EXISTS events    JSONB,
    ADD COLUMN IF NOT EXISTS vacancies JSONB;