package com.unibuc.orderservice.service;

import com.unibuc.orderservice.model.Order;

import java.util.List;

public interface OrderService {

    Order addNewOrder(Order order);

    Order updateById(Long id, Order order);

    List<Order> findAll();

    List<Order> getOrderByClientEmail(String clientEmail);

    List<Order> getOrderByProductName(String productName);



    Order findById (Long id);

    List<Order> getAllOrders();


}
