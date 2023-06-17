package com.unibuc.orderservice.service;

import com.unibuc.orderservice.exceptions.OrderNotFoundException;
import com.unibuc.orderservice.model.Order;
import com.unibuc.orderservice.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {


    private final OrderRepository orderRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Order addNewOrder(Order orderRequest) {
        Order order = new Order(orderRequest.getClientEmail(),
                orderRequest.getProductName(),orderRequest.getOrderDate(), orderRequest.getQuantity(),
                orderRequest.getTotalPrice());
        orderRepository.save(order);
        return order;
    }

    @Override
    public Order updateById(Long id, Order orderReq) {
        Order order = findById(id);
        orderRepository.save(orderReq);
        return order;
    }

    @Override
    public List<Order> findAll() {
        List<Order> orders = orderRepository.findAll();
        return orders.stream().collect(Collectors.toList());
    }


    @Override
    public List<Order> getByClientEmail(String clientEmail) {
        return orderRepository.findByClientEmail(clientEmail).stream().toList();
    }


    @Override
    public List<Order> getByProductName(String productName) {
        return orderRepository.findByProductName(productName).stream().toList();
    }

    @Override
    public Order findById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException("Order"));

    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }


}
