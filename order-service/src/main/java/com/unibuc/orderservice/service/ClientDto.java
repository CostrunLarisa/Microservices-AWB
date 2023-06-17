package com.unibuc.orderservice.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientDto {

    private String email;
    private String lastName;
    private String firstName;
    private String phoneNumber;

}
