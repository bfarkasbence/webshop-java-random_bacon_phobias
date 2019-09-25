package com.codecool.shop.dao.implementation;

import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import java.sql.*;
import java.util.List;

public class ProductDaoJdbc extends DaoJdbc implements com.codecool.shop.dao.ProductDao {

    @Override
    public void add(Product product) {
        String sqlStatement = "INSERT INTO products (name,category,default_price,default_currency,supplier) VALUES (?,?,?,?,?)";
        try (Connection connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(sqlStatement)) {
            //REGISTER JDBC DRIVER
            Class.forName(JDBC_DRIVER);
            //EXECUTE QUERY
            statement.setString(1, product.getName());
            statement.setString(2, product.getProductCategory().getName());
            statement.setDouble(3, product.getDefaultPrice());
            statement.setString(4, product.getDefaultCurrency().getSymbol());
            statement.setString(5, product.getSupplier().getName());

            statement.execute();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public Product find(int id) {
        return null;
    }

    @Override
    public void remove(int id) {

    }

    @Override
    public List<Product> getAll() {
        return null;
    }

    @Override
    public List<Product> getBy(Supplier supplier) {
        return null;
    }

    @Override
    public List<Product> getBy(ProductCategory productCategory) {
        return null;
    }

    @Override
    public List<Product> getBy(ProductCategory productCategory, Supplier supplier) {
        return null;
    }


    public static void main(String[] args) {
        ProductCategory stout = new ProductCategory("Stout", "Beer", "Beer is a teast flued witch make you smile");
        Supplier legenda = new Supplier("Legenda Brewing", "Beer");
        Product product = new Product("Milk Stout", 900, "HUF", "Black stout beer", stout, legenda);
/*
//        Product product1 = new Product("CodeCool Beer",900,"HUF","Tasty IPA from the best!",stout,codecool);
*/

        ProductDaoJdbc conn = new ProductDaoJdbc();
        conn.add(product);
    }
}
