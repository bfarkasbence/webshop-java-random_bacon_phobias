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
        CustomerDataDaoMem customerData = CustomerDataDaoMem.getInstance();
        WebContext context = new WebContext(req, resp, req.getServletContext());
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
            context.setVariable("fullName", customerData.get("fullName"));
            context.setVariable("email", customerData.get("email"));
            context.setVariable("phoneNumber", customerData.get("phoneNumber"));
            context.setVariable("billingAddress", customerData.get("billingAddress"));
            context.setVariable("shippingAddress", customerData.get("shippingAddress"));
        engine.process("checkout.html", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CustomerDataDaoMem customerData = CustomerDataDaoMem.getInstance();
        customerData.put("fullName", req.getParameter("name"));
        customerData.put("email", req.getParameter("email"));
        customerData.put("phoneNumber", req.getParameter("phoneNumber"));
        customerData.put("billingAddress", req.getParameter("billingAddress"));
        customerData.put("shippingAddress", req.getParameter("shippingAddress"));
        resp.sendRedirect("/review");

    }
}
