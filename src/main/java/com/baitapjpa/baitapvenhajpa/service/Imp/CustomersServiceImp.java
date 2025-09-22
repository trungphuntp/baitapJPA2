package com.baitapjpa.baitapvenhajpa.service.Imp;

import com.baitapjpa.baitapvenhajpa.entity.Customers;
import com.baitapjpa.baitapvenhajpa.repository.CustomersRepository;
import com.baitapjpa.baitapvenhajpa.request.SaveCustomersRequest;
import com.baitapjpa.baitapvenhajpa.response.CustomersResponse;
import com.baitapjpa.baitapvenhajpa.service.CustomersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomersServiceImp implements CustomersService {
    @Autowired
    private CustomersRepository customersRepository;

    public List<CustomersResponse> getAllCustomers() {
        return customersRepository.findAll().stream().map(c -> new CustomersResponse(c.getId(),c.getName(),c.getPhone())).toList();
    }

    public CustomersResponse getCustomerById(int id) {
        Customers customer = customersRepository.findById(id).orElse(null);
        CustomersResponse customersResponse = new CustomersResponse();
        if(customer != null) {
            customersResponse.setId(customer.getId());
            customersResponse.setName(customer.getName());
            customersResponse.setPhone(customer.getPhone());
        }else{
            return null;
        }
        return customersResponse;
    }

    public CustomersResponse saveCustomers(SaveCustomersRequest saveCustomersRequest) {
        Customers customers = new Customers();
        customers.setName(saveCustomersRequest.getName());
        customers.setPhone(saveCustomersRequest.getPhone());
        customers.setEmail(saveCustomersRequest.getEmail());

        Customers savedCustomers = customersRepository.save(customers);
        CustomersResponse customersResponse = new CustomersResponse();
        if (savedCustomers != null) {
            customersResponse.setId(savedCustomers.getId());
            customersResponse.setName(savedCustomers.getName());
            customersResponse.setPhone(savedCustomers.getPhone());
        }else{
            return null;
        }
        return customersResponse;
    }
}
