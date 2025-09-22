package com.baitapjpa.baitapvenhajpa.service;

import com.baitapjpa.baitapvenhajpa.request.SaveEmployeeRequest;
import com.baitapjpa.baitapvenhajpa.response.EmployeeResponse;
import com.baitapjpa.baitapvenhajpa.response.PageResponse;
import org.springframework.data.domain.Pageable;

public interface EmployeeService {
    PageResponse getAllEmployees(Pageable pageable);
    EmployeeResponse getEmployeeById(int id);
    EmployeeResponse SaveEmployee(SaveEmployeeRequest saveEmployeeRequest);
}
