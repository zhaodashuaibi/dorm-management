package com.example.dorm.dao;

import com.example.dorm.model.Dormitory;
import com.example.dorm.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;  // 添加 List 和 ArrayList 导入语句

public class DormitoryDAO {

    // 获取所有宿舍楼
    public List<Dormitory> list() {
        List<Dormitory> dormitories = new ArrayList<>();
        String sql = "SELECT * FROM dormitories";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Dormitory d = new Dormitory();
                d.setId(rs.getInt("id"));
                d.setName(rs.getString("name"));
                d.setAddress(rs.getString("address"));
                dormitories.add(d);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return dormitories;
    }

    // 添加宿舍楼
    public void add(Dormitory dormitory) {
        String sql = "INSERT INTO dormitories (name, address) VALUES (?, ?)";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, dormitory.getName());
            ps.setString(2, dormitory.getAddress());

            ps.executeUpdate();  // 执行插入操作

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
