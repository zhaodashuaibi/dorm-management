package com.example.dorm.servlet;

import com.example.dorm.dao.UserDAO;
import com.example.dorm.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private UserDAO userDAO = new UserDAO();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        User u = userDAO.login(username, password);
        if (u != null) {
            HttpSession session = req.getSession();
            session.setAttribute("currentUser", u);
            if ("admin".equals(u.getType())) {
                resp.sendRedirect(req.getContextPath() + "/jsp/admin/index.jsp");
            } else {
                resp.sendRedirect(req.getContextPath() + "/jsp/student/index.jsp");
            }
        } else {
            req.setAttribute("msg", "用户名或密码错误");
            req.getRequestDispatcher("/jsp/login.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 登出功能
        String op = req.getParameter("op");
        if ("logout".equals(op)) {
            HttpSession session = req.getSession(false);
            if (session != null) session.invalidate();
            resp.sendRedirect(req.getContextPath() + "/jsp/login.jsp");
        } else {
            resp.sendRedirect(req.getContextPath() + "/jsp/login.jsp");
        }
    }
}
