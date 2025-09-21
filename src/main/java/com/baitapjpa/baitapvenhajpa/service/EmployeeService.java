package com.baitapjpa.baitapvenhajpa.service;

import com.baitapjpa.baitapvenhajpa.entity.Employees;
import com.baitapjpa.baitapvenhajpa.repository.EmployeeRepository;
import com.baitapjpa.baitapvenhajpa.request.SaveEmployeeRequest;
import com.baitapjpa.baitapvenhajpa.response.EmployeeResponse;
import com.baitapjpa.baitapvenhajpa.response.PageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    public PageResponse getAllEmployees(Pageable pageable) {

        Page<Employees> employees = employeeRepository.findAll(pageable);
        PageResponse pageResponse = new PageResponse();

        if (!employees.isEmpty()) {
            pageResponse.setContent(employees.getContent());
            pageResponse.setPage(employees.getNumber());
            pageResponse.setSize(employees.getSize());
            pageResponse.setTotalElements(employees.getTotalElements());
            pageResponse.setTotalPages(employees.getTotalPages());
            pageResponse.setLast(employees.isLast());
        }
        return  pageResponse;
    }


    public EmployeeResponse getEmployeeById(int id){
        Employees employees = employeeRepository.findById(id).orElse(null);

        EmployeeResponse employeeResponse = new EmployeeResponse();
        if(employees != null){
            employeeResponse.setId(employees.getId());
            employeeResponse.setName(employees.getName());
            employeeResponse.setSalary(employees.getSalary());
            employeeResponse.setDepartment(employees.getDepartment());
        }else {
            return null;
        }
        return employeeResponse;
    }

    public EmployeeResponse SaveEmployee(SaveEmployeeRequest saveEmployeeRequest){
        Employees employees = new Employees();
        employees.setName(saveEmployeeRequest.getName());
        employees.setSalary(saveEmployeeRequest.getSalary());
        employees.setDepartment(saveEmployeeRequest.getDepartment());

        Employees savedEmployees = employeeRepository.save(employees);

        EmployeeResponse employeeResponse = new EmployeeResponse();
        employeeResponse.setId(savedEmployees.getId());
        employeeResponse.setName(savedEmployees.getName());
        employeeResponse.setSalary(savedEmployees.getSalary());
        employeeResponse.setDepartment(savedEmployees.getDepartment());

        return employeeResponse;
    }
}
