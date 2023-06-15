package com.unibuc.orderservice.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponse {
    private String name;
    private Long price;
    private String type;
    private String description;
    private String producer;
    private LocalDate producedDate;
}