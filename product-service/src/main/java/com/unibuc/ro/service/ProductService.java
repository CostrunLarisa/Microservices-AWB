package com.unibuc.ro.service;

import com.unibuc.ro.dto.ProductResponse;
import com.unibuc.ro.model.Product;
import com.unibuc.ro.dto.ProductRequest;

import java.util.List;


public interface ProductService {

    Product findById(Long id);

    ProductResponse save(ProductRequest productRequest);

    void deleteById(Long id);

    ProductResponse updateById(Long id, ProductRequest productRequest);

    List<ProductResponse> findAll();
}
