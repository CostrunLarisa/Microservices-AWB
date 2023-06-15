package com.unibuc.ro.service;

import com.unibuc.ro.dto.ProductResponse;
import com.unibuc.ro.model.Product;
import com.unibuc.ro.dto.ProductRequest;

import java.util.List;


public interface ProductService {

    ProductResponse save(ProductRequest productRequest);

    void deleteByName(String name);

    ProductResponse updateById(Long id, ProductRequest productRequest);

    List<ProductResponse> findAll();

    ProductResponse getByName(String name);
}
