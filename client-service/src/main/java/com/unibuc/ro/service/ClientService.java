package com.unibuc.ro.service;

import com.unibuc.ro.domain.model.Client;

import java.util.List;

public interface ClientService {
    Client addNewClient(Client client);

    List<Client> getAllClients();

    Client getByEmail(String email);

    void deleteClientByEmail(String email);

    Client updatePhoneNumber(String clientEmail, String newPhoneNumber);
}
