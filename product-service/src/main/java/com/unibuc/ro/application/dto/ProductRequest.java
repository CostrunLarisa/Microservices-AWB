package com.unibuc.ro.application.dto;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class ProductRequest {

    private Long price;

    private String type;

    private String description;

}
