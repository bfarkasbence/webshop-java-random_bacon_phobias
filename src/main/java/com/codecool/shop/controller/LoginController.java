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
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/login")
public class LoginController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext context = new WebContext(req, resp, req.getServletContext());
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        engine.process("login.html", context, resp.getWriter());
    }

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
            System.out.println("siker");
            HttpSession session = req.getSession();
            session.setAttribute("logged-in", true);
            session.setAttribute("username", userName);
            System.out.println(session);
            System.out.println(session.getAttribute("logged-in"));
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            PrintWriter message = resp.getWriter();
            message.print(true);
            message.flush();

        }
        else {
            //TODO wrong pw or username -> send back json
            System.out.println("sikertelen");
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            PrintWriter message = resp.getWriter();
            message.print(false);
            message.flush();
        }
    }
}
