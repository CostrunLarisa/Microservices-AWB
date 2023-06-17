package com.unibuc.orderservice.service;

import com.unibuc.clientservice.domain.model.dto.ClientDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(value = "clientservice")
public interface ClientServiceProxy {

    @GetMapping("/clients")
    ResponseEntity<CollectionModel<ClientDto>> getAll();

    @DeleteMapping("/clients/{id}")
    ResponseEntity<String> deleteClient(@PathVariable("id") Long id);

    @PostMapping("/clients")
    ResponseEntity<Client> addClient(@RequestBody ClientDto clientDto);

    @GetMapping("/clients/{email}")
    ResponseEntity<ClientDto> getByEmail(@PathVariable("email") String email);

}
