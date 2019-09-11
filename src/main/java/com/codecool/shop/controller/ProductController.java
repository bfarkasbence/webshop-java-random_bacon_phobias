package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
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

import static java.lang.Integer.parseInt;

@WebServlet(urlPatterns = {"/"})
public class ProductController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        SupplierDao supplierDataStore = SupplierDaoMem.getInstance();

        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());

        int selectedCategory = getFilterParameter(req, "categoryFilter");
        int selectedSupplier = getFilterParameter(req, "supplierFilter");

        context.setVariable("categories", productCategoryDataStore.getAll());
        context.setVariable("suppliers", supplierDataStore.getAll());

        context.setVariable("category", productCategoryDataStore.find(selectedCategory));
        context.setVariable("supplier", supplierDataStore.find(selectedSupplier));
        context.setVariable("products", productDataStore.getBy(productCategoryDataStore.find(selectedCategory), supplierDataStore.find(selectedSupplier)));

        engine.process("product/index.html", context, resp.getWriter());
    }

    protected int getFilterParameter (HttpServletRequest req, String filterName) {
        if (req.getParameter(filterName)!=null)
        {
            return Integer.parseInt(req.getParameter(filterName));
        }
        else {
            return 0;
        }
    }
}
