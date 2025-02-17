package com.eauts.ems.Eauts_management.model;

import jakarta.persistence.*;

@Entity
@Table(name = "courses")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long courses_id;

    @Column(unique = true, length = 20, nullable = false)
    private String course_code;

    @Column(nullable = false, length = 100)
    private String course_name;

    @Column(nullable = false)
    private int credits;

    @Column(columnDefinition = "TEXT")
    private String description;

    // Getters và Setters

    public Long getCourses_id() {
        return courses_id;
    }

    public void setCourses_id(Long courses_id) {
        this.courses_id = courses_id;
    }

    public String getCourse_code() {
        return course_code;
    }

    public void setCourse_code(String course_code) {
        this.course_code = course_code;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
