DROP TABLE IF EXISTS company CASCADE;
DROP TABLE IF EXISTS product CASCADE;

CREATE TABLE company
(
    id   BIGSERIAL PRIMARY KEY,
    name VARCHAR UNIQUE NOT NULL
);

CREATE TABLE product
(
    id         BIGSERIAL PRIMARY KEY,
    name       VARCHAR    NOT NULL,
    cost       NUMERIC(2) NOT NULL CHECK (cost >= 0),
    company_id BIGINT     NOT NULL REFERENCES company,
    CONSTRAINT name_comp_uni UNIQUE (name, company_id)
);

INSERT INTO company (name)
VALUES ('companyA'),
       ('companyB'),
       ('companyC');

INSERT INTO product (name, cost, company_id)
VALUES ('prod5', 2.00, (SELECT id FROM company WHERE name = 'companyA')),
       ('prod2', 25.68, (SELECT id FROM company WHERE name = 'companyB')),
       ('prod3', 5.8, (SELECT id FROM company WHERE name = 'companyA')),
       ('test4', 0.2, (SELECT id FROM company WHERE name = 'companyC')),
       ('test1', 12.01, (SELECT id FROM company WHERE name = 'companyB'));
