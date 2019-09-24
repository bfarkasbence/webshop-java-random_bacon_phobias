package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.ShoppingCartDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.implementation.ShoppingCartDaoMem;
import com.codecool.shop.model.OrderedItem;
import com.codecool.shop.model.Product;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(urlPatterns = {"/shoppingCart"})
public class ShoppingCartController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ShoppingCartDaoMem shoppingCart = ShoppingCartDaoMem.getInstance();
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
        context.setVariable("cartItems",shoppingCart.cartItems);
        engine.process("shoppingCart.html", context, resp.getWriter());
    }

    @Override
    protected void doPost (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ShoppingCartDaoMem shoppingCart = ShoppingCartDaoMem.getInstance();
        int productId;
        if (req.getParameter("subtract") != null){
            productId = Integer.parseInt(req.getParameter("subtract"));
            subtractItem(shoppingCart, productId);
        }
        else {
            productId = Integer.parseInt(req.getParameter("add"));
            addItem(shoppingCart, productId);
        }
        resp.sendRedirect("/shoppingCart");
    }

    private void addItem (ShoppingCartDaoMem shoppingCart, int productId){
        for (OrderedItem item:shoppingCart.cartItems) {
            if (item.getId() == productId) {
                item.increaseQuantity();
                break;
            }
        }
    }

    private void subtractItem (ShoppingCartDaoMem shoppingCart, int productId){
        for (OrderedItem item:shoppingCart.cartItems) {
            if(item.getId() == productId) {
                if (item.getProductCounter()>1  ){
                    item.decreaseQuantity();
                    break;
                }
                else {
                    shoppingCart.cartItems.remove(item);
                    break;
                }
            }
        }
    }
}

