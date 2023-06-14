package com.unibuc.ro.service;

import com.unibuc.ro.application.dto.ProductRequest;
import com.unibuc.ro.application.dto.ProductResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@FeignClient("product-client")
public interface FeignProductService {
    @RequestMapping("/products")
    ProductResponse save(ProductRequest product);
    @RequestMapping("/products")
    List<ProductResponse> getAll();
    @RequestMapping("/products/{id}")
    void deleteById(Long id);
    @RequestMapping("/products/{id}")
    ProductResponse updateById(Long id, ProductRequest productRequest);
}
