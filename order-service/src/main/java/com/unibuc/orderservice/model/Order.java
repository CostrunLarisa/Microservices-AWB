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
    private Long clientId;

    @NonNull
    private LocalDate orderDate;

    @OneToMany(mappedBy="order")
    private ArrayList<OrderProduct> orderProductList;

    public Order() {

    }

    public Long getOrderId() {
        return orderId;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
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

    public ArrayList<OrderProduct> getOrderProductList() {
        return orderProductList;
    }

    public void setOrderProductList(ArrayList<OrderProduct> orderProductList) {
        this.orderProductList = orderProductList;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", clientId=" + clientId +
                ", orderProductList=" + orderProductList +
                '}';
    }

    public Order(@NonNull Long clientId, @NonNull LocalDate orderDate, ArrayList<OrderProduct> orderProductList) {
        this.clientId = clientId;
        this.orderDate = orderDate;
        this.orderProductList = orderProductList;
    }
}
