package com.eauts.ems.Eauts_management.dto;

public class StudentDTO {
    private Long student_id;
    private String full_name;
    private String date_of_birth;
    private String gender;
    private String address;
    private String phone;
    private int enrollment_year;
    private String status;

    // Chỉ hiển thị user_id & username
    private Long user_id;
    private String username;

    // Chỉ hiển thị class_id, major_name, class_name
    private Long class_id;
    private String major_name;
    private String class_name;

    // Constructor
    public StudentDTO(Long student_id, String full_name, String date_of_birth, String gender, String address, String phone,
                      int enrollment_year, String status, Long user_id, String username, Long class_id, String major_name, String class_name) {
        this.student_id = student_id;
        this.full_name = full_name;
        this.date_of_birth = date_of_birth;
        this.gender = gender;
        this.address = address;
        this.phone = phone;
        this.enrollment_year = enrollment_year;
        this.status = status;
        this.user_id = user_id;
        this.username = username;
        this.class_id = class_id;
        this.major_name = major_name;
        this.class_name = class_name;
    }

    // Getters và Setters
    public Long getStudent_id() { return student_id; }
    public void setStudent_id(Long student_id) { this.student_id = student_id; }

    public String getFull_name() { return full_name; }
    public void setFull_name(String full_name) { this.full_name = full_name; }

    public String getDate_of_birth() { return date_of_birth; }
    public void setDate_of_birth(String date_of_birth) { this.date_of_birth = date_of_birth; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public int getEnrollment_year() { return enrollment_year; }
    public void setEnrollment_year(int enrollment_year) { this.enrollment_year = enrollment_year; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public Long getUser_id() { return user_id; }
    public void setUser_id(Long user_id) { this.user_id = user_id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public Long getClass_id() { return class_id; }
    public void setClass_id(Long class_id) { this.class_id = class_id; }

    public String getMajor_name() { return major_name; }
    public void setMajor_name(String major_name) { this.major_name = major_name; }

    public String getClass_name() { return class_name; }
    public void setClass_name(String class_name) { this.class_name = class_name; }
}
