package com.baitapjpa.baitapvenhajpa.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class EmployeeResponse {
    private Long id;

    private String name;
    private Double salary;
    private String department;
}
