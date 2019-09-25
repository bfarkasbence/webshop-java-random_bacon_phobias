package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.ShoppingCartDao;
import com.codecool.shop.model.OrderedItem;
import com.codecool.shop.model.Product;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCartDaoMem implements ShoppingCartDao {

    public List<OrderedItem> cartItems = new ArrayList<>();
    private static ShoppingCartDaoMem instance = null;

    private ShoppingCartDaoMem() {
    }

    public static ShoppingCartDaoMem getInstance() {
        if (instance == null) {
            instance = new ShoppingCartDaoMem();
        }
        return instance;
    }

    @Override
    public void add(OrderedItem product) {
        cartItems.add(product);
    }

    @Override
    public Product find(int id) {
        return cartItems.stream().filter(t -> t.getId() == id).findFirst().orElse(null);
    }

    @Override
    public void remove(int id) {
        cartItems.remove(find(id));
    }

    @Override
    public List<OrderedItem> getAll() {
        return cartItems;
    }

    @Override
    public void clear() {
        cartItems.clear();
    }

    @Override
    public float getTotalPrice() {
        float totalPrice = 0;
        for (OrderedItem item : cartItems) {
            totalPrice += (item.getDefaultPrice() * item.getProductCounter());
        }
        return totalPrice;
    }
}
