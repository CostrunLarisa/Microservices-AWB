package com.unibuc.clientservice.controller;

import com.unibuc.clientservice.domain.model.dto.ClientDto;
import com.unibuc.clientservice.domain.model.Client;
import com.unibuc.clientservice.service.ClientService;
import com.unibuc.clientservice.utils.ClientMapper;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.hateoas.Link;


import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/clients")
@Validated
public class ClientController {

    private final ClientService clientService;
    private ClientMapper clientMapper = new ClientMapper();
    @Value("${msg:Config Server is not working. Please check...}")
    private String msg;

    private Logger LOGGER = LoggerFactory.getLogger(ClientController.class);
    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @Operation(method = "GET", summary = "Get all existing clients - list sorted by name")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Clients found")})
    @GetMapping
    public ResponseEntity<CollectionModel<ClientDto>> getAll() {
        Link getSelfLink = linkTo(methodOn(ClientController.class).getAll()).withSelfRel();
        LOGGER.info(msg);
        List<ClientDto> clientDtoList = clientService.getAllClients()
                .stream()
                .map(client -> {
                    ClientDto dto = clientMapper.entityToDto(client);
                    Link selfLink = linkTo(methodOn(ClientController.class).getByEmail(client.getEmail())).withSelfRel();
                    dto.add(selfLink);
                    return dto;
                }).collect(Collectors.toList());
        CollectionModel<ClientDto> response = CollectionModel.of(clientDtoList, getSelfLink);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(method = "GET", summary = "Get the client by email")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Client with given email found",
                    content = {
                            @Content(mediaType = "application/hal+json",
                                    schema = @Schema(implementation = ClientDto.class))
                    }
            ),
            @ApiResponse(responseCode = "404", description = "Client with given email not found", content = @Content)})
    @GetMapping("/{email}")
    public ResponseEntity<ClientDto> getByEmail(@PathVariable("email") String email) {
        ClientDto dto = clientMapper.entityToDto(clientService.getByEmail(email));
        Link selfLink = linkTo(methodOn(ClientController.class).getByEmail(email)).withSelfRel();
        dto.add(selfLink);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(method = "DELETE", summary = "Delete client by email")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Client with given email deleted", content = @Content),
            @ApiResponse(responseCode = "404", description = "Client with given email not found", content = @Content)})
    @DeleteMapping("/{email}")
    public ResponseEntity<?> deleteClientByEmail(@PathVariable("email") @NotBlank String email) {
        clientService.deleteClientByEmail(email);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(method = "POST", summary = "Add a new client")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "New client added",
                    content = {@Content(mediaType = "application/hal+json",
                            schema = @Schema(implementation = ClientDto.class))
                    }
            ),
            @ApiResponse(responseCode = "400",
                    description = "Client with given email already exists",
                    content = @Content
            )
    })
    @PostMapping
    public ResponseEntity<ClientDto> addClient(@Valid @RequestBody ClientDto newClient) {
        Client clientEntity = clientMapper.dtoToEntity(newClient);
        clientService.addNewClient(clientEntity);

        Link getByEmailLink = linkTo(methodOn(ClientController.class).getByEmail(newClient.getEmail()))
                .withRel("getByEmail");
        newClient.add(getByEmailLink);
        return new ResponseEntity<>(newClient, HttpStatus.CREATED);
    }


    @Operation(method = "PUT", summary = "Update phone number for a client identified by email")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Phone number for client updated",
                    content = {@Content(
                            mediaType = "application/hal+json",
                            schema = @Schema(implementation = ClientDto.class))
                    }
            ),
            @ApiResponse(responseCode = "404",
                    description = "Client with given email not found",
                    content = @Content),
            @ApiResponse(responseCode = "400",
                    description = "Client with given phone number already exists",
                    content = @Content)
    })
    @PutMapping("/{email}")
    public ResponseEntity<ClientDto> updateClientPhoneNumber(@PathVariable("email") String email,
                                                             @RequestParam() String phoneNumber) {
        Client client = clientService.updatePhoneNumber(email, phoneNumber);
        ClientDto dto = clientMapper.entityToDto(client);
        Link getByEmailLink = linkTo(methodOn(ClientController.class).getByEmail(email))
                .withRel("getByEmail");
        dto.add(getByEmailLink);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
}
