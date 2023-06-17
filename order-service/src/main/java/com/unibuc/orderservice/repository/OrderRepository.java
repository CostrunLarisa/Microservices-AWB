package com.unibuc.orderservice.repository;

import com.unibuc.clientservice.domain.model.Client;
import com.unibuc.orderservice.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    Optional<Order> findByOrderDateAndClientEmail(LocalDate orderDate, String clientEmail);

    Optional<Order> findByClientEmail(String clientEmail);

    void deleteByClientEmailAndProductName(String clientEmail, String productName);

    void deleteByOrderId(Long orderId);

    Optional<Boolean> existsByClientEmail(String clientEmail);


    Optional<Order> findByProductName(String productName);
}
