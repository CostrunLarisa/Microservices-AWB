package com.unibuc.orderservice.service;

import com.unibuc.clientservice.domain.model.Client;
import com.unibuc.clientservice.domain.model.dto.ClientDto;
import jakarta.validation.constraints.NotBlank;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(value = "clientservice")
public interface ClientServiceProxy {

    @GetMapping("/clients")
    ResponseEntity<CollectionModel<Client>> getAll();

    @DeleteMapping("/clients/{id}")
    ResponseEntity<String> deleteClient(@PathVariable("id") Long id);

    @PostMapping("/clients")
    ResponseEntity<ClientDto> addClient(@RequestBody ClientDto clientDto);

    @GetMapping("/clients")
    ResponseEntity<Client> getByEmail(@PathVariable("email") String email);

}
