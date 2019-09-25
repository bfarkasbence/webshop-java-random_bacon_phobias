package com.codecool.shop.controller;

import com.codecool.shop.dao.implementation.CustomerDataDaoMem;
import com.codecool.shop.dao.implementation.ShoppingCartDaoMem;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/thank-you")
public class ThankYouController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        String transactionId = req.getParameter("transactionId");
        ShoppingCartDaoMem shoppingCart = ShoppingCartDaoMem.getInstance();
        CustomerDataDaoMem customerData = CustomerDataDaoMem.getInstance();
        System.out.println(shoppingCart);
        System.out.println(customerData);
        System.out.println(transactionId);
        // TODO move data to the database order table

        shoppingCart.clear();
        customerData.clear();
        resp.sendRedirect("/");
    }
}

