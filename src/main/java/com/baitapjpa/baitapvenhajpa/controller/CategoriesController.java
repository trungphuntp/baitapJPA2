package com.baitapjpa.baitapvenhajpa.controller;

import com.baitapjpa.baitapvenhajpa.entity.Categories;
import com.baitapjpa.baitapvenhajpa.entity.Products;
import com.baitapjpa.baitapvenhajpa.request.AddCategoryRequest;
import com.baitapjpa.baitapvenhajpa.request.SaveCategoriesRequest;
import com.baitapjpa.baitapvenhajpa.response.BaseResponse;
import com.baitapjpa.baitapvenhajpa.response.CategoryResponse;
import com.baitapjpa.baitapvenhajpa.response.ProductsResponse;
import com.baitapjpa.baitapvenhajpa.service.CategoriesService;
import com.baitapjpa.baitapvenhajpa.service.Imp.CategoriesServiceImp;
import com.baitapjpa.baitapvenhajpa.service.Imp.ProductsServiceImp;
import com.baitapjpa.baitapvenhajpa.service.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoriesController {
    @Autowired
    private CategoriesService categoriesService;
    @Autowired
    private ProductsService productsService;

    @GetMapping
    public ResponseEntity<?> getAllCategories(){
        List<CategoryResponse> categoryResponseList = categoriesService.getAllCategories();
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setData(categoryResponseList);
        if(categoryResponseList.isEmpty()){
            baseResponse.setCode(404);
            baseResponse.setMessage("Categories Not Found");
        }else {
            baseResponse.setCode(200);
            baseResponse.setMessage("ok");
        }
        return ResponseEntity.ok().body(baseResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCategoriesById(@PathVariable int id){
        Categories categories = categoriesService.getCategoryById(id);
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setData(categories);
        if(categories != null){
            baseResponse.setCode(200);
            baseResponse.setMessage("ok");
        }else{
            baseResponse.setCode(404);
            baseResponse.setMessage("Category Not Found");
        }

        return ResponseEntity.ok().body(baseResponse);
    }

    @PostMapping
    public ResponseEntity<?> saveCategories(@RequestBody SaveCategoriesRequest saveCategoriesRequest){
        CategoryResponse categories = categoriesService.saveCategories(saveCategoriesRequest);
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setData(categories);

        if(categories != null){
            baseResponse.setCode(200);
            baseResponse.setMessage("ok");
        }
        else {
            baseResponse.setCode(404);
            baseResponse.setMessage("Category Not Found");
        }

        return ResponseEntity.ok().body(baseResponse);
    }

    @PostMapping("/{id}/products")
    public ResponseEntity<?> addCategoriesForProducts(@PathVariable int id, @RequestBody AddCategoryRequest  addCategoryRequest){
        ProductsResponse productsResponse = categoriesService.addCategoryForProducts(id, addCategoryRequest);

        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setData(productsResponse);

        Categories categorie = categoriesService.getCategoryById(id);
        if (categorie == null){
            baseResponse.setCode(404);
            baseResponse.setMessage("Category Not Found");
            return ResponseEntity.ok().body(baseResponse);
        }
        Products   products = productsService.getProductById(addCategoryRequest.getId());
        if (products == null){
            baseResponse.setCode(404);
            baseResponse.setMessage("Product Not Found");
            return ResponseEntity.ok().body(baseResponse);
        }

        if (productsResponse != null){
            baseResponse.setCode(200);
            baseResponse.setMessage("ok");
        }else{
            baseResponse.setCode(404);
            baseResponse.setMessage("Product Not Found");
        }
        return ResponseEntity.ok().body(baseResponse);
    }

    @GetMapping("/{id}/products")
    public ResponseEntity<?> getAllProductsByCategory(@PathVariable int id){
        List<ProductsResponse> listProducts = categoriesService.getAllProductsByIdCategory(id);
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setData(listProducts);
        if (listProducts.isEmpty()){
            baseResponse.setCode(404);
            baseResponse.setMessage("Category Not Found");
        }
        else{
            baseResponse.setCode(200);
            baseResponse.setMessage("ok");
        }
        return ResponseEntity.ok().body(baseResponse);
    }
}
