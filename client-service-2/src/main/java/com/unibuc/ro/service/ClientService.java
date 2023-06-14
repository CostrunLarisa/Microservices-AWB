package com.unibuc.ro.service;

import com.unibuc.ro.domain.model.Client;
import com.unibuc.ro.exception.ClientNotFoundException;
import com.unibuc.ro.exception.ClientAlreadyExistsException;
import com.unibuc.ro.repository.ClientRepository;
import com.unibuc.ro.utils.Constants;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {
    private final ClientRepository clientRepository;
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Client addNewClient(Client client) {
        checkIfClientExists(client);
        return clientRepository.save(client);
    }

    public List<Client> getAllClients() {
        return clientRepository.findAll(Sort.by("email").ascending());
    }

    public Client getByEmail(String email) {
        return clientRepository.findByEmail(email)
                .orElseThrow(() -> new ClientNotFoundException(String.format(Constants.CLIENT_NOT_FOUND, email)));
    }

    public void deleteClientByEmail(String email) {
        clientRepository.deleteByEmail(email);
    }

    public Client updatePhoneNumber(String clientEmail, String newPhoneNumber) {
        Client existingClient = getByEmail(clientEmail);
        checkIfPhoneNumberExists(newPhoneNumber);
        existingClient.setPhoneNumber(newPhoneNumber);
        return clientRepository.save(existingClient);
    }

    private boolean checkIfPhoneNumberExists(String phoneNumber) {
        return clientRepository.existsByPhoneNumber(phoneNumber)
                .orElseThrow(() -> new ClientAlreadyExistsException(String.format(Constants.PHONENUMBER_ALREADY_EXISTS, phoneNumber)));
    }

    private boolean checkIfClientExists(Client client) {
        return clientRepository.existsByEmailOrPhoneNumber(client.getEmail(), client.getPhoneNumber())
                .orElseThrow(() -> new ClientAlreadyExistsException(String.format(Constants.CLIENT_ALREADY_EXISTS,
                        client.getEmail(), client.getPhoneNumber())));
    }
}