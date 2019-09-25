package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.model.Supplier;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class SupplierDaoJdbc extends DaoJdbc implements SupplierDao {
    @Override
    public void add(Supplier supplier) {
        String sqlStatement = "INSERT INTO supplier (name, description) VALUES (?,?);";
        try(Connection connection = DriverManager.getConnection(DB_URL,USERNAME, PASSWORD);
            PreparedStatement statement = connection.prepareStatement(sqlStatement)){

                statement.setString(1,supplier.getName());
                statement.setString(2,supplier.getDescription());

                statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public Supplier find(int id) {
        return null;
    }

    @Override
    public void remove(int id) {

    }

    @Override
    public List<Supplier> getAll() {
        return null;
    }

    public static void main(String[] args) {
        Supplier codecool = new Supplier("CodeCool", "Beer");
        SupplierDaoJdbc supplier = new SupplierDaoJdbc();
        supplier.add(codecool);
    }
}
