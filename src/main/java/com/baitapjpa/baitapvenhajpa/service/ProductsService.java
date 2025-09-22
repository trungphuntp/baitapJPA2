package com.baitapjpa.baitapvenhajpa.service;

import com.baitapjpa.baitapvenhajpa.entity.Products;
import com.baitapjpa.baitapvenhajpa.request.SaveProductRequest;
import com.baitapjpa.baitapvenhajpa.request.UpdateProductsRequest;
import com.baitapjpa.baitapvenhajpa.response.ProductsResponse;

import java.util.List;

public interface ProductsService {
    List<ProductsResponse> getAllProducts();
    Products getProductById(int id);
    Products saveProducts(SaveProductRequest saveProductRequest);
    Products updateProducts(UpdateProductsRequest updateProductsRequest);
    Products deleteProducts(int id);
}
