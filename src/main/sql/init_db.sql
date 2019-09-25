ALTER TABLE IF EXISTS ONLY public.products
    DROP CONSTRAINT IF EXISTS pk_product_key CASCADE;
ALTER TABLE IF EXISTS ONLY public.category
    DROP CONSTRAINT IF EXISTS pk_category_key CASCADE;
ALTER TABLE IF EXISTS ONLY public.supplier
    DROP CONSTRAINT IF EXISTS pk_supplier_name CASCADE;
ALTER TABLE IF EXISTS ONLY public.products
    DROP CONSTRAINT IF EXISTS fk_category_name CASCADE;
ALTER TABLE IF EXISTS ONLY public.products
    DROP CONSTRAINT IF EXISTS fk_supplier_name CASCADE;

DROP table if exists public.products;
DROP TABLE IF EXISTS public.supplier;
DROP TABLE IF EXISTS public.category;

DROP SEQUENCE IF EXISTS products_id_seq;
CREATE TABLE products
(
    id               serial NOT NULL,
    name             varchar(255),
    category_id      int,
    default_price    float,
    default_currency varchar(10),
    supplier_id      int,
    description      varchar(255)
);
DROP SEQUENCE IF EXISTS category_id_seq;
CREATE TABLE category
(
    id          serial NOT NULL,
    name        varchar(255),
    department  varchar(255),
    description VARCHAR(255)
);
CREATE TABLE supplier
(
    id          serial NOT NULL,
    name        varchar(255),
    description varchar(255)
);

ALTER TABLE ONLY products
    ADD CONSTRAINT pk_product_key PRIMARY KEY (id);
ALTER TABLE ONLY category
    ADD CONSTRAINT pk_category_key PRIMARY KEY (id);
ALTER TABLE ONLY supplier
    ADD CONSTRAINT pk_supplier_name PRIMARY KEY (id);
ALTER TABLE ONLY products
    ADD CONSTRAINT fk_category_name FOREIGN KEY (category_id) REFERENCES category (id);
ALTER TABLE ONLY products
    Add CONSTRAINT fk_supplier_name FOREIGN KEY (supplier_id) REFERENCES supplier (id);