package com.unibuc.clientservice.domain.dto;

import com.unibuc.clientservice.domain.model.Order;
import lombok.Data;

@Data
public class ClientDto extends RepresentationModel<ClientDto> {
    private String email;
    private String lastName;
    private String firstName;
    private String phoneNumber;
}
