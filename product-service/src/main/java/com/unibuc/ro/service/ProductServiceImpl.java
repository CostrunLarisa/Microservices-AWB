package com.unibuc.ro.service;

import com.unibuc.ro.exceptions.EntityAlreadyExistsException;
import com.unibuc.ro.mapper.ProductMapper;
import com.unibuc.ro.model.Product;
import com.unibuc.ro.repository.ProductRepository;
import com.unibuc.ro.dto.ProductRequest;
import com.unibuc.ro.dto.ProductResponse;
import com.unibuc.ro.exceptions.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    private Product findById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product"));
    }

    public ProductResponse save(ProductRequest productRequest) {
        if (productWithNameAlreadyExists(productRequest.getName())) {
            throw new EntityAlreadyExistsException("Product");
        }

        Product product = new Product(productRequest.getPrice(),
                productRequest.getName(), productRequest.getType(),
                productRequest.getProducer(), productRequest.getDate());
        productRepository.save(product);
        return productMapper.toProductResponse(productRequest);
    }

    public Product findByName(String name) {
        return productRepository.findByName(name)
                .orElseThrow(() -> new EntityNotFoundException("Product"));
    }

    private boolean productWithNameAlreadyExists(String name) {
        return productRepository.findByName(name).isPresent();
    }

    public void deleteByName(String name) {
        findByName(name);
        productRepository.deleteByName(name);
    }

    public ProductResponse updateById(Long id, ProductRequest productRequest) {
        Product product = findById(id);
        productRepository.save(productMapper.toProduct(productRequest));
        return productMapper.toProductResponse(product);
    }

    @Override
    public List<ProductResponse> findAll() {
        List<Product> products = productRepository.findAll();
        return products.stream()
                .map(product -> productMapper.toProductResponse(product))
                .collect(Collectors.toList());
    }

    @Override
    public ProductResponse getByName(String name) {

        return productMapper.toProductResponse(findByName(name));
    }
}