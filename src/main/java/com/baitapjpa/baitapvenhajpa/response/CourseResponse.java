package com.baitapjpa.baitapvenhajpa.response;

public class CourseResponse {
    private int id;
    private String name;

    public CourseResponse(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public CourseResponse() {

    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}
