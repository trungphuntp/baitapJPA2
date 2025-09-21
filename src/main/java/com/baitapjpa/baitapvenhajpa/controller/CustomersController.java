package com.baitapjpa.baitapvenhajpa.controller;

import com.baitapjpa.baitapvenhajpa.request.SaveCustomersRequest;
import com.baitapjpa.baitapvenhajpa.response.BaseResponse;
import com.baitapjpa.baitapvenhajpa.response.CustomersResponse;
import com.baitapjpa.baitapvenhajpa.service.CustomersService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomersController {
    @Autowired
    private CustomersService customersService;

    @GetMapping
    public ResponseEntity<?> getAllCustomers(){
        List<CustomersResponse> savedCustomers = customersService.getAllCustomers();
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setData(savedCustomers);
        baseResponse.setMessage("ok");
        baseResponse.setCode(200);
        return ResponseEntity.ok().body(baseResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCustomerById(@PathVariable int id){
        CustomersResponse customers = customersService.getCustomerById(id);
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setData(customers);
        if (customers != null){
            baseResponse.setMessage("ok");
            baseResponse.setCode(200);
        }else{
            baseResponse.setMessage("Customer not found");
            baseResponse.setCode(404);
        }
        return ResponseEntity.ok().body(baseResponse);
    }

    @PostMapping
    public ResponseEntity<?> saveCustomer(@Valid @RequestBody SaveCustomersRequest saveCustomersRequest){
        CustomersResponse customersResponse = customersService.saveCustomers(saveCustomersRequest);
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setData(customersResponse);
        if (customersResponse != null){
            baseResponse.setMessage("ok");
            baseResponse.setCode(200);
        }else{
            baseResponse.setMessage("Customer not found");
            baseResponse.setCode(404);
        }
        return  ResponseEntity.ok().body(baseResponse);
    }
}
