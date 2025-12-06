package com.example.dorm.dao;

import com.example.dorm.model.Student;
import com.example.dorm.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class StudentDAO {

    // 获取所有学生
    public List<Student> list() {
        List<Student> students = new ArrayList<>();
        String sql = "SELECT * FROM students";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Student s = new Student();
                s.setId(rs.getInt("id"));
                s.setUserId(rs.getInt("user_id"));
                s.setStudentNo(rs.getString("student_no"));
                s.setName(rs.getString("name"));
                s.setGender(rs.getString("gender"));
                s.setCollege(rs.getString("college"));
                s.setClazz(rs.getString("class"));
                s.setPhone(rs.getString("phone"));
                s.setDormId(rs.getInt("dorm_id"));
                students.add(s);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return students;
    }

    // 根据学生ID查找学生
    public Student findById(int studentId) {
        String sql = "SELECT * FROM students WHERE id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, studentId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Student student = new Student();
                student.setId(rs.getInt("id"));
                student.setUserId(rs.getInt("user_id"));
                student.setStudentNo(rs.getString("student_no"));
                student.setName(rs.getString("name"));
                student.setGender(rs.getString("gender"));
                student.setCollege(rs.getString("college"));
                student.setClazz(rs.getString("class"));
                student.setPhone(rs.getString("phone"));
                student.setDormId(rs.getInt("dorm_id"));
                return student;  // 返回学生对象
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;  // 如果没有找到学生，则返回 null
    }

    // 添加学生
    public void add(Student student) {
        String sql = "INSERT INTO students (user_id, student_no, name, gender, college, class, phone, dorm_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, student.getUserId());
            ps.setString(2, student.getStudentNo());
            ps.setString(3, student.getName());
            ps.setString(4, student.getGender());
            ps.setString(5, student.getCollege());
            ps.setString(6, student.getClazz());
            ps.setString(7, student.getPhone());
            ps.setInt(8, student.getDormId());

            ps.executeUpdate();  // 执行插入操作

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 更新学生信息
    public void update(Student student) {
        String sql = "UPDATE students SET user_id = ?, student_no = ?, name = ?, gender = ?, college = ?, class = ?, phone = ?, dorm_id = ? WHERE id = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, student.getUserId());
            ps.setString(2, student.getStudentNo());
            ps.setString(3, student.getName());
            ps.setString(4, student.getGender());
            ps.setString(5, student.getCollege());
            ps.setString(6, student.getClazz());
            ps.setString(7, student.getPhone());
            ps.setInt(8, student.getDormId());
            ps.setInt(9, student.getId());

            ps.executeUpdate();  // 执行更新操作

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
