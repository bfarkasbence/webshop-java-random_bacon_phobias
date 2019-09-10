package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.ShoppingCartDao;
import com.codecool.shop.model.Product;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCartDaoMem implements ShoppingCartDao {

    private List<Product> cartItem = new ArrayList<>();
    private static ShoppingCartDaoMem instance = null;

    private ShoppingCartDaoMem(){
    }

    public static ShoppingCartDaoMem getInstance() {
        if (instance == null) {
            instance = new ShoppingCartDaoMem();
        }
        return instance;
    }

    @Override
    public void add(Product product) {
        product.setId(cartItem.size() + 1);
        cartItem.add(product);
    }

    @Override
    public Product find(int id) {
        return cartItem.stream().filter(t -> t.getId() == id).findFirst().orElse(null);
    }

    @Override
    public void remove(int id) {
        cartItem.remove(find(id));
    }

    @Override
    public List<Product> getAll() {
        return cartItem;
    }
}
