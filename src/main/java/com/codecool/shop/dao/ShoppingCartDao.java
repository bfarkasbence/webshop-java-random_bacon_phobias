package com.codecool.shop.dao;

import com.codecool.shop.model.OrderedItem;
import com.codecool.shop.model.Product;

import java.util.List;

public interface ShoppingCartDao {

    void add(OrderedItem product);
    Product find(int id);
    void remove(int id);

    List<OrderedItem> getAll();
    float getTotalPrice();
}
