package com.unibuc.ro.controller;

import com.unibuc.ro.dto.ProductResponse;
import com.unibuc.ro.dto.ProductRequest;
import com.unibuc.ro.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<ProductResponse> addProduct(@RequestBody ProductRequest product) {
        ProductResponse productResponse = productService.save(product);
        return new ResponseEntity<>(productResponse, HttpStatus.CREATED);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable("id") Long id) {
        productService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> updateProduct(@PathVariable("id") Long id, @RequestBody ProductRequest productRequest) {
        ProductResponse productUpdated = productService.updateById(id, productRequest);
        return new ResponseEntity<>(productUpdated, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> getAll() {
        return new ResponseEntity<>(productService.findAll(), HttpStatus.OK);
    }
}
