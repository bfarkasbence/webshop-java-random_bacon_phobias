package com.codecool.shop.dao.implementation;

import java.sql.*;

public class CustomerDataDaoJdbc extends DaoJdbc {
    public void add(CustomerDataDaoMem customerData) {
        String sqlStatement = "INSERT INTO customer (name, email, phone_number, billing_address, shipping_address) VALUES (?, ?, ?, ?, ?);";
        try (Connection connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(sqlStatement);) {
            statement.setString(1, customerData.get("fullName").toString());
            statement.setString(2, customerData.get("email").toString());
            statement.setString(3, customerData.get("phoneNumber").toString());
            statement.setString(4, customerData.get("billingAddress").toString());
            statement.setString(5, customerData.get("shippingAddress").toString());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getLastCustomerId() {
        String sqlStatement = "SELECT MAX(id) AS max_id FROM customer;";
        try (Connection connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            PreparedStatement statement = connection.prepareStatement(sqlStatement)) {
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()){
                int customerId = resultSet.getInt("max_id");
                return customerId;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }
}
