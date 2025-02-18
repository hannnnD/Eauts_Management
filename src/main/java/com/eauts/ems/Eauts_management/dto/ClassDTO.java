package com.eauts.ems.Eauts_management.dto;

public class ClassDTO {
    private Long id;
    private String className;
    private String majorName;
    private String note;

    public ClassDTO(Long id, String className, String majorName, String note) {
        this.id = id;
        this.className = className;
        this.majorName = majorName;
        this.note = note;
    }

    // Getters & Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMajorName() {
        return majorName;
    }

    public void setMajorName(String majorName) {
        this.majorName = majorName;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
