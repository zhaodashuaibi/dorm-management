package com.example.dorm.servlet;

import com.example.dorm.dao.DormitoryDAO;
import com.example.dorm.model.Dormitory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/dormitories")
public class DormitoryServlet extends HttpServlet {
    private DormitoryDAO dormitoryDAO = new DormitoryDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Dormitory> dormitories = dormitoryDAO.list();
        req.setAttribute("dormitories", dormitories);
        req.getRequestDispatcher("/jsp/admin/dormitory_list.jsp").forward(req, resp);
    }

    // 添加宿舍楼功能
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String address = req.getParameter("address");

        Dormitory dormitory = new Dormitory();
        dormitory.setName(name);
        dormitory.setAddress(address);

        dormitoryDAO.add(dormitory);
        resp.sendRedirect(req.getContextPath() + "/dormitories");
    }
}
