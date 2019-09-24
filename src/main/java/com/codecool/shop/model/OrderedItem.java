package com.codecool.shop.model;

public class OrderedItem extends Product {

    private int productCounter;


    public OrderedItem(String name, float defaultPrice, String currencyString, String description, ProductCategory productCategory, Supplier supplier) {
        super(name, defaultPrice, currencyString, description, productCategory, supplier);
        productCounter++;
    }

    public void increaseQuantity(){
        productCounter++;
    }

    public void decreaseQuantity(){
        productCounter--;
    }

    public int getProductCounter() {
        return productCounter;
    }
}
