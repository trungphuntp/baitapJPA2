package com.baitapjpa.baitapvenhajpa.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Courses {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    @OneToMany(mappedBy = "courses",  cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Enrollment> enrollments;


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
