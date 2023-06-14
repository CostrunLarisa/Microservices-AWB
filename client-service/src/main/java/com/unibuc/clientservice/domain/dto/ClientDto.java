package com.unibuc.clientservice.domain.dto;

import com.unibuc.clientservice.domain.model.Order;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

@Data
public class ClientDto extends RepresentationModel<ClientDto> {
    private String email;
    private String lastName;
    private String firstName;
    private String phoneNumber;
}
