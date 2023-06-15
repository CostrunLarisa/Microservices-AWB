package com.unibuc.orderservice;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ProductResponse {
    private String name;
    private Long price;
    private String type;
    private String description;
    private String producer;
    private LocalDate producedDate;
}