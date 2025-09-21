package com.baitapjpa.baitapvenhajpa.service;

import com.baitapjpa.baitapvenhajpa.entity.Categories;
import com.baitapjpa.baitapvenhajpa.entity.Products;
import com.baitapjpa.baitapvenhajpa.repository.CategoriesRepository;
import com.baitapjpa.baitapvenhajpa.repository.ProductsRepository;
import com.baitapjpa.baitapvenhajpa.request.SaveProductRequest;
import com.baitapjpa.baitapvenhajpa.request.UpdateProductsRequest;
import com.baitapjpa.baitapvenhajpa.response.CategoryResponse;
import com.baitapjpa.baitapvenhajpa.response.ProductsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductsService {
    @Autowired
    private ProductsRepository productsRepository;

    @Autowired
    private CategoriesRepository  categoriesRepository;

    public List<ProductsResponse> getAllProducts(){
        List<Products> productsList = productsRepository.findAll();

        List<ProductsResponse> listProductsResponse = new ArrayList<>();
        for(Products products: productsList){
            ProductsResponse productsResponse = new ProductsResponse();

            CategoryResponse  categoryResponse = new CategoryResponse();
            if (products.getCategory() != null) {
                categoryResponse.setId(products.getCategory().getId());
                categoryResponse.setName(products.getCategory().getName());
            } else {
                categoryResponse = null;
            }

            productsResponse.setId(products.getId());
            productsResponse.setName(products.getName());
            productsResponse.setPrice(products.getPrice());
            productsResponse.setCategory(categoryResponse);

            listProductsResponse.add(productsResponse);
        }

        return listProductsResponse;
    }

    public Products getProductById(int id){
        Optional<Products> products = productsRepository.findById(id);
        return products.orElse(null);
    }

    public Products saveProducts(SaveProductRequest saveProductRequest){
        Products products = new Products();
        products.setName(saveProductRequest.getName());
        products.setPrice(saveProductRequest.getPrice());
        products.setDescription(saveProductRequest.getDescription());

        Categories  categories = categoriesRepository.findById(saveProductRequest
                .getId_category()).orElse(null);

        products.setCategory(categories);
        return productsRepository.save(products);
    }

    public Products updateProducts(UpdateProductsRequest  updateProductsRequest){
        Products products = getProductById(updateProductsRequest.getId());
        if(products!=null){
            products.setName(updateProductsRequest.getName());
            products.setPrice(updateProductsRequest.getPrice());
            products.setDescription(updateProductsRequest.getDescription());
            products =  productsRepository.save(products);
        }
        return  products;
    }

    public Products deleteProducts(int id){
        Products products = getProductById(id);
        if(products!=null){
            productsRepository.deleteById(id);
        }
        return  products;
    }
}
