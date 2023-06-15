package com.unibuc.ro;

import lombok.Data;

@Data
public class ProductRequest {
    private Long price;
    private String type;
    private String description;
    private String name;
}