package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.implementation.CustomerDataDaoMem;
import com.codecool.shop.dao.implementation.ShoppingCartDaoMem;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/checkout")
public class CheckoutController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ShoppingCartDaoMem shoppingCart = ShoppingCartDaoMem.getInstance();
        CustomerDataDaoMem customerDataDaoMem = CustomerDataDaoMem.getInstance();
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
        engine.process("checkout.html", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CustomerDataDaoMem customerDataDaoMem = CustomerDataDaoMem.getInstance();
        customerDataDaoMem.put("fullName", req.getParameter("name"));
        customerDataDaoMem.put("email", req.getParameter("email"));
        customerDataDaoMem.put("phoneNumber", req.getParameter("phoneNumber"));
        customerDataDaoMem.put("billingAddress", req.getParameter("billingAddress"));
        customerDataDaoMem.put("shippingAddress", req.getParameter("shippingAddress"));
        resp.sendRedirect("/review");

    }
}
