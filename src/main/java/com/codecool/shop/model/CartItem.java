package com.codecool.shop.model;

public class CartItem {

    public int productCounter = 0;
    public int productId;

    public CartItem(int productId) {
        this.productId = productId;
        productCounter++;
    }
}
