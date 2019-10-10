package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.UsersDao;
import com.codecool.shop.dao.implementation.UsersDaoJdbc;
import com.codecool.shop.util.Util;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/register")
public class RegisterController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext context = new WebContext(req, resp, req.getServletContext());
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        engine.process("register.html", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UsersDao usersDao = new UsersDaoJdbc();
        Util util = new Util();
        BufferedReader reader = req.getReader();
        JSONTokener tokener = new JSONTokener(reader);
        JSONObject json = new JSONObject(tokener);
        String userName = json.getString("username");
        String plainTextPassword = json.getString("password");
        String email = json.getString("email");
        System.out.println(email);
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter message = resp.getWriter();
        if (usersDao.checkUserNameIsUsed(userName)){
            String jsonMessage = "Username is used";
            message.print(jsonMessage);
        }
        else if(usersDao.checkEmailIsUsed(email)){
            message.print("Email is used");
        }
        else{
            usersDao.registerUser(userName, util.hash(plainTextPassword), email);
            message.print(true);
        }
        message.flush();

    }
}
