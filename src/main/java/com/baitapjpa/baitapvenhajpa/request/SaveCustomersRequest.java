package com.baitapjpa.baitapvenhajpa.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SaveCustomersRequest {
    @NotBlank(message = "name is not empty")
    private String name;
    @NotBlank(message = "email is not empty")
    @Email(message = "email is not valid")
    private String email;
    private String phone;
}
