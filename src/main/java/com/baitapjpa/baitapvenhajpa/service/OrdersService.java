package com.baitapjpa.baitapvenhajpa.service;

import com.baitapjpa.baitapvenhajpa.entity.Orders;
import com.baitapjpa.baitapvenhajpa.repository.OrderRepository;
import com.baitapjpa.baitapvenhajpa.request.SaveOrdersRequest;
import com.baitapjpa.baitapvenhajpa.response.OrdersResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

@Service
public class OrdersService {
    @Autowired
    private OrderRepository orderRepository;


    public List<OrdersResponse> getAllOrders() {
        return orderRepository.findAll().stream().map(
                o -> new OrdersResponse(o.getId(),o.getCustomerName(),o.getTotalAmount(),o.getCreatedAt())
        ).toList();
    }

    public OrdersResponse getOrderById(int id) {
        Orders orders = orderRepository.findById(id).orElse(null);

        OrdersResponse ordersResponse = new OrdersResponse();
        if (orders != null) {
            ordersResponse.setId(orders.getId());
            ordersResponse.setCustomerName(orders.getCustomerName());
            ordersResponse.setTotalAmount(orders.getTotalAmount());
            ordersResponse.setCreatedAt(orders.getCreatedAt());
        }else {
            return null;
        }
        return ordersResponse;
    }

    public List<OrdersResponse> getTop5ByTotalAmount() {
        return orderRepository.findTop5ByTotalAmount().stream().map(
                o -> new OrdersResponse(o.getId(),o.getCustomerName(),o.getTotalAmount(),o.getCreatedAt())
        ).toList();
    }

    public List<OrdersResponse> getOrdersBetweenDate(String startDate, String endDate) {
        LocalDate startLocalDate = null;
        LocalDate endLocalDate = null;
        try {
             startLocalDate = LocalDate.parse(startDate);
             endLocalDate = LocalDate.parse(endDate);
        } catch(DateTimeParseException e) {
            return null;
        }
       return orderRepository.findOrdersBetweenDates(startLocalDate,endLocalDate).stream().map(
                o -> new OrdersResponse(o.getId(),o.getCustomerName(),o.getTotalAmount(),o.getCreatedAt())
        ).toList();
    }

    public OrdersResponse saveOrders(SaveOrdersRequest  saveOrdersRequest) {
        Orders orders = new Orders();
        orders.setCustomerName(saveOrdersRequest.getCustomerName());
        orders.setTotalAmount(saveOrdersRequest.getTotalAmount());
        orders.setCreatedAt(LocalDate.now());

        Orders savedOrders = orderRepository.save(orders);
        OrdersResponse ordersResponse = new OrdersResponse();
        if (savedOrders != null) {
            ordersResponse.setId(savedOrders.getId());
            ordersResponse.setCustomerName(savedOrders.getCustomerName());
            ordersResponse.setTotalAmount(savedOrders.getTotalAmount());
            ordersResponse.setCreatedAt(savedOrders.getCreatedAt());
        } else {
            return null;
        }
        return ordersResponse;
    }


}
