package com.unibuc.ro.mapper;
import com.unibuc.ro.dto.ProductRequest;
import com.unibuc.ro.dto.ProductResponse;
import com.unibuc.ro.model.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductResponse toProductResponse(Product product);
    ProductResponse toProductResponse(ProductRequest product);
    Product toProduct(ProductRequest productRequest);
}
