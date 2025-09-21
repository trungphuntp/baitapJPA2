package com.baitapjpa.baitapvenhajpa.controller;

import com.baitapjpa.baitapvenhajpa.entity.Products;
import com.baitapjpa.baitapvenhajpa.request.SaveProductRequest;
import com.baitapjpa.baitapvenhajpa.request.UpdateProductsRequest;
import com.baitapjpa.baitapvenhajpa.response.BaseResponse;
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
    public ResponseEntity<?> getProducts(){
        List<ProductsResponse> listProducts = productsService.getAllProducts();
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setData(listProducts);
        if (listProducts.isEmpty()){
            baseResponse.setCode(404);
            baseResponse.setMessage("No products found");
        } else {
            baseResponse.setCode(200);
            baseResponse.setMessage("ok");
        }
        return ResponseEntity.ok().body(baseResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProductsById(@PathVariable int id){
        Products products = productsService.getProductById(id);
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setData(products);
        if(products!=null){
            baseResponse.setCode(200);
            baseResponse.setMessage("ok");

        } else {
            baseResponse.setCode(404);
            baseResponse.setMessage("Found no product with id " + id);
        }
        return  ResponseEntity.ok().body(baseResponse);
    }

    @PostMapping
    public ResponseEntity<?> saveProducts(@RequestBody SaveProductRequest  saveProductRequest){
        Products products = productsService.saveProducts(saveProductRequest);
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setData(products);

        if (products!=null){
            baseResponse.setCode(200);
            baseResponse.setMessage("ok");
        } else {
            baseResponse.setCode(404);
            baseResponse.setMessage("Found no product");
        }

        return  ResponseEntity.ok().body(baseResponse);
    }

    @PutMapping
    public ResponseEntity<?> updateProducts(@RequestBody UpdateProductsRequest updateProductsRequest){
        Products products = productsService.updateProducts(updateProductsRequest);

        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setData(products);
        if (products!=null){
            baseResponse.setCode(200);
            baseResponse.setMessage("ok");
        } else {
            baseResponse.setCode(404);
            baseResponse.setMessage("Found no product");
        }

        return   ResponseEntity.ok().body(baseResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProducts(@PathVariable int id){
        Products products= productsService.deleteProducts(id);
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setData(products);
        if (products!=null){
            baseResponse.setCode(200);
            baseResponse.setMessage("ok");
        }
        else {
            baseResponse.setCode(404);
            baseResponse.setMessage("Found no product");
        }
        return   ResponseEntity.ok().body(baseResponse);
    }


}
