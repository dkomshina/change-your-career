--liquibase formatted sql

--changeset dkomshina:changeller
CREATE TABLE IF NOT EXISTS changeller
(
    id         BIGSERIAL   NOT NULL PRIMARY KEY,
    created_at TIMESTAMPTZ NOT NULL
);