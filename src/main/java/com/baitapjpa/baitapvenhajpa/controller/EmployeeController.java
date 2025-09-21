package com.baitapjpa.baitapvenhajpa.controller;

import com.baitapjpa.baitapvenhajpa.request.SaveEmployeeRequest;
import com.baitapjpa.baitapvenhajpa.response.BaseResponse;
import com.baitapjpa.baitapvenhajpa.response.EmployeeResponse;
import com.baitapjpa.baitapvenhajpa.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    public ResponseEntity<?> getAllEmployees(@PageableDefault(size = 5 ) Pageable page){
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setCode(200);
        baseResponse.setMessage("ok");
        baseResponse.setData(employeeService.getAllEmployees(page));
        return  ResponseEntity.ok().body(baseResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getEmployeeById(@PathVariable int id){
        BaseResponse baseResponse = new BaseResponse();
        EmployeeResponse employeeResponse = employeeService.getEmployeeById(id);
        baseResponse.setData(employeeResponse);

        if(employeeResponse == null){
            baseResponse.setCode(400);
            baseResponse.setMessage("not found");
        }else {
            baseResponse.setCode(200);
            baseResponse.setMessage("ok");
        }
        return  ResponseEntity.ok().body(baseResponse);
    }

    @PostMapping
    public ResponseEntity<?> SaveEmployee(@RequestBody SaveEmployeeRequest saveEmployeeRequest){
        BaseResponse baseResponse = new BaseResponse();
        EmployeeResponse employeeResponse = employeeService.SaveEmployee(saveEmployeeRequest);
        baseResponse.setData(employeeResponse);
        if(employeeResponse != null){
            baseResponse.setCode(200);
            baseResponse.setMessage("ok");
        }else {
            baseResponse.setCode(400);
            baseResponse.setMessage("error");
        }
    return ResponseEntity.ok().body(baseResponse);
    }


}
