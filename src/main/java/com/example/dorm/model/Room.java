package com.example.dorm.model;

public class Room {
    private int id;
    private int dormId;
    private String roomNo;
    private int capacity;
    private int occupied;
    private int floor;
    private String remark;

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getDormId() { return dormId; }
    public void setDormId(int dormId) { this.dormId = dormId; }

    public String getRoomNo() { return roomNo; }
    public void setRoomNo(String roomNo) { this.roomNo = roomNo; }

    public int getCapacity() { return capacity; }
    public void setCapacity(int capacity) { this.capacity = capacity; }

    public int getOccupied() { return occupied; }
    public void setOccupied(int occupied) { this.occupied = occupied; }

    public int getFloor() { return floor; }
    public void setFloor(int floor) { this.floor = floor; }

    public String getRemark() { return remark; }
    public void setRemark(String remark) { this.remark = remark; }
}
