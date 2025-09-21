package com.baitapjpa.baitapvenhajpa.response;

import com.baitapjpa.baitapvenhajpa.entity.Courses;

import java.util.List;

public class EntrollCoursesByStudentsRequest {
    private int id;
    private String name;
    private String email;
    private List<CourseResponse> listCourses;

    public EntrollCoursesByStudentsRequest() {}

    public EntrollCoursesByStudentsRequest(int id, String name, String email, List<CourseResponse> listCourses) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.listCourses = listCourses;
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

    public void setListCourses(List<CourseResponse> listCourses) {
        this.listCourses = listCourses;
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

    public List<CourseResponse> getListCourses() {
        return listCourses;
    }
}
