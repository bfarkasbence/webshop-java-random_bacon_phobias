package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.model.ProductCategory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductCategoryDaoJdbc extends DaoJdbc implements ProductCategoryDao {
    @Override
    public void add(ProductCategory category) {
        String sqlStatement = "INSERT INTO category (name, department, description) VALUES (?,?,?);";
        try (Connection connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(sqlStatement);) {
            statement.setString(1, category.getName());
            statement.setString(2, category.getDepartment());
            statement.setString(3, category.getDescription());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ProductCategory find(int id) {
        String sqlStatement = "SELECT * FROM category WHERE id = ?;";
        try (Connection connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(sqlStatement)) {
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                ProductCategory category = new ProductCategory(
                        result.getString("name"),
                        result.getString("department"),
                        result.getString("description"));
                category.setId(result.getInt("id"));
                return category;
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void remove(int id) {
        String sqlStatement = "DELETE FROM category WHERE id = ?; ";
        try (Connection connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(sqlStatement)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<ProductCategory> getAll() {
        List<ProductCategory> result = new ArrayList<ProductCategory>();
        String sqlStatement = "SELECT * FROM category";
        try (Connection connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(sqlStatement)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                ProductCategory category = new ProductCategory(
                        resultSet.getString("name"),
                        resultSet.getString("department"),
                        resultSet.getString("description"));
                category.setId(resultSet.getInt("id"));
                result.add(category);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void main(String[] args) {
        ProductCategory lager = new ProductCategory("Lager2", "Beer", "asd");
        ProductCategoryDao category = new ProductCategoryDaoJdbc();
      category.add(lager);
/*
        category.remove(4);
        category.find(5);
*/
        category.getAll();
    }
}
