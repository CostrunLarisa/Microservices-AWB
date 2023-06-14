package com.unibuc.ro.domain.service;

import com.unibuc.ro.application.dto.ProductResponse;
import com.unibuc.ro.domain.model.Product;
import com.unibuc.ro.application.dto.ProductRequest;

import java.util.List;


public interface ProductService {

    Product findById(Long id);

    ProductResponse save(ProductRequest productRequest);

    void deleteById(Long id);

    ProductResponse updateById(Long id, ProductRequest productRequest);

    List<ProductResponse> findAll();
}
