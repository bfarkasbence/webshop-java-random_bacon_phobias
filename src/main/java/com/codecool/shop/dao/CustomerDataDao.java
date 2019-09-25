package com.codecool.shop.dao;

import com.codecool.shop.model.Supplier;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface CustomerDataDao {

    void put(String key, String value);

    void remove(String key);

    void replace(String key, String value);

    Object get(Object key);
    void clear();
}