package com.unibuc.ro.service;

import com.unibuc.ro.ProductRequest;
import com.unibuc.ro.ProductResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient("product-service")
public interface ProductServiceProxy {
    @RequestMapping(method = RequestMethod.POST, value = "/products")
    ProductResponse save(ProductRequest product);

    @RequestMapping(method = RequestMethod.GET, value = "/products")
    List<ProductResponse> getAll();

    @RequestMapping(method = RequestMethod.DELETE, value = "/products/{id}")
    void deleteById(Long id);

    @RequestMapping(method = RequestMethod.PUT, value = "/products/{id}")
    ProductResponse updateById(Long id, ProductRequest productRequest);
}
