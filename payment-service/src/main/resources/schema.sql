CREATE SEQUENCE IF NOT EXISTS system_seq INCREMENT 1000;

CREATE TABLE IF NOT EXISTS type_entity
(
    id            BIGINT NOT NULL DEFAULT nextval('system_seq'),
    type_number   VARCHAR(100),
    dtype         VARCHAR(100),
    amount        NUMERIC(19, 2),
    currency      VARCHAR(3),
    deptor_iban   VARCHAR(45),
    creditor_iban VARCHAR(45),
    details       VARCHAR(200),
    bic_code      VARCHAR(45),
    CONSTRAINT type_summary_pk PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS payment_summary
(
    id               BIGINT NOT NULL DEFAULT nextval('system_seq'),
    payment_number   VARCHAR(100),
    total_amount     NUMERIC(19, 2),
    first_name       VARCHAR(45),
    last_name        VARCHAR(45),
    card_number      VARCHAR(100),
    status           VARCHAR(9),
    creation_date    TIMESTAMP,
    cancellation_fee NUMERIC(19, 2),
    type_id          BIGINT,
    CONSTRAINT payment_summary_pk PRIMARY KEY (id),
    CONSTRAINT type_entity_fk FOREIGN KEY (type_id) REFERENCES type_entity (id)
);

CREATE TABLE IF NOT EXISTS cancellation_coefficients
(
    id          BIGINT NOT NULL DEFAULT nextval('system_seq'),
    type_d_type VARCHAR(45),
    coefficient DOUBLE PRECISION,
    CONSTRAINT cancellation_coefficients_pk PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS payment_transfer_log
(
    id               BIGINT NOT NULL DEFAULT nextval('system_seq'),
    payment_number   VARCHAR(100),
    http_status_code INTEGER,
    http_comment     VARCHAR(15),
    CONSTRAINT payment_transmission_pk PRIMARY KEY (id)
);

CREATE TABLE error_messages
(
    id      BIGINT       NOT NULL DEFAULT nextval('system_seq'),
    code    VARCHAR(7)   NOT NULL,
    message VARCHAR(200) NOT NULL,
    CONSTRAINT error_messages_pk PRIMARY KEY (id)
);
