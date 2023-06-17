package com.unibuc.orderservice.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(value = "clientservice")
public interface ClientServiceProxy {

    @GetMapping("/clients")
    ResponseEntity<List<Client>> getAll();

    @DeleteMapping("/clients/{id}")
    ResponseEntity<String> deleteClient(@PathVariable("id") Long id);

    @PostMapping("/clients")
    ResponseEntity<Client> addClient(@RequestBody ClientDto clientDto);

    @GetMapping("/clients")
    ResponseEntity<Client> getByName(@RequestParam String name);

}
