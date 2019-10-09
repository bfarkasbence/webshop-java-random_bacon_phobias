package com.codecool.shop.controller;

import com.codecool.shop.dao.UsersDao;
import com.codecool.shop.dao.implementation.UsersDaoJdbc;
import com.codecool.shop.util.Util;
import org.json.JSONObject;
import org.json.JSONTokener;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet(urlPatterns = "/login")
public class LoginController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UsersDao usersDaoJdbc = new UsersDaoJdbc();
        BufferedReader reader = req.getReader();
        JSONTokener tokener = new JSONTokener(reader);
        JSONObject json = new JSONObject(tokener);
        String userName = json.getString("username");
        String plainTextPassword = json.getString("password");
        Util passwordChecker = new Util();
        if (passwordChecker.verify(plainTextPassword, usersDaoJdbc.getPasswordForUser(userName))){
            //TODO session logged in -> redirect to homepage
        }
        else {
            //TODO wrong pw or username -> send back json
        }
        super.doPost(req, resp);
    }
}
