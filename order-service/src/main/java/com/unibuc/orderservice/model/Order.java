package com.unibuc.orderservice.model;

<<<<<<< HEAD

=======
>>>>>>> main
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
    private Long clientId;

    @NonNull
    private Long productId;
    @NonNull
    private LocalDate orderDate;

    @NonNull
    private int quantity;

    @NonNull
    private float totalPrice;

    public Order() {

    }

    public Order(@NonNull Long clientId, @NonNull Long productId, @NonNull LocalDate orderDate, @NonNull int quantity, @NonNull float totalPrice) {
        this.clientId = clientId;
        this.productId = productId;
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

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
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
