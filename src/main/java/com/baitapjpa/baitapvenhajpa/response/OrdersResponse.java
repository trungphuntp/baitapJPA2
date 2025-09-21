package com.baitapjpa.baitapvenhajpa.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrdersResponse {
    private int id;
    private String customerName;
    private int totalAmount;
    private LocalDate createdAt;
}
