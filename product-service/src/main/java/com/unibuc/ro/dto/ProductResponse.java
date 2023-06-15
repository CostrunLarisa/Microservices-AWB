package com.unibuc.ro.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ProductResponse extends RepresentationModel<ProductResponse> {
    private String name;

    private Long price;

    private String type;

    private String description;

    private String producer;

    private LocalDate producedDate;
}
