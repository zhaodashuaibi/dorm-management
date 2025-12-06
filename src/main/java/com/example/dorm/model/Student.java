package com.example.dorm.model;

public class Student {
    private int id;
    private int userId;
    private String studentNo;
    private String name;
    private String gender;
    private String college;
    private String clazz;
    private String phone;
    private Integer dormId;

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public String getStudentNo() { return studentNo; }
    public void setStudentNo(String studentNo) { this.studentNo = studentNo; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }

    public String getCollege() { return college; }
    public void setCollege(String college) { this.college = college; }

    public String getClazz() { return clazz; }
    public void setClazz(String clazz) { this.clazz = clazz; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public Integer getDormId() { return dormId; }
    public void setDormId(Integer dormId) { this.dormId = dormId; }
}
