package com.example.dorm.servlet;

import com.example.dorm.dao.StudentDAO;
import com.example.dorm.model.Student;
import com.example.dorm.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/students")
public class StudentServlet extends HttpServlet {
    private StudentDAO studentDAO = new StudentDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 简单权限校验：必须是 admin
        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("currentUser") == null) {
            resp.sendRedirect(req.getContextPath() + "/jsp/login.jsp");
            return;
        }
        User u = (User) session.getAttribute("currentUser");
        if (!"admin".equals(u.getType())) {
            resp.getWriter().write("无权限访问");
            return;
        }

        List<Student> students = studentDAO.list();
        req.setAttribute("students", students);
        req.getRequestDispatcher("/jsp/admin/student_list.jsp").forward(req, resp);
    }

    // 添加学生功能
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String studentNo = req.getParameter("studentNo");
        String name = req.getParameter("name");
        String gender = req.getParameter("gender");
        String college = req.getParameter("college");
        String clazz = req.getParameter("class");
        String phone = req.getParameter("phone");

        // 假设每个学生注册时已经有 user_id（注册在 User 表中）
        Student s = new Student();
        s.setStudentNo(studentNo);
        s.setName(name);
        s.setGender(gender);
        s.setCollege(college);
        s.setClazz(clazz);
        s.setPhone(phone);

        studentDAO.add(s);
        resp.sendRedirect(req.getContextPath() + "/students");
    }
}
