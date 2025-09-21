package com.baitapjpa.baitapvenhajpa.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SaveUsersResponse {
    @NotBlank(message = "username must not be empty")
    private String username;

    @NotBlank(message = "email must not be empty")
    @Email(message = "email is invalid")
    private String email;

    @Size(min = 6)
    private String password;
}
