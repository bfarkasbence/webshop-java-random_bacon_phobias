package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.ShoppingCartDao;
import com.codecool.shop.model.OrderedItem;
import com.codecool.shop.model.Product;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.codecool.shop.dao.implementation.DaoJdbc.*;

public class ShoppingCartDaoJdbc implements ShoppingCartDao {

    public List<OrderedItem> cartItems = new ArrayList<>();
    private static ShoppingCartDaoJdbc instance = null;

    private ShoppingCartDaoJdbc() {
    }

    public static ShoppingCartDaoJdbc getInstance() {
        if (instance == null) {
            instance = new ShoppingCartDaoJdbc();
        }
        return instance;
    }

    @Override
    public void add(OrderedItem product) {
        String sqlStatement = "INSERT INTO cart_items (user_id, session_id, product_id, product_number) VALUES (?, ?, ?, ?);";
        try (Connection connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(sqlStatement);){
            statement.setInt(1, -1); //TODO need to write the actual user if logged in else -1
            statement.setString(2, "session_id"); //TODO need to write the actual session id
            statement.setInt(3, product.getId());
            statement.setInt(4, product.getProductCounter());
                    

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public Product find(int id) {
        return cartItems.stream().filter(t -> t.getId() == id).findFirst().orElse(null);
    }

    @Override
    public void remove(int id) {
       String sqlStatement = "DELETE FROM cart_items WHERE product_id = ?;";
       try (Connection connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            PreparedStatement statement = connection.prepareStatement(sqlStatement)){
           statement.setInt(1, id);
           statement.executeUpdate();
       }
       catch (SQLException e) {
           e.printStackTrace();
       }
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
