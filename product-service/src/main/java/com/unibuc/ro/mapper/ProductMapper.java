package com.unibuc.ro.mapper;
import com.unibuc.ro.dto.ProductRequest;
import com.unibuc.ro.dto.ProductResponse;
import com.unibuc.ro.model.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    Product toProduct(ProductRequest productRequest);
    ProductResponse toProductResponse(Product product);
}
