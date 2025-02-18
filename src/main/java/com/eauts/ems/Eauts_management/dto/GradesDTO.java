package com.eauts.ems.Eauts_management.dto;

import com.eauts.ems.Eauts_management.model.Grades;

public class GradesDTO {
    private Long gradesId;
    private Long studentId;
    private String studentName;
    private Long courseId;
    private String courseName;
    private Long scheduleId;
    private float attendance;
    private float midterm;
    private float finalExam;
    private float total;

    // Constructor mặc định để tránh lỗi serialization
    public GradesDTO() {}

    public GradesDTO(Grades grades) {
        this.gradesId = grades.getGrades_id();
        this.studentId = grades.getStudent().getStudent_id();
        this.studentName = grades.getStudent().getFull_name();
        this.courseId = grades.getCourse().getCourses_id();
        this.courseName = grades.getCourse().getCourseName();
        this.scheduleId = grades.getSchedule().getId();
        this.attendance = grades.getAttendance();
        this.midterm = grades.getMidterm();
        this.finalExam = grades.getFinalExam();
        this.total = grades.getTotal();
    }

    // Getters và Setters
    public Long getGradesId() { return gradesId; }
    public void setGradesId(Long gradesId) { this.gradesId = gradesId; }

    public Long getStudentId() { return studentId; }
    public void setStudentId(Long studentId) { this.studentId = studentId; }

    public String getStudentName() { return studentName; }
    public void setStudentName(String studentName) { this.studentName = studentName; }

    public Long getCourseId() { return courseId; }
    public void setCourseId(Long courseId) { this.courseId = courseId; }

    public String getCourseName() { return courseName; }
    public void setCourseName(String courseName) { this.courseName = courseName; }

    public Long getScheduleId() { return scheduleId; }
    public void setScheduleId(Long scheduleId) { this.scheduleId = scheduleId; }

    public float getAttendance() { return attendance; }
    public void setAttendance(float attendance) { this.attendance = attendance; }

    public float getMidterm() { return midterm; }
    public void setMidterm(float midterm) { this.midterm = midterm; }

    public float getFinalExam() { return finalExam; }
    public void setFinalExam(float finalExam) { this.finalExam = finalExam; }

    public float getTotal() { return total; }
    public void setTotal(float total) { this.total = total; }
}