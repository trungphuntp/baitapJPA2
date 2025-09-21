package com.baitapjpa.baitapvenhajpa.controller;

import com.baitapjpa.baitapvenhajpa.entity.Users;
import com.baitapjpa.baitapvenhajpa.request.SaveUsersResponse;
import com.baitapjpa.baitapvenhajpa.response.BaseResponse;
import com.baitapjpa.baitapvenhajpa.response.UsersResponse;
import com.baitapjpa.baitapvenhajpa.service.UsersService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UsersController {
    @Autowired
    private UsersService usersService;

    @GetMapping
    public ResponseEntity<?> getAllUsers() {
        BaseResponse response = new BaseResponse();
        List<UsersResponse> listUsers = usersService.getAllUsers();
        response.setData(listUsers);
        response.setCode(200);
        response.setMessage("ok");
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable int id) {
        BaseResponse baseResponse = new BaseResponse();
        UsersResponse user = usersService.getUserById(id);
        baseResponse.setData(user);
        if (user != null) {
            baseResponse.setCode(200);
            baseResponse.setMessage("ok");
        } else{
            baseResponse.setCode(400);
            baseResponse.setMessage("user not found");
        }
        return ResponseEntity.ok().body(baseResponse);
    }

    @PostMapping
    public ResponseEntity<?> saveUsers(@Valid @RequestBody SaveUsersResponse saveUsersResponse) {
        BaseResponse baseResponse = new BaseResponse();
        UsersResponse usersResponse = usersService.saveUser(saveUsersResponse);
        baseResponse.setData(usersResponse);
        if (usersResponse != null) {
            baseResponse.setCode(200);
            baseResponse.setMessage("ok");
        }else{
            baseResponse.setCode(400);
            baseResponse.setMessage("user not found");
        }
        return  ResponseEntity.ok().body(baseResponse);
    }
}
