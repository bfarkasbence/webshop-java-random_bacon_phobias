package com.codecool.shop.dao.implementation;

import com.codecool.shop.model.ProductCategory;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ProductCategoryDaoJdbcTest {

    ProductCategoryDaoJdbc productCategoryDaoJdbc = new ProductCategoryDaoJdbc();

    @Test
    void testFindFunction() {
        ProductCategory productCategory = productCategoryDaoJdbc.find(1);
        assertEquals("IPA",productCategory.getName());
    }

    @Test
    void testRemoveFunction() {
    }

    @Test
    void testGetAllFunction() {
       // productCategoryDaoJdbc.getAll()
    }
}