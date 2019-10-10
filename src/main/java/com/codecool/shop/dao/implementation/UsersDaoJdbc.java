package com.codecool.shop.dao.implementation;


import com.codecool.shop.dao.UsersDao;
import com.codecool.shop.util.Util;

import java.sql.*;

public class UsersDaoJdbc extends DaoJdbc implements UsersDao {
    private final Util util = new Util();

    @Override
    public String getPasswordForUser(String username) {
        String sqlStatement = "SELECT password FROM users WHERE username = ?;";
        try (Connection connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(sqlStatement)) {
            statement.setString(1, username);
            ResultSet result = statement.executeQuery();
            if (result.next()){return result.getString("password");}
            return null;


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void register(String username, String password, String email) {
        String sqlStatement = "INSERT INTO users (username, password, email) VALUES (?, ?, ?)";
        try (Connection connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(sqlStatement)) {
            statement.setString(1, username);
            statement.setString(2, util.hash(password));
            statement.setString(3, email);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        UsersDaoJdbc usr = new UsersDaoJdbc();
        usr.register("admin", "admin", "admin@admin.com");
    }
}
