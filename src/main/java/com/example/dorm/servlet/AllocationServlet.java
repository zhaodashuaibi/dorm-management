package com.example.dorm.servlet;

import com.example.dorm.dao.AllocationDAO;
import com.example.dorm.dao.StudentDAO;
import com.example.dorm.dao.RoomDAO;
import com.example.dorm.model.Allocation;
import com.example.dorm.model.Student;
import com.example.dorm.model.Room;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/allocations")
public class AllocationServlet extends HttpServlet {
    private AllocationDAO allocationDAO = new AllocationDAO();
    private RoomDAO roomDAO = new RoomDAO();
    private StudentDAO studentDAO = new StudentDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Allocation> allocations = allocationDAO.list();
        req.setAttribute("allocations", allocations);
        req.getRequestDispatcher("/jsp/admin/allocation_list.jsp").forward(req, resp);
    }

    // 分配宿舍
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int studentId = Integer.parseInt(req.getParameter("studentId"));
        int roomId = Integer.parseInt(req.getParameter("roomId"));

        Allocation allocation = new Allocation();
        allocation.setStudentId(studentId);
        allocation.setRoomId(roomId);
        allocation.setAssignTime(String.valueOf(System.currentTimeMillis())); // 当前时间戳
        allocation.setStatus("assigned");

        allocationDAO.add(allocation);

        // 更新房间占用情况
        Room room = roomDAO.findById(roomId);
        room.setOccupied(room.getOccupied() + 1);
        roomDAO.update(room);

        // 更新学生宿舍号
        Student student = studentDAO.findById(studentId);
        student.setDormId(roomId);
        studentDAO.update(student);

        resp.sendRedirect(req.getContextPath() + "/allocations");
    }
}
