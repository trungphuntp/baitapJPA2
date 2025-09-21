package com.baitapjpa.baitapvenhajpa.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SaveStudentsResponse {
    private int id;
    private String name;
    private String email;

}
