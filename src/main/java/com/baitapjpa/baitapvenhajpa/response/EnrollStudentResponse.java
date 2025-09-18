package com.baitapjpa.baitapvenhajpa.response;

import com.baitapjpa.baitapvenhajpa.entity.Courses;

public class EnrollStudentResponse {
    private int id;
    private String name;
    private String email;
    private Courses courses;

    public EnrollStudentResponse() {

    }

    public EnrollStudentResponse(int id, String name, String email, Courses courses) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.courses = courses;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public Courses getCourses() {
        return courses;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCourses(Courses courses) {
        this.courses = courses;
    }
}
