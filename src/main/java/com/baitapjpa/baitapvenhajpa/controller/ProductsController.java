package com.baitapjpa.baitapvenhajpa.controller;

import com.baitapjpa.baitapvenhajpa.entity.Products;
import com.baitapjpa.baitapvenhajpa.request.SaveProductRequest;
import com.baitapjpa.baitapvenhajpa.request.UpdateProductsRequest;
import com.baitapjpa.baitapvenhajpa.response.ProductsResponse;
import com.baitapjpa.baitapvenhajpa.service.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductsController {
    @Autowired
    private ProductsService productsService;

    @GetMapping
    public ResponseEntity<List<ProductsResponse>> getProducts(){
        return ResponseEntity.ok().body(productsService.getAllProducts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProductsById(@PathVariable int id){
        Products products = productsService.getProductById(id);
        if(products!=null){
            return  ResponseEntity.ok().body(productsService.getProductById(id));
        }
        return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(products);
    }

    @PostMapping
    public ResponseEntity<Products> saveProducts(@RequestBody SaveProductRequest  saveProductRequest){
        return  ResponseEntity.ok().body(productsService.saveProducts(saveProductRequest));
    }

    @PutMapping
    public ResponseEntity<Products> updateProducts(@RequestBody UpdateProductsRequest updateProductsRequest){
        return   ResponseEntity.ok().body(productsService.updateProducts(updateProductsRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Products> deleteProducts(@PathVariable int id){
        return   ResponseEntity.ok().body(productsService.deleteProducts(id));
    }


}
