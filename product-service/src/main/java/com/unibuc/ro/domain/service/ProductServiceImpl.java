package com.unibuc.ro.domain.service;

import com.unibuc.ro.application.dto.ProductRequest;
import com.unibuc.ro.application.dto.ProductResponse;
import com.unibuc.ro.application.mapper.ProductMapper;
import com.unibuc.ro.common.exception.EntityAlreadyExistsException;
import com.unibuc.ro.common.exception.EntityNotFoundException;
import com.unibuc.ro.domain.model.Product;
import com.unibuc.ro.domain.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public Product findById(Long id) {
        Optional<Product> productFound = productRepository.findById(id);
        return productFound.orElseThrow(() -> new EntityNotFoundException("Product"));
    }

    public ProductResponse save(ProductRequest productRequest) {
        try {
            findByName(productRequest.getName());
            throw new EntityAlreadyExistsException("Product");
        } catch (EntityNotFoundException e) {
            Product product = productMapper.toProduct(productRequest);
            productRepository.save(product);
            return productMapper.toProductResponse(product);
        }
    }

    public Product findByName(String name) {
        Optional<Product> productFound = productRepository.findProductByName(name);
        return productFound.orElseThrow(() -> new EntityNotFoundException("Product"));
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

    @Override
    public List<ProductResponse> findAll() {
        List<Product> products = productRepository.findAll();
        return products.stream().map(product -> productMapper.toProductResponse(product)).collect(Collectors.toList());
    }
}