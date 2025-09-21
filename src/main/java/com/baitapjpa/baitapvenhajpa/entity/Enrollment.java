package com.baitapjpa.baitapvenhajpa.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity(name = "enrollment")
public class Enrollment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "id_course")
    private Courses courses;


    @ManyToOne
    @JoinColumn(name = "id_student")
    private Students students;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCourses(Courses courses) {
        this.courses = courses;
    }

    public void setStudents(Students students) {
        this.students = students;
    }


    public Courses getCourses() {
        return courses;
    }

    public Students getStudents() {
        return students;
    }

}
