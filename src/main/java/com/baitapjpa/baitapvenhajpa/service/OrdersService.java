package com.baitapjpa.baitapvenhajpa.service;

import com.baitapjpa.baitapvenhajpa.request.SaveOrdersRequest;
import com.baitapjpa.baitapvenhajpa.response.OrdersResponse;

import java.util.List;

public interface OrdersService {
    List<OrdersResponse> getAllOrders();
    OrdersResponse getOrderById(int id);
    List<OrdersResponse> getTop5ByTotalAmount();
    List<OrdersResponse> getOrdersBetweenDate(String startDate, String endDate);
    OrdersResponse saveOrders(SaveOrdersRequest saveOrdersRequest);
}
