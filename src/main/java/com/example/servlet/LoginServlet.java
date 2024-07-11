package com.example.servlet;

import com.example.Users;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static final String LOGIN_PAGE = "/login.jsp";
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        String user = (String) req.getSession().getAttribute("user");
        if (user != null) {
            try {
                resp.sendRedirect("/user/hello.jsp");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                resp.sendRedirect(LOGIN_PAGE);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        String user = req.getParameter("login");
        String password = req.getParameter("password");
        if (Users.getInstance().getUsers().contains(user) && password != null && !password.isEmpty()) {
            HttpSession session = req.getSession();
            session.setAttribute("user", user);
            try {
                resp.sendRedirect("/user/hello.jsp");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                req.getRequestDispatcher(LOGIN_PAGE).forward(req, resp);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
