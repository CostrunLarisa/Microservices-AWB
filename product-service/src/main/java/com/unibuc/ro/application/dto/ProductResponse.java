package com.unibuc.ro.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ProductResponse {
    private String name;

    private Long price;

    private String type;

    private String description;

    private String producer;

    private LocalDate producedDate;
}
