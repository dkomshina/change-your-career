--liquibase formatted sql

--changeset dkomshina:form
CREATE TABLE IF NOT EXISTS form
(
    id         BIGSERIAL   NOT NULL PRIMARY KEY,
    outdated   BOOLEAN     NOT NULL,
    created_at TIMESTAMPTZ NOT NULL
);

--changeset dkomshina:form_comments runOnChange:true
COMMENT ON TABLE form IS 'Конфигурация формы';
COMMENT ON COLUMN form.outdated IS 'Устаревшая ли форма';
