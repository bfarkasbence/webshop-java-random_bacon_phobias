ALTER TABLE IF EXISTS ONLY public.products
    DROP CONSTRAINT IF EXISTS pk_product_key CASCADE;
ALTER TABLE IF EXISTS ONLY public.category
    DROP CONSTRAINT IF EXISTS pk_category_key CASCADE;
ALTER TABLE IF EXISTS ONLY public.supplier
    DROP CONSTRAINT IF EXISTS pk_supplier_name CASCADE;
ALTER TABLE IF EXISTS ONLY public.customer
    DROP CONSTRAINT IF EXISTS pk_customer_id CASCADE;
ALTER TABLE IF EXISTS ONLY public.ordered_items
    DROP CONSTRAINT IF EXISTS pk_order_id CASCADE;
ALTER TABLE IF EXISTS ONLY public.products
    DROP CONSTRAINT IF EXISTS fk_category_name CASCADE;
ALTER TABLE IF EXISTS ONLY public.products
    DROP CONSTRAINT IF EXISTS fk_supplier_name CASCADE;
ALTER TABLE IF EXISTS ONLY public.users
    DROP CONSTRAINT IF EXISTS pk_user_id CASCADE;
ALTER TABLE IF EXISTS ONLY public.cart_items
    DROP CONSTRAINT IF EXISTS pk_cart_item_id CASCADE;
ALTER TABLE IF EXISTS ONLY public.cart_items
    DROP CONSTRAINT IF EXISTS fk_product_id CASCADE;
ALTER TABLE IF EXISTS ONLY public.cart_items
    DROP CONSTRAINT IF EXISTS fk_user_id CASCADE;


DROP TABLE IF EXISTS public.products;
DROP TABLE IF EXISTS public.supplier;
DROP TABLE IF EXISTS public.category;
DROP TABLE IF EXISTS public.customer;
DROP TABLE IF EXISTS public.ordered_items;
DROP TABLE IF EXISTS public.users;
DROP TABLE IF EXISTS public.cart_items;

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

DROP SEQUENCE IF EXISTS supplier_id_seq;
CREATE TABLE supplier
(
    id          serial NOT NULL,
    name        varchar(255),
    description varchar(255)
);

DROP SEQUENCE IF EXISTS customer_id_seq;
CREATE TABLE customer
(
    id              serial NOT NULL,
    name            varchar(255),
    email           varchar(255),
    phone_number     varchar(16),
    billing_address  varchar(255),
    shipping_address varchar(255),
    user_id          int
);

DROP SEQUENCE IF EXISTS ordered_items_id_seq;
CREATE TABLE ordered_items
(
    id              serial NOT NULL,
    customer_id     int,
    transaction_id  varchar(32),
    product_id      int,
    product_number  int
);

DROP SEQUENCE IF EXISTS cart_items_id_seq;
CREATE TABLE cart_items
(
    id              serial NOT NULL,
    user_id         int,
    session_id      varchar(255),
    product_id      int,
    product_number  int
);

DROP SEQUENCE IF EXISTS users_id_seq;
CREATE TABLE users
(
  id                serial NOT NULL,
  username          varchar(32) NOT NULL,
  password          varchar(255) NOT NULL,
  email             varchar(255) NOT NULL

);

ALTER TABLE ONLY cart_items
    ADD CONSTRAINT pk_cart_item_id PRIMARY KEY (id);
ALTER TABLE ONLY products
    ADD CONSTRAINT pk_product_key PRIMARY KEY (id);
ALTER TABLE ONLY category
    ADD CONSTRAINT pk_category_key PRIMARY KEY (id);
ALTER TABLE ONLY supplier
    ADD CONSTRAINT pk_supplier_name PRIMARY KEY (id);
ALTER TABLE ONLY customer
    ADD CONSTRAINT pk_customer_id PRIMARY KEY (id);
ALTER TABLE ONLY ordered_items
    ADD CONSTRAINT pk_order_id PRIMARY KEY (id);
ALTER TABLE ONLY users
    ADD CONSTRAINT pk_user_id PRIMARY KEY (id);
ALTER TABLE ONLY products
    ADD CONSTRAINT fk_category_name FOREIGN KEY (category_id) REFERENCES category (id);
ALTER TABLE ONLY cart_items
    ADD CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES users (id);
ALTER TABLE ONLY cart_items
    ADD CONSTRAINT fk_product_id FOREIGN KEY (product_id) REFERENCES products (id);
ALTER TABLE ONLY products
    Add CONSTRAINT fk_supplier_name FOREIGN KEY (supplier_id) REFERENCES supplier (id);
ALTER TABLE ONLY ordered_items
    ADD CONSTRAINT fk_customer_id FOREIGN KEY (customer_id) REFERENCES customer (id);
ALTER TABLE ONLY ordered_items
    ADD CONSTRAINT fk_product_id FOREIGN KEY (product_id) REFERENCES products (id);
ALTER TABLE ONLY customer
    ADD CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES users (id);
