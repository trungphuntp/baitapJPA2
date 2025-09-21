package com.baitapjpa.baitapvenhajpa.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsersResponse {
    private int id;

    private String username;
    private String email;
    private String password;
}
