package com.unibuc.ro.service;

import com.unibuc.ro.exception.EntityAlreadyExistsException;
import com.unibuc.ro.exception.EntityNotFoundException;
import com.unibuc.ro.model.Product;
import com.unibuc.ro.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public Product findById(Long id) {
        Optional<Product> productFound = productRepository.findById(id);
        return productFound.orElseThrow(() -> new EntityNotFoundException("Product"));
    }

    public void save(Product product) {
        try {
            findById(product.getId());
            throw new EntityAlreadyExistsException("Product");
        } catch (EntityNotFoundException e) {
            productRepository.save(product);
        }
    }

    public void deleteById(Long id) {
        findById(id);
        productRepository.deleteById(id);
    }
}
