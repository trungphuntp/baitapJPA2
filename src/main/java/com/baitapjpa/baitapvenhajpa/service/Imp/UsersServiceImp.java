package com.baitapjpa.baitapvenhajpa.service.Imp;

import com.baitapjpa.baitapvenhajpa.entity.Users;
import com.baitapjpa.baitapvenhajpa.repository.UsersRepository;
import com.baitapjpa.baitapvenhajpa.request.SaveUsersResponse;
import com.baitapjpa.baitapvenhajpa.response.UsersResponse;
import com.baitapjpa.baitapvenhajpa.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersServiceImp implements UsersService {
    @Autowired
    private UsersRepository usersRepository;

    public List<UsersResponse> getAllUsers() {
        return usersRepository.findAll().stream().map(
                u -> new UsersResponse(u.getId(),u.getUsername(), u.getEmail(), u.getPassword())
        ).toList();
    }

    public UsersResponse getUserById(int id) {
        Users user = usersRepository.findById(id).orElse(null);
        UsersResponse usersResponse = new UsersResponse();
        if (user != null) {
            usersResponse.setId(user.getId());
            usersResponse.setUsername(user.getUsername());
            usersResponse.setEmail(user.getEmail());
            usersResponse.setPassword(user.getPassword());
        } else {
            return null;
        }

        return usersResponse;
    }

    public UsersResponse saveUser(SaveUsersResponse saveUsersResponse) {
        Users user = new Users();
        user.setUsername(saveUsersResponse.getUsername());
        user.setEmail(saveUsersResponse.getEmail());
        user.setPassword(saveUsersResponse.getPassword());

        Users saveUsers = usersRepository.save(user);

        UsersResponse usersResponse = new UsersResponse();
        usersResponse.setId(saveUsers.getId());
        usersResponse.setUsername(saveUsers.getUsername());
        usersResponse.setEmail(saveUsers.getEmail());
        usersResponse.setPassword(saveUsers.getPassword());
        return usersResponse;


    }
}
