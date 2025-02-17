package com.eauts.ems.Eauts_management.model;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "schedule")
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long schedule_id;

    @ManyToOne
    @JoinColumn(name = "lecturer_id", nullable = false)
    private Teacher lecturer;

    @ManyToOne
    @JoinColumn(name = "class_id", nullable = false)
    private ClassEntity studentClass;

    @ManyToOne
    @JoinColumn(name = "courses_id", nullable = false)
    private Course course;

    @Column(nullable = false, length = 100)
    private String room;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Shift shift;

    @Column(nullable = false)
    private Date start_date;

    @Column(nullable = false)
    private Date end_date;

    // Getters và Setters

    public Long getSchedule_id() {
        return schedule_id;
    }

    public void setSchedule_id(Long schedule_id) {
        this.schedule_id = schedule_id;
    }

    public Teacher getLecturer() {
        return lecturer;
    }

    public void setLecturer(Teacher lecturer) {
        this.lecturer = lecturer;
    }

    public ClassEntity getStudentClass() {
        return studentClass;
    }

    public void setStudentClass(ClassEntity studentClass) {
        this.studentClass = studentClass;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public Shift getShift() {
        return shift;
    }

    public void setShift(Shift shift) {
        this.shift = shift;
    }

    public Date getStart_date() {
        return start_date;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    public Date getEnd_date() {
        return end_date;
    }

    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
    }
}
