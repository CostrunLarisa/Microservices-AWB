package com.unibuc.orderservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NonNull;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDate;
import java.util.ArrayList;


@Entity(name="Orders")
@AllArgsConstructor
@Builder
public class Order extends RepresentationModel<Order> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="order_id")
    private Long orderId;

    @NonNull
    private String clientEmail;

    @NonNull
    private String productName;
    @NonNull
    private LocalDate orderDate;

    @NonNull
    private int quantity;

    @NonNull
    private float totalPrice;

    public Order() {

    }

    public Order(@NonNull String clientEmail, @NonNull String productName, @NonNull LocalDate orderDate, @NonNull int quantity, @NonNull float totalPrice) {
        this.clientEmail = clientEmail;
        this.productName = productName;
        this.orderDate = orderDate;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
    }


    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getClientEmail() {
        return clientEmail;
    }

    public void setClientEmail(String clientEmail) {
        this.clientEmail = clientEmail;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }
}
