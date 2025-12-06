package com.example.dorm.model;

public class Allocation {
    private int id;
    private int studentId;
    private int roomId;
    private String assignTime;
    private String status;
    private String remark;

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getStudentId() { return studentId; }
    public void setStudentId(int studentId) { this.studentId = studentId; }

    public int getRoomId() { return roomId; }
    public void setRoomId(int roomId) { this.roomId = roomId; }

    public String getAssignTime() { return assignTime; }
    public void setAssignTime(String assignTime) { this.assignTime = assignTime; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getRemark() { return remark; }
    public void setRemark(String remark) { this.remark = remark; }
}
