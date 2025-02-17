package com.eauts.ems.Eauts_management.model;

import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name = "class")
public class ClassEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long class_id;

    @Column(nullable = false, length = 100)
    private String class_name;

    @Column(nullable = false, length = 100)
    private String major_name;

    @Column(length = 100)
    private String note;

    @OneToMany(mappedBy = "studentClass")
    private Set<Student> students;

    // Getters và Setters

    public Long getClass_id() {
        return class_id;
    }

    public void setClass_id(Long class_id) {
        this.class_id = class_id;
    }

    public String getClass_name() {
        return class_name;
    }

    public void setClass_name(String class_name) {
        this.class_name = class_name;
    }

    public String getMajor_name() {
        return major_name;
    }

    public void setMajor_name(String major_name) {
        this.major_name = major_name;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }
}
