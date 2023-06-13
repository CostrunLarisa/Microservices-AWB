package com.unibuc.ro.application.mapper;
import com.unibuc.ro.application.dto.ProductRequest;
import com.unibuc.ro.domain.model.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    Product toProduct(ProductRequest productRequest);
}
