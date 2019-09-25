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

@WebServlet("/review")
public class ReviewController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ShoppingCartDaoMem shoppingCart = ShoppingCartDaoMem.getInstance();
        CustomerDataDaoMem customerDataDaoMem = CustomerDataDaoMem.getInstance();
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
        context.setVariable("fullName", customerDataDaoMem.get("fullName"));
        context.setVariable("email", customerDataDaoMem.get("email"));
        context.setVariable("phoneNumber", customerDataDaoMem.get("phoneNumber"));
        context.setVariable("billingAddress", customerDataDaoMem.get("billingAddress"));
        context.setVariable("shippingAddress", customerDataDaoMem.get("shippingAddress"));
        engine.process("review.html", context, resp.getWriter());
    }

}
