package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoJdbc extends DaoJdbc implements com.codecool.shop.dao.ProductDao {
    ProductCategoryDao categoryDao = new ProductCategoryDaoJdbc();
    SupplierDao supplierDao = new SupplierDaoJdbc();

    @Override
    public void add(Product product) {
        String sqlStatement = "INSERT INTO products (name," +
                "category_id," +
                "default_price," +
                "default_currency," +
                "supplier_id,description) VALUES (?,?,?,?,?,?)";
        try (Connection connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(sqlStatement)) {
            //REGISTER JDBC DRIVER
            Class.forName(JDBC_DRIVER);
            //EXECUTE QUERY
            statement.setString(1, product.getName());
            statement.setInt(2, product.getProductCategory().getId());
            statement.setDouble(3, product.getDefaultPrice());
            statement.setString(4, product.getDefaultCurrency().getCurrencyCode());
            statement.setInt(5, product.getSupplier().getId());
            statement.setString(6, product.getDescription());

            statement.execute();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public Product find(int id) {
        String sqlStatement = "SELECT * FROM products WHERE id = ?;";
        try (Connection connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(sqlStatement)) {
            statement.setInt(1,id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int category_id = resultSet.getInt("category_id");
                int supplier_id = resultSet.getInt("supplier_id");
                Product result = new Product(
                        resultSet.getString("name"),
                        resultSet.getInt("default_price"),
                        resultSet.getString("default_currency"),
                        resultSet.getString("description"),
                        categoryDao.find(category_id),
                        supplierDao.find(supplier_id));
                result.setId(resultSet.getInt("id"));
                return result;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void remove(int id) {
    String sqlStatement = "DELETE FROM products WHERE id = ?;";
    try(Connection connection = DriverManager.getConnection(DB_URL,USERNAME,PASSWORD);
        PreparedStatement statement = connection.prepareStatement(sqlStatement)){
        statement.setInt(1,id);
        statement.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    }
    }

    @Override
    public List<Product> getAll() {
        List<Product> result = new ArrayList<>();
        String sqlStatement = "SELECT * FROM products;";
        try(Connection connection = DriverManager.getConnection(DB_URL,USERNAME,PASSWORD);
            PreparedStatement statement = connection.prepareStatement(sqlStatement)){
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                int category_id = resultSet.getInt("category_id");
                int supplier_id = resultSet.getInt("supplier_id");
                Product resultProduct = new Product(
                        resultSet.getString("name"),
                        resultSet.getInt("default_price"),
                        resultSet.getString("default_currency"),
                        resultSet.getString("description"),
                        categoryDao.find(category_id),
                        supplierDao.find(supplier_id));
                resultProduct.setId(resultSet.getInt("id"));
                result.add(resultProduct);


            }

            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Product> getBy(Supplier supplier) {
        List<Product> result = new ArrayList<Product>();
        String sqlStatement = "SELECT * FROM products WHERE supplier_id = ?;";
        try(Connection connection = DriverManager.getConnection(DB_URL,USERNAME,PASSWORD);
            PreparedStatement statement = connection.prepareStatement(sqlStatement)){
            int supplier_id = supplier.getId();
            statement.setInt(1,supplier_id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                int category_id = resultSet.getInt("category_id");
                Product resultProduct = new Product(
                        resultSet.getString("name"),
                        resultSet.getInt("default_price"),
                        resultSet.getString("default_currency"),
                        resultSet.getString("description"),
                        categoryDao.find(category_id),
                        supplierDao.find(supplier_id));
                resultProduct.setId(resultSet.getInt("id"));
                result.add(resultProduct);
            }
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    return null;
    }

    @Override
    public List<Product> getBy(ProductCategory productCategory) {
        List<Product> result = new ArrayList<Product>();
        String sqlStatement = "SELECT * FROM products WHERE category_id = ?;";
        try (Connection connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(sqlStatement)) {
            int category_id = productCategory.getId();
            statement.setInt(1, category_id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int supplier_id = resultSet.getInt("supplier_id");
                Product resultProduct = new Product(
                        resultSet.getString("name"),
                        resultSet.getInt("default_price"),
                        resultSet.getString("default_currency"),
                        resultSet.getString("description"),
                        categoryDao.find(category_id),
                        supplierDao.find(supplier_id));
                resultProduct.setId(resultSet.getInt("id"));
                result.add(resultProduct);
            }
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    return null;
    }

        @Override
    public List<Product> getBy(ProductCategory productCategory, Supplier supplier) {
            List<Product> result = new ArrayList<Product>();
            String sqlStatement = "SELECT * FROM products WHERE category_id = ? AND supplier_id = ?;";
            try (Connection connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
                 PreparedStatement statement = connection.prepareStatement(sqlStatement)) {
                int category_id = productCategory.getId();
                int supplier_id = supplier.getId();
                statement.setInt(1, category_id);
                statement.setInt(2,supplier_id);
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    Product resultProduct = new Product(
                            resultSet.getString("name"),
                            resultSet.getInt("default_price"),
                            resultSet.getString("default_currency"),
                            resultSet.getString("description"),
                            categoryDao.find(category_id),
                            supplierDao.find(supplier_id));
                    resultProduct.setId(resultSet.getInt("id"));
                    result.add(resultProduct);
                }
                return result;
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return null;
        }


}
