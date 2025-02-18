package com.eauts.ems.Eauts_management.dto;

import java.util.Date;

public class ScheduleDTO {
    private Long id;
    private String teacherName;
    private String className;
    private String courseName;
    private String room;
    private String shift;
    private Date startDate;
    private Date endDate;

    public ScheduleDTO(Long id, String teacherName, String className, String courseName,
                       String room, String shift, Date startDate, Date endDate) {
        this.id = id;
        this.teacherName = teacherName;
        this.className = className;
        this.courseName = courseName;
        this.room = room;
        this.shift = shift;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    // Getters & Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getShift() {
        return shift;
    }

    public void setShift(String shift) {
        this.shift = shift;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
