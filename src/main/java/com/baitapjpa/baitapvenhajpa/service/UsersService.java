package com.baitapjpa.baitapvenhajpa.service;

import com.baitapjpa.baitapvenhajpa.request.SaveCustomersRequest;
import com.baitapjpa.baitapvenhajpa.request.SaveUsersResponse;
import com.baitapjpa.baitapvenhajpa.response.CustomersResponse;
import com.baitapjpa.baitapvenhajpa.response.UsersResponse;

import java.util.List;

public interface UsersService {
    List<UsersResponse> getAllUsers();
    UsersResponse getUserById(int id);
    UsersResponse saveUser(SaveUsersResponse saveUsersResponse);
}
