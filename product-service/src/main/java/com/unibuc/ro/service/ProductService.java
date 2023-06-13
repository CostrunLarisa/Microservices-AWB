package com.unibuc.ro.service;

import com.unibuc.ro.exception.EntityAlreadyExistsException;
import com.unibuc.ro.model.Product;
import com.unibuc.ro.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductService {
    private  final ProductRepository productRepository;

    public void save(Product product) {
        Optional<Product> productFound = productRepository.findById(product.getId());
        if (productFound.isPresent()) {
            throw new EntityAlreadyExistsException("Product");
        }
        productRepository.save(product);
    }
}
