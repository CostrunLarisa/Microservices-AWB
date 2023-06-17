package com.unibuc.orderservice.repository;

import com.unibuc.clientservice.domain.model.Client;
import com.unibuc.orderservice.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    Optional<Order> findByOrderDateAndClientId(LocalDate orderdate, Long clientId);

    Optional<Order> findByClientId(Long clientId);

    void deleteByClientId(Long clientId);

    void deleteByOrderId(Long orderId);

    Optional<Boolean> existsByClientId(Long clientId);




}
