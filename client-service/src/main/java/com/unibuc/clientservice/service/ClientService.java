package com.unibuc.clientservice.service;

import com.unibuc.clientservice.domain.model.Client;

import java.util.List;

public interface ClientService {
    Client addNewClient(Client client);

    List<Client> getAllClients();

    Client getByEmail(String email);

    void deleteClientByEmail(String email);

    Client updatePhoneNumber(String clientEmail, String newPhoneNumber);
}
