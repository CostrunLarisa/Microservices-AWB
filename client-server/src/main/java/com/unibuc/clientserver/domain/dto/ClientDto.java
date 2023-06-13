package com.unibuc.clientserver.domain.dto;

import lombok.Data;

@Data
public class ClientDto {
    private String email;
    private String lastName;
    private String firstName;
    private String phoneNumber;
}
