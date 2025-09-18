package com.baitapjpa.baitapvenhajpa.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
public class Products {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private int  id;
    private String name;
    private double price;
    private String description;

    @ManyToOne
    @JoinColumn(name = "id_category")
    private Categories category;


    public Categories getCategory() {
        return category;
    }

    public void setCategory(Categories category) {
        this.category = category;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }
}
