package com.unibuc.ro.controller;

import com.unibuc.ro.model.Product;
import com.unibuc.ro.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;
    @PostMapping
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        productService.save(product);
        return  new ResponseEntity<>(product, HttpStatus.CREATED);

    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable("id") Long id) {
        productService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
