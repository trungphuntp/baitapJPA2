package com.baitapjpa.baitapvenhajpa.controller;

import com.baitapjpa.baitapvenhajpa.request.SaveOrdersRequest;
import com.baitapjpa.baitapvenhajpa.response.BaseResponse;
import com.baitapjpa.baitapvenhajpa.response.OrdersResponse;
import com.baitapjpa.baitapvenhajpa.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrdersController {
    @Autowired
    private OrdersService ordersService;


    @GetMapping
    public ResponseEntity<?> getAllOrders() {
        List<OrdersResponse> ordersResponseList = ordersService.getAllOrders();

        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setMessage("oke");
        baseResponse.setCode(200);
        baseResponse.setData(ordersResponseList);

        return ResponseEntity.ok().body(baseResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOrderById(@PathVariable int id) {
        OrdersResponse ordersResponse = ordersService.getOrderById(id);
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setData(ordersResponse);
        if (ordersResponse != null) {
            baseResponse.setMessage("ok");
            baseResponse.setCode(200);
        }else{
            baseResponse.setMessage("Order Not Found");
            baseResponse.setCode(404);
        }
        return  ResponseEntity.ok().body(baseResponse);
    }

    @GetMapping("/top")
    public ResponseEntity<?> getTop5ByTotalAmount() {
        List<OrdersResponse> ordersResponseList = ordersService.getTop5ByTotalAmount();
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setData(ordersResponseList);
        baseResponse.setMessage("ok");
        baseResponse.setCode(200);
        return ResponseEntity.ok().body(baseResponse);
    }

    @GetMapping(value = "/search",params = {"startDate", "endDate"})
    public ResponseEntity<?> getOrdersBetween(@RequestParam String startDate, @RequestParam String endDate) {
        List<OrdersResponse> ordersResponseList = ordersService.getOrdersBetweenDate(startDate, endDate);
        BaseResponse baseResponse = new BaseResponse();
        if (ordersResponseList != null) {
            baseResponse.setMessage("ok");
            baseResponse.setCode(200);
        } else {
            baseResponse.setMessage("Order Not Found");
            baseResponse.setCode(404);
        }
        baseResponse.setData(ordersResponseList);
        return ResponseEntity.ok().body(baseResponse);

    }


    @PostMapping
    public ResponseEntity<?> saveOrders(@RequestBody SaveOrdersRequest saveOrdersRequest) {
        OrdersResponse ordersResponse = ordersService.saveOrders(saveOrdersRequest);
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setData(ordersResponse);
        if (ordersResponse != null) {
            baseResponse.setMessage("ok");
            baseResponse.setCode(200);
        }else{
            baseResponse.setMessage("Order Not Found");
            baseResponse.setCode(404);
        }
        return  ResponseEntity.ok().body(baseResponse);
    }
}
