package com.baitapjpa.baitapvenhajpa.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SaveEmployeeRequest {
    private String name;
    private Double salary;
    private String department;
}
