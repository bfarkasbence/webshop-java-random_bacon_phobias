package com.codecool.shop.dao.implementation;

import com.codecool.shop.model.OrderedItem;

import java.sql.*;

public class OrderedItemsDaoJdbc extends DaoJdbc {
    public void add(ShoppingCartDaoMem shoppingCart, int customerId, String transactionId) {
        for (OrderedItem item : shoppingCart.cartItems) {
            String sqlStatement = "INSERT INTO ordered_items (customer_id, transaction_id, product_id, product_number) VALUES (?, ?, ?, ?);";
            try (Connection connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
                 PreparedStatement statement = connection.prepareStatement(sqlStatement);) {
                statement.setInt(1, customerId);
                statement.setString(2, transactionId);
                statement.setInt(3, item.getId());
                statement.setInt(4, item.getProductCounter());

                statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
