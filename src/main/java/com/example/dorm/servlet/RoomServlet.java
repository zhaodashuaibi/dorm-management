package com.example.dorm.servlet;

import com.example.dorm.dao.RoomDAO;
import com.example.dorm.model.Room;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/rooms")
public class RoomServlet extends HttpServlet {
    private RoomDAO roomDAO = new RoomDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Room> rooms = roomDAO.list();
        req.setAttribute("rooms", rooms);
        req.getRequestDispatcher("/jsp/admin/room_list.jsp").forward(req, resp);
    }

    // 添加房间功能
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String roomNo = req.getParameter("roomNo");
        int dormId = Integer.parseInt(req.getParameter("dormId"));
        int capacity = Integer.parseInt(req.getParameter("capacity"));
        String remark = req.getParameter("remark");

        Room room = new Room();
        room.setRoomNo(roomNo);
        room.setDormId(dormId);
        room.setCapacity(capacity);
        room.setRemark(remark);

        roomDAO.add(room);
        resp.sendRedirect(req.getContextPath() + "/rooms");
    }
}
