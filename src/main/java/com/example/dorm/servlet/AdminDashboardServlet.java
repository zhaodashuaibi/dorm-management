package com.example.dorm.servlet;

import com.example.dorm.dao.RoomDAO;
import com.example.dorm.dao.RepairDAO;
import com.example.dorm.model.Room;
import com.example.dorm.model.Repair;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/admin/dashboard")
public class AdminDashboardServlet extends HttpServlet {
    private RoomDAO roomDAO = new RoomDAO();
    private RepairDAO repairDAO = new RepairDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 统计入住情况
        List<Room> rooms = roomDAO.list();
        int totalRooms = rooms.size();
        int occupiedRooms = (int) rooms.stream().filter(r -> r.getOccupied() > 0).count();
        int freeRooms = totalRooms - occupiedRooms;

        // 统计待处理报修
        List<Repair> repairs = repairDAO.list();
        int pendingRepairs = (int) repairs.stream().filter(r -> "pending".equals(r.getStatus())).count();

        req.setAttribute("totalRooms", totalRooms);
        req.setAttribute("occupiedRooms", occupiedRooms);
        req.setAttribute("freeRooms", freeRooms);
        req.setAttribute("pendingRepairs", pendingRepairs);

        req.getRequestDispatcher("/jsp/admin/dashboard.jsp").forward(req, resp);
    }
}
