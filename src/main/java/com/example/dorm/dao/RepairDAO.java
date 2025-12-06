package com.example.dorm.dao;

import com.example.dorm.model.Repair;
import com.example.dorm.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class RepairDAO {

    // 获取所有报修记录
    public List<Repair> list() {
        List<Repair> repairs = new ArrayList<>();
        String sql = "SELECT * FROM repairs";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Repair r = new Repair();
                r.setId(rs.getInt("id"));
                r.setStudentId(rs.getInt("student_id"));
                r.setRoomId(rs.getInt("room_id"));
                r.setTitle(rs.getString("title"));
                r.setDescription(rs.getString("description"));
                r.setStatus(rs.getString("status"));
                r.setCreateTime(rs.getString("create_time"));
                r.setProcessTime(rs.getString("process_time"));
                r.setHandler(rs.getString("handler"));
                repairs.add(r);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return repairs;
    }

    // 添加报修记录
    public void add(Repair repair) {
        String sql = "INSERT INTO repairs (student_id, room_id, title, description, status, create_time, process_time, handler) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, repair.getStudentId());
            ps.setInt(2, repair.getRoomId());
            ps.setString(3, repair.getTitle());
            ps.setString(4, repair.getDescription());
            ps.setString(5, repair.getStatus());
            ps.setString(6, repair.getCreateTime());
            ps.setString(7, repair.getProcessTime());
            ps.setString(8, repair.getHandler());

            ps.executeUpdate();  // 执行插入操作

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
