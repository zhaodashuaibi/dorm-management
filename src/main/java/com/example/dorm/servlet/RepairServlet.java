package com.example.dorm.servlet;

import com.example.dorm.dao.RepairDAO;
import com.example.dorm.dao.StudentDAO;
import com.example.dorm.model.Repair;
import com.example.dorm.model.Student;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/repairs")
public class RepairServlet extends HttpServlet {
    private RepairDAO repairDAO = new RepairDAO();
    private StudentDAO studentDAO = new StudentDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Repair> repairs = repairDAO.list();
        req.setAttribute("repairs", repairs);
        req.getRequestDispatcher("/jsp/admin/repair_list.jsp").forward(req, resp);
    }

    // 提交报修单
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int studentId = Integer.parseInt(req.getParameter("studentId"));
        String title = req.getParameter("title");
        String description = req.getParameter("description");

        Repair repair = new Repair();
        repair.setStudentId(studentId);
        repair.setTitle(title);
        repair.setDescription(description);
        repair.setStatus("pending");
        repair.setCreateTime(String.valueOf(System.currentTimeMillis()));

        repairDAO.add(repair);
        resp.sendRedirect(req.getContextPath() + "/repairs");
    }
}
