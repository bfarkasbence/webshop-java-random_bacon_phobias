package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.model.Supplier;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SupplierDaoJdbc extends DaoJdbc implements SupplierDao {
    @Override
    public void add(Supplier supplier) {
        String sqlStatement = "INSERT INTO supplier (name, description) VALUES (?,?);";
        try (Connection connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(sqlStatement)) {

            statement.setString(1, supplier.getName());
            statement.setString(2, supplier.getDescription());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public Supplier find(int id) {
        String sqlStatement = "SELECT * FROM supplier WHERE id = ?;";
        try (Connection connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(sqlStatement)) {
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                Supplier resultSupplier = new Supplier(
                        result.getString("name"),
                        result.getString("description"));
                resultSupplier.setId(result.getInt("id"));
                return resultSupplier;
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void remove(int id) {
        String sqlStatement = "DELETE FROM supplier WHERE id = ?;";
        try (Connection connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(sqlStatement)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<Supplier> getAll() {
        List<Supplier> result = new ArrayList<Supplier>();
        String sqlStatement = "SELECT * FROM supplier";
        try(Connection connection = DriverManager.getConnection(DB_URL,PASSWORD,USERNAME);
            PreparedStatement statement = connection.prepareStatement(sqlStatement)){
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                Supplier resultSupplier = new Supplier(
                        resultSet.getString("name"),
                        resultSet.getString("description"));
                resultSupplier.setId(resultSet.getInt("id"));
                result.add(resultSupplier);
            }
            System.out.println(result);
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        Supplier codecool = new Supplier("CodeCool13", "Beer");
        SupplierDaoJdbc supplier = new SupplierDaoJdbc();
        supplier.add(codecool);
        supplier.find(5);
        supplier.find(3);
        supplier.getAll();
        /*
        supplier.remove(2);
*/
    }
}
