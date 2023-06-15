package com.unibuc.orderservice.controller;

import com.netflix.discovery.converters.Auto;
import com.unibuc.orderservice.service.ClientServiceProxy;
import com.unibuc.orderservice.service.OrderService;
import com.unibuc.orderservice.service.ProductServiceProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController("/orders")
public class OrderController {
    private final ClientServiceProxy clientServiceProxy;
    private final ProductServiceProxy productServiceProxy;
    private final OrderService orderService;

    @Autowired
    public OrderController(ClientServiceProxy clientServiceProxy, ProductServiceProxy productServiceProxy, OrderService orderService) {
        this.clientServiceProxy = clientServiceProxy;
        this.productServiceProxy = productServiceProxy;
        this.orderService = orderService;
    }
}
