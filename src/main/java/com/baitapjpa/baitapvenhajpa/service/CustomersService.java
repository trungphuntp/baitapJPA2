package com.baitapjpa.baitapvenhajpa.service;

import com.baitapjpa.baitapvenhajpa.request.SaveCustomersRequest;
import com.baitapjpa.baitapvenhajpa.response.CustomersResponse;

import java.util.List;

public interface CustomersService {
    List<CustomersResponse> getAllCustomers();
    CustomersResponse getCustomerById(int id);
    CustomersResponse saveCustomers(SaveCustomersRequest saveCustomersRequest);
}
