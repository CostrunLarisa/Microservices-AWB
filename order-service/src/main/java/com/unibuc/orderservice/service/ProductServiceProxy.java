package com.unibuc.orderservice.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient("productservice")
public interface ProductServiceProxy {
    @GetMapping("/products")
    ResponseEntity<List<ProductResponse>> getAll();

    @DeleteMapping("/products/{id}")
    ResponseEntity<String> deleteProduct(@PathVariable("id") Long id);

    @PostMapping("/products")
    ResponseEntity<ProductResponse> addProduct(@RequestBody ProductRequest product);

}