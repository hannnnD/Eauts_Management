package com.eauts.ems.Eauts_management.model;

import jakarta.persistence.*;

@Entity
@Table(name = "courses")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long coursesId; // Đổi tên thành camelCase

    @Column(name = "course_code", unique = true, length = 20, nullable = false)
    private String courseCode; // Đổi từ course_code -> courseCode

    @Column(name = "course_name", nullable = false, length = 100)
    private String courseName; // Đổi từ course_name -> courseName

    @Column(nullable = false)
    private int credits;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(name = "tuition_fee_per_credit", nullable = false)
    private double tuitionFeePerCredit = 500000; // Mặc định 500,000 VND/tín chỉ

    // Getter & Setter
    public double calculateTuitionFee() {
        return this.credits * this.tuitionFeePerCredit;
    }

    public double getTuitionFeePerCredit() {
        return tuitionFeePerCredit;
    }

    public void setTuitionFeePerCredit(double tuitionFeePerCredit) {
        this.tuitionFeePerCredit = tuitionFeePerCredit;
    }

    public Long getCoursesId() {
        return coursesId;
    }

    public void setCoursesId(Long coursesId) {
        this.coursesId = coursesId;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
