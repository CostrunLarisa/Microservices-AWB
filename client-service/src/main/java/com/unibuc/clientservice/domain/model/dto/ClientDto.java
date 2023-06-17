package com.unibuc.clientservice.domain.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

@Data
public class ClientDto extends RepresentationModel<ClientDto> {
    @NotBlank(message = "Email cannot be null")
    @NotEmpty(message = "Email cannot be empty")
    private String email;

    @NotBlank(message = "LastName cannot be null")
    @NotEmpty(message = "LastName cannot be empty")
    private String lastName;

    @NotBlank(message = "FirstName cannot be null")
    @NotEmpty(message = "FirstName cannot be empty")
    private String firstName;

    @NotBlank(message = "Phone number cannot be null")
    @NotEmpty(message = "Phone number cannot be empty")
    @Pattern(regexp = "^\\d{10}$")
    private String phoneNumber;
}
