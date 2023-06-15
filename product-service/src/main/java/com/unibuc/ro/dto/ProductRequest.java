package com.unibuc.ro.dto;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class ProductRequest {

    private Long price;

    private String type;

    private String description;

    private String name;

}
