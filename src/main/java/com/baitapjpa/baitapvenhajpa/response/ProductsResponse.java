package com.baitapjpa.baitapvenhajpa.response;

public class ProductsResponse {
    private int id;
    private String name;
    private Double price;
    private CategoryResponse category;

    public ProductsResponse() {}

    public ProductsResponse(int id, String name, Double price, CategoryResponse category) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.category = category;
    }

    public CategoryResponse getCategory() {
        return category;
    }
    public void setCategory(CategoryResponse category) {
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Double getPrice() {
        return price;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
