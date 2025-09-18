package com.baitapjpa.baitapvenhajpa.controller;

import com.baitapjpa.baitapvenhajpa.entity.Categories;
import com.baitapjpa.baitapvenhajpa.entity.Products;
import com.baitapjpa.baitapvenhajpa.request.AddCategoryRequest;
import com.baitapjpa.baitapvenhajpa.request.SaveCategoriesRequest;
import com.baitapjpa.baitapvenhajpa.response.CategoryResponse;
import com.baitapjpa.baitapvenhajpa.response.ProductsResponse;
import com.baitapjpa.baitapvenhajpa.service.CategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoriesController {
    @Autowired
    private CategoriesService categoriesService;

    @GetMapping
    public ResponseEntity<List<CategoryResponse>> getAllCategories(){
        return ResponseEntity.ok(categoriesService.getAllCategories());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Categories> getCategoriesById(@PathVariable int id){
        return ResponseEntity.ok().body(categoriesService.getCategoryById(id));
    }

    @PostMapping
    public ResponseEntity<Categories> saveCategories(@RequestBody SaveCategoriesRequest saveCategoriesRequest){
        return ResponseEntity.ok().body(categoriesService.saveCategories(saveCategoriesRequest));
    }

    @PostMapping("/{id}/products")
    public ResponseEntity<ProductsResponse> addCategoriesForProducts(@PathVariable int id, @RequestBody AddCategoryRequest  addCategoryRequest){
        return ResponseEntity.ok().body(categoriesService.addCategoryForProducts(id, addCategoryRequest));
    }

    @GetMapping("/{id}/products")
    public ResponseEntity<List<ProductsResponse>> getAllProductsByCategory(@PathVariable int id){
        return ResponseEntity.ok().body(categoriesService.getAllProductsByIdCategory(id));
    }
}
