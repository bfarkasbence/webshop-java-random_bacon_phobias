package com.codecool.shop.controller;

import com.codecool.shop.dao.implementation.CustomerDataDaoJdbc;
import com.codecool.shop.dao.implementation.CustomerDataDaoMem;
import com.codecool.shop.dao.implementation.OrderedItemsDaoJdbc;
import com.codecool.shop.dao.implementation.ShoppingCartDaoMem;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;

@WebServlet("/thank-you")
public class ThankYouController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        String transactionId = req.getParameter("transactionId");
        ShoppingCartDaoMem shoppingCart = ShoppingCartDaoMem.getInstance();
        CustomerDataDaoMem customerData = CustomerDataDaoMem.getInstance();


        // TODO move shoppingCart to the database ordered_items table
        CustomerDataDaoJdbc newCustomer = new CustomerDataDaoJdbc();
        newCustomer.add(customerData);
        OrderedItemsDaoJdbc newOrder = new OrderedItemsDaoJdbc();
        newOrder.add(shoppingCart, newCustomer.getLastCustomerId(), transactionId);


        shoppingCart.clear();
        customerData.clear();
        resp.sendRedirect("/");
    }
}

