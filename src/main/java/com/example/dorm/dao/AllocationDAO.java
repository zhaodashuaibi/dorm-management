package com.example.dorm.dao;

import com.example.dorm.model.Allocation;
import com.example.dorm.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;  // 添加 List 和 ArrayList 导入语句

public class AllocationDAO {

    // 获取所有宿舍分配记录
    public List<Allocation> list() {
        List<Allocation> allocations = new ArrayList<>();
        String sql = "SELECT * FROM allocations";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Allocation a = new Allocation();
                a.setId(rs.getInt("id"));
                a.setStudentId(rs.getInt("student_id"));
                a.setRoomId(rs.getInt("room_id"));
                a.setAssignTime(rs.getString("assign_time"));
                a.setStatus(rs.getString("status"));
                a.setRemark(rs.getString("remark"));
                allocations.add(a);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return allocations;
    }

    // 添加宿舍分配记录
    public void add(Allocation allocation) {
        String sql = "INSERT INTO allocations (student_id, room_id, assign_time, status, remark) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, allocation.getStudentId());
            ps.setInt(2, allocation.getRoomId());
            ps.setString(3, allocation.getAssignTime());
            ps.setString(4, allocation.getStatus());
            ps.setString(5, allocation.getRemark());

            ps.executeUpdate();  // 执行插入操作

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
