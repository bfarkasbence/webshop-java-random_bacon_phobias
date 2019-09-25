package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.model.ProductCategory;

import java.sql.*;
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
            if (result.next()){
            String name = result.getString("name");
            String department = result.getString("department");
            String description = result.getString("description");
            Integer categoryId = result.getInt("id");
            ProductCategory category = new ProductCategory(name, department, description);
            category.setId(categoryId);
            System.out.println(category);
            return category;}


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void remove(int id) {
    String sqlStatement = "DELETE FROM category WHERE id = ?; ";
    try(Connection connection = DriverManager.getConnection(DB_URL,USERNAME,PASSWORD);
        PreparedStatement statement = connection.prepareStatement(sqlStatement)){
        statement.setInt(1,id);
        statement.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    }
    }

    @Override
    public List<ProductCategory> getAll() {
        return null;
    }

    public static void main(String[] args) {
        ProductCategory lager = new ProductCategory("Lager1", "Beer", "asd");
        ProductCategoryDao category = new ProductCategoryDaoJdbc();
/*        category.add(lager);
        category.remove(4);
        category.find(5);*/
    }
}
