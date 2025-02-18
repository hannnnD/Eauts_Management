package com.eauts.ems.Eauts_management.model;

import jakarta.persistence.*;

@Entity
@Table(name = "grades")
public class Grades {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long grades_id;

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @ManyToOne
    @JoinColumn(name = "courses_id", nullable = false)
    private Courses courses;

    @ManyToOne
    @JoinColumn(name = "schedule_id", nullable = false)
    private Schedule schedule;

    @Column(nullable = false)
    private float attendance;

    @Column(nullable = false)
    private float midterm;

    @Column(nullable = false)
    private float finalExam;

    @Column(nullable = false)
    private float total;

    // Getters và Setters
    public Long getGrades_id() {
        return grades_id;
    }

    public void setGrades_id(Long grades_id) {
        this.grades_id = grades_id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Courses getCourse() {
        return courses;
    }

    public void setCourse(Courses courses) {
        this.courses = courses;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    public float getAttendance() {
        return attendance;
    }

    public void setAttendance(float attendance) {
        this.attendance = attendance;
    }

    public float getMidterm() {
        return midterm;
    }

    public void setMidterm(float midterm) {
        this.midterm = midterm;
    }

    public float getFinalExam() {
        return finalExam;
    }

    public void setFinalExam(float finalExam) {
        this.finalExam = finalExam;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }
}
