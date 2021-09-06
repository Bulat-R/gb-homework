DROP TABLE IF EXISTS company CASCADE;
DROP TABLE IF EXISTS product CASCADE;
DROP TABLE IF EXISTS users CASCADE;
DROP TABLE IF EXISTS orders CASCADE;
DROP FUNCTION IF EXISTS set_price();

CREATE TABLE users
(
    id   BIGSERIAL PRIMARY KEY,
    name VARCHAR UNIQUE NOT NULL
);

CREATE TABLE company
(
    id   BIGSERIAL PRIMARY KEY,
    name VARCHAR UNIQUE NOT NULL
);

CREATE TABLE product
(
    id         BIGSERIAL PRIMARY KEY,
    name       VARCHAR        NOT NULL,
    cost       NUMERIC(19, 2) NOT NULL CHECK (cost >= 0),
    company_id BIGINT         NOT NULL REFERENCES company,
    CONSTRAINT name_comp_uni UNIQUE (name, company_id)
);

CREATE TABLE orders
(
    id            BIGSERIAL PRIMARY KEY,
    name          VARCHAR,
    date          TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT now(),
    user_id       BIGINT                      NOT NULL REFERENCES users,
    product_id    BIGINT                      NOT NULL REFERENCES product,
    count         INTEGER                     NOT NULL CHECK ( count > 0 ),
    selling_price NUMERIC(19, 2)
);

CREATE FUNCTION set_price()
    RETURNS trigger
    LANGUAGE 'plpgsql'
AS
$BODY$
BEGIN
    UPDATE orders SET selling_price = (SELECT cost FROM product WHERE id = NEW.product_id) WHERE id = NEW.id;
    RETURN NEW;
END;
$BODY$;

CREATE TRIGGER set_price
    AFTER INSERT
    ON orders
    FOR EACH ROW
EXECUTE PROCEDURE set_price();

INSERT INTO company (name)
VALUES ('companyA'),
       ('companyB'),
       ('companyC');

INSERT INTO product (name, cost, company_id)
VALUES ('prod5', 2.00, 1),
       ('prod2', 25.68, 2),
       ('prod3', 5.8, 1),
       ('test4', 99999.2, 3),
       ('test1', 12.01, 2);

INSERT INTO users (name)
VALUES ('Igor'),
       ('Petr'),
       ('John');

INSERT INTO orders(user_id, product_id, count)
VALUES (1, 5, 2),
       (1, 3, 1),
       (3, 5, 1),
       (3, 1, 3),
       (2, 2, 2);
