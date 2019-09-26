package com.codecool.shop.dao.implementation;

import com.codecool.shop.model.Supplier;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SupplierDaoJdbcTest {

    SupplierDaoJdbc supplierDaoJdbc = new SupplierDaoJdbc();

    @Test
    void testFindSupplierById() {
        Supplier supplier = supplierDaoJdbc.find(1);
        assertEquals("First Craft Beer",supplier.getName());
    }

    @Test
    void testRemoveSupplierById() {
        supplierDaoJdbc.remove(7);
        assertNull(supplierDaoJdbc.find(7));
    }

    @Test
    void testAddingSupplierToDataBase() {
        Supplier supplier = new Supplier("TestSupplier","test");
        supplierDaoJdbc.add(supplier);
        assertNotNull(supplierDaoJdbc.find(7));

    }
}