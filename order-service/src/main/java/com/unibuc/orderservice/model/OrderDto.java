package com.unibuc.orderservice.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

@Data
public class OrderDto extends RepresentationModel<OrderDto> {

    @NotBlank(message = "Email cannot be null")
    @NotEmpty(message = "Email cannot be empty")
    private String clientEmail;

    @NotBlank(message = "ProductName cannot be null")
    @NotEmpty(message = "ProductName cannot be empty")
    private String ProductName;

    @NotBlank(message = "Quantity cannot be null")
    @NotEmpty(message = "Quantity cannot be empty")
    private int quantity;


}
