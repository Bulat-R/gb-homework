DROP TABLE IF EXISTS product;
DROP TABLE IF EXISTS company;

CREATE TABLE company
(
    id   LONG AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR NOT NULL UNIQUE
);

INSERT INTO company (name)
VALUES ('company_A'),
       ('company_B'),
       ('company_C');

CREATE TABLE product
(
    id         LONG AUTO_INCREMENT PRIMARY KEY,
    name       VARCHAR NOT NULL,
    company_id LONG    NOT NULL,
    cost       DOUBLE  NOT NULL,
    FOREIGN KEY (company_id) REFERENCES company (id),
    CONSTRAINT uni_name_comp UNIQUE (name, company_id),
    CONSTRAINT cost_not_negative CHECK (cost >= 0)
);

INSERT INTO product (name, company_id, cost)
VALUES ('test5', SELECT id FROM company WHERE name = 'company_C', 20),
       ('test2', SELECT id FROM company WHERE name = 'company_A', 1.02),
       ('test4', SELECT id FROM company WHERE name = 'company_B', 5.7),
       ('test3', SELECT id FROM company WHERE name = 'company_B', 1),
       ('test1', SELECT id FROM company WHERE name = 'company_A', 65.87);
