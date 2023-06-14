package com.unibuc.ro.application.service;

import com.unibuc.ro.application.dto.ProductRequest;
import com.unibuc.ro.application.dto.ProductResponse;
import com.unibuc.ro.domain.model.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
@FeignClient("product-client")
public interface FeignProductService {
    @RequestMapping("/products")
    void save(Product product);
    @RequestMapping("/products/{id}")
    void deleteById(Long id);
    @RequestMapping("/products/{id}")
    ProductResponse updateById(Long id, ProductRequest productRequest);
}
