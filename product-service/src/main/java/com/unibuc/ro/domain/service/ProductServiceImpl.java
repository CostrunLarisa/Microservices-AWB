package com.unibuc.ro.domain.service;

import com.unibuc.ro.application.dto.ProductRequest;
import com.unibuc.ro.application.dto.ProductResponse;
import com.unibuc.ro.application.mapper.ProductMapper;
import com.unibuc.ro.common.exception.EntityAlreadyExistsException;
import com.unibuc.ro.common.exception.EntityNotFoundException;
import com.unibuc.ro.domain.model.Product;
import com.unibuc.ro.domain.repository.ProductRepository;
import com.unibuc.ro.domain.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

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

    public ProductResponse updateById(Long id, ProductRequest productRequest) {
        Product product = findById(id);
        productRepository.save(productMapper.toProduct(productRequest));
        return productMapper.toProductResponse(product);
    }
}