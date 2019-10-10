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

    @Override
    public boolean checkUserNameIsUsed(String userName) {
        String sqlStatement = "SELECT username FROM users WHERE username = ?;";
        try( Connection connection = DriverManager.getConnection(DB_URL,USERNAME,PASSWORD);
        PreparedStatement statement = connection.prepareStatement(sqlStatement)){
        statement.setString(1, userName);
        ResultSet result = statement.executeQuery();
        if (!result.next()) { return false;}
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public boolean checkEmailIsUsed(String email) {
        String sqlStatement = "SELECT email FROM users WHERE email = ?;";
        try( Connection connection = DriverManager.getConnection(DB_URL,USERNAME,PASSWORD);
             PreparedStatement statement = connection.prepareStatement(sqlStatement)){
            statement.setString(1, email);
            ResultSet result = statement.executeQuery();
            if (!result.next()) {return false;}

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public void registerUser(String username, String password, String email) {
        String sqlStatement = "INSERT INTO users (username, password, email) VALUES (?, ?, ?)";
        try (Connection connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(sqlStatement)) {
            statement.setString(1, username);
            statement.setString(2, password);
            statement.setString(3, email);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
