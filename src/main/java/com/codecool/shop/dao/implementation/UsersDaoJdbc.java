package com.codecool.shop.dao.implementation;


import com.codecool.shop.dao.UsersDao;

import java.sql.*;

public class UsersDaoJdbc extends DaoJdbc implements UsersDao {


    @Override
    public String getPasswordForUser(String username) {
        String sqlStatement = "SELECT password FROM users WHERE username = ?;";
        try (Connection connection = DriverManager.getConnection(DB_URL,USERNAME,PASSWORD);
             PreparedStatement statement = connection.prepareStatement(sqlStatement)){
            statement.setString(1, username);
            ResultSet result = statement.executeQuery();
            return result.getString("password");


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
