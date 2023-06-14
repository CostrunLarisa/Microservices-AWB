package com.unibuc.clientservice.controller;

import com.unibuc.clientservice.domain.dto.ClientDto;
import com.unibuc.clientservice.domain.model.Client;
import com.unibuc.clientservice.service.ClientService;
import com.unibuc.clientservice.utils.ClientMapper;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.hateoas.Link;


import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/clients")
@Validated
@Slf4j
public class ClientController {
    private final ClientService clientService;
    private ClientMapper clientMapper = new ClientMapper();

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @Operation(method = "GET", summary = "Get all clients")
    @GetMapping
    public ResponseEntity<List<ClientDto>> getAll() {
        List<Client> clientList = clientService.getAllClients();
        for(Client client :clientList){
            Link selfLink = linkTo(methodOn(ClientController.class).getByEmail(client.getEmail())).withSelfRel();
            client.add(selfLink);
            Link deleteLink = linkTo(methodOn(ClientController.class).deleteClientByEmail(client.getEmail())).withRel("deleteClientByEmail");
            client.add(deleteLink);
        }
        return new ResponseEntity<>(clientService.getAllClients()
                .stream()
                .map(this.clientMapper::entityToDto)
                .collect(Collectors.toList()), HttpStatus.OK);
    }

    @Operation(method = "GET", summary = "Get the client by email")
    @GetMapping("/{email}")
    public ResponseEntity<ClientDto> getByEmail(@PathVariable("email") String email) {
        return new ResponseEntity<>(clientMapper.entityToDto(clientService.getByEmail(email)),
                HttpStatus.OK);
    }

    @Operation(method = "DELETE", summary = "Delete client by email")
    @DeleteMapping("/{email}")
    public ResponseEntity<?> deleteClientByEmail(@PathVariable("email") @NotBlank String email) {
        clientService.deleteClientByEmail(email);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(method = "POST", summary = "Add a new client")
    @PostMapping
    public ResponseEntity<ClientDto> addClient(@RequestBody @Valid ClientDto newClient) {
        Client clientEntity = clientMapper.dtoToEntity(newClient);
        clientService.addNewClient(clientEntity);
        return new ResponseEntity<>(newClient, HttpStatus.CREATED);
    }


    @Operation(method = "PUT", summary = "Update phone number for a client identified by email")
    @PutMapping("/{email}")
    public ResponseEntity<ClientDto> updatePatient(@PathVariable("email") String email,
                                                   @RequestParam @Valid String phoneNumber) {
        Client client = clientService.updatePhoneNumber(email, phoneNumber);
        return new ResponseEntity<>(clientMapper.entityToDto(client), HttpStatus.OK);
    }
}
