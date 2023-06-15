package com.unibuc.clientservice.service;

import com.unibuc.clientservice.domain.model.Client;
import com.unibuc.clientservice.exception.ClientAlreadyExistsException;
import com.unibuc.clientservice.exception.ClientNotFoundException;
import com.unibuc.clientservice.repository.ClientRepository;
import com.unibuc.clientservice.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {
    private final ClientRepository clientRepository;

    @Autowired
    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public Client addNewClient(Client client) {
        if (checkIfClientAlreadyExists(client)) {
            throw new ClientAlreadyExistsException(String.format(Constants.CLIENT_ALREADY_EXISTS,
                    client.getEmail(), client.getPhoneNumber()));

        }
        return clientRepository.save(client);
    }

    @Override
    public List<Client> getAllClients() {
        return clientRepository.findAll(Sort.by("email").ascending());
    }

    @Override
    public Client getByEmail(String email) {
        return clientRepository.findByEmail(email)
                .orElseThrow(() -> new ClientNotFoundException(String.format(Constants.CLIENT_NOT_FOUND, email)));
    }

    @Override
    public void deleteClientByEmail(String email) {
        getByEmail(email);
        clientRepository.deleteByEmail(email);
    }

    @Override
    public Client updatePhoneNumber(String clientEmail, String newPhoneNumber) {
        Client existingClient = getByEmail(clientEmail);
        if (checkIfPhoneNumberExists(newPhoneNumber)) {
            throw new ClientAlreadyExistsException(String.format(Constants.PHONENUMBER_ALREADY_EXISTS, newPhoneNumber));
        }

        existingClient.setPhoneNumber(newPhoneNumber);
        return clientRepository.save(existingClient);
    }

    private boolean checkIfPhoneNumberExists(String phoneNumber) {
        return clientRepository.existsByPhoneNumber(phoneNumber).get();
    }

    private boolean checkIfClientAlreadyExists(Client client) {
        return clientRepository.findByEmailOrPhoneNumber(client.getEmail(), client.getPhoneNumber())
                .isPresent();
    }
}
