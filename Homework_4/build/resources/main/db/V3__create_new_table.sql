CREATE TABLE test_table (
    id     SERIAL  NOT NULL,
    name   VARCHAR NOT NULL,
    date   timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT test_table_pk PRIMARY KEY (id)
);