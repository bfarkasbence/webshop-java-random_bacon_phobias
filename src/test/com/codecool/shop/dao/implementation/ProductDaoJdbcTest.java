package com.codecool.shop.dao.implementation;

import com.codecool.shop.model.Product;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductDaoJdbcTest {

    ProductDaoJdbc productDaoJdbc = new ProductDaoJdbc();

    @Test
    void testFindById() {
        Product product = productDaoJdbc.find(0);
        assertEquals("Fake Coffee",product.getName());
    }

    @Test
    void remove() {
        productDaoJdbc.remove(0);
        Product product = productDaoJdbc.find(0);
        assertNull(product);
    }
}