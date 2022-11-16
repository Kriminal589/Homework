CREATE TABLE organization
(
    id               SERIAL NOT NULL,
    inn              BIGINT NOT NULL,
    checking_account BIGINT NOT NULL,
    CONSTRAINT organization_pk PRIMARY KEY (id)
);

CREATE TABLE product
(
    id   SERIAL  NOT NULL,
    name VARCHAR NOT NULL,
    code INT     NOT NULL,
    CONSTRAINT product_pk PRIMARY KEY (id)
);

CREATE TABLE invoice
(
    id              SERIAL NOT NULL,
    organization_id INT    NOT NULL REFERENCES organization (id) ON UPDATE CASCADE ON DELETE CASCADE,
    date            DATE   NOT NULL,
    CONSTRAINT invoice_pk PRIMARY KEY (id)
);

CREATE TABLE position_invoice
(
    id         SERIAL NOT NULL,
    invoice_id INT    NOT NULL REFERENCES invoice (id) ON UPDATE CASCADE ON DELETE CASCADE,
    product_id INT    NOT NULL REFERENCES product (id) ON UPDATE CASCADE ON DELETE CASCADE,
    price      INT    NOT NULL,
    count      INT    NOT NULL,
    CONSTRAINT position_invoice_pk PRIMARY KEY (id)
);
