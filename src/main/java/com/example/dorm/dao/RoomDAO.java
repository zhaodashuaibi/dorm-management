package com.example.dorm.dao;

import com.example.dorm.model.Room;
import com.example.dorm.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RoomDAO {

    // 获取所有房间
    public List<Room> list() {
        List<Room> rooms = new ArrayList<>();
        String sql = "SELECT * FROM rooms";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Room r = new Room();
                r.setId(rs.getInt("id"));
                r.setDormId(rs.getInt("dorm_id"));
                r.setRoomNo(rs.getString("room_no"));
                r.setCapacity(rs.getInt("capacity"));
                r.setOccupied(rs.getInt("occupied"));
                r.setFloor(rs.getInt("floor"));
                r.setRemark(rs.getString("remark"));
                rooms.add(r);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return rooms;
    }

    // 根据房间ID查找房间
    public Room findById(int roomId) {
        String sql = "SELECT * FROM rooms WHERE id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, roomId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Room room = new Room();
                room.setId(rs.getInt("id"));
                room.setDormId(rs.getInt("dorm_id"));
                room.setRoomNo(rs.getString("room_no"));
                room.setCapacity(rs.getInt("capacity"));
                room.setOccupied(rs.getInt("occupied"));
                room.setFloor(rs.getInt("floor"));
                room.setRemark(rs.getString("remark"));
                return room;  // 返回房间对象
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;  // 如果没有找到房间，则返回 null
    }

    // 添加房间
    public void add(Room room) {
        String sql = "INSERT INTO rooms (dorm_id, room_no, capacity, occupied, floor, remark) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, room.getDormId());
            ps.setString(2, room.getRoomNo());
            ps.setInt(3, room.getCapacity());
            ps.setInt(4, room.getOccupied());
            ps.setInt(5, room.getFloor());
            ps.setString(6, room.getRemark());

            ps.executeUpdate();  // 执行插入操作

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 更新房间信息
    public void update(Room room) {
        String sql = "UPDATE rooms SET dorm_id = ?, room_no = ?, capacity = ?, occupied = ?, floor = ?, remark = ? WHERE id = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, room.getDormId());
            ps.setString(2, room.getRoomNo());
            ps.setInt(3, room.getCapacity());
            ps.setInt(4, room.getOccupied());
            ps.setInt(5, room.getFloor());
            ps.setString(6, room.getRemark());
            ps.setInt(7, room.getId());

            ps.executeUpdate();  // 执行更新操作

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
