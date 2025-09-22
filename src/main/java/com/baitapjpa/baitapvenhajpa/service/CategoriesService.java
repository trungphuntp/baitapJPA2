package com.baitapjpa.baitapvenhajpa.service;

import com.baitapjpa.baitapvenhajpa.entity.Categories;
import com.baitapjpa.baitapvenhajpa.request.AddCategoryRequest;
import com.baitapjpa.baitapvenhajpa.request.SaveCategoriesRequest;
import com.baitapjpa.baitapvenhajpa.response.CategoryResponse;
import com.baitapjpa.baitapvenhajpa.response.ProductsResponse;

import java.util.List;

public interface CategoriesService {
    List<CategoryResponse> getAllCategories();
    Categories getCategoryById(int id);
    CategoryResponse saveCategories(SaveCategoriesRequest saveCategoriesRequest);
    ProductsResponse addCategoryForProducts(int idCategory, AddCategoryRequest addCategoryRequest);
    List<ProductsResponse> getAllProductsByIdCategory(int idCategory);
}
