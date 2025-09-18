package com.baitapjpa.baitapvenhajpa.service;

import com.baitapjpa.baitapvenhajpa.entity.Categories;
import com.baitapjpa.baitapvenhajpa.entity.Products;
import com.baitapjpa.baitapvenhajpa.repository.CategoriesRepository;
import com.baitapjpa.baitapvenhajpa.repository.ProductsRepository;
import com.baitapjpa.baitapvenhajpa.request.AddCategoryRequest;
import com.baitapjpa.baitapvenhajpa.request.SaveCategoriesRequest;
import com.baitapjpa.baitapvenhajpa.response.CategoryResponse;
import com.baitapjpa.baitapvenhajpa.response.ProductsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoriesService {
    @Autowired
    private CategoriesRepository categoriesRepository;
    @Autowired
    private ProductsRepository productsRepository;

    public   List<CategoryResponse> getAllCategories() {
        List<CategoryResponse> listCategory = categoriesRepository.findAll().stream()
                .map(p -> new CategoryResponse(p.getId(), p.getName()))
                .toList();
        return listCategory;
    }

    public Categories getCategoryById(int id) {
        Categories categories = categoriesRepository.findById(id).orElse(null);
        return categories;
    }

    public Categories saveCategories(SaveCategoriesRequest saveCategoriesRequest) {
        Categories categories = new Categories();
        categories.setName(saveCategoriesRequest.getName());
        return categoriesRepository.save(categories);
    }

    public ProductsResponse addCategoryForProducts(int idCategory, AddCategoryRequest addCategoryRequest) {
        Products products = productsRepository.findById(addCategoryRequest.getId()).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));
        Categories categories = categoriesRepository.findById(idCategory).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found"));
        products.setCategory(categories);

        Products productsAffterAdd = productsRepository.save(products);

        ProductsResponse productsResponse = new ProductsResponse();
        CategoryResponse categoryResponse = new CategoryResponse();

        productsResponse.setId(productsAffterAdd.getId());
        productsResponse.setName(productsAffterAdd.getName());
        productsResponse.setPrice(productsAffterAdd.getPrice());

        if (productsAffterAdd.getCategory() != null) {
            categoryResponse.setId(productsAffterAdd.getCategory().getId());
            categoryResponse.setName(productsAffterAdd.getCategory().getName());
        } else {
            categoryResponse = null;
        }
        productsResponse.setCategory(categoryResponse);

        return productsResponse;
    }

    public List<ProductsResponse> getAllProductsByIdCategory(int idCategory) {
        Categories categories = categoriesRepository.findById(idCategory).orElseThrow(()->new  ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found"));
        List<Products> ListProductsByCategory = categories.getProducts();

        List<ProductsResponse> ListProductsResponse = ListProductsByCategory.stream().map( p -> {
            CategoryResponse categoryResponse = new CategoryResponse(p.getCategory().getId(), p.getCategory().getName());
            return  new ProductsResponse(p.getId(),p.getName(),p.getPrice(),categoryResponse);
        } ).toList();
        return ListProductsResponse;

    }


}
