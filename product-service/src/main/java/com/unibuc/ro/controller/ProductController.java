package com.unibuc.ro.controller;

import com.unibuc.ro.service.ProductService;
import com.unibuc.ro.dto.ProductResponse;
import com.unibuc.ro.dto.ProductRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @Operation(method = "POST", summary = "Add a new product")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "New product added"),
            @ApiResponse(responseCode = "400", description = "Product with given name already exists")
    })
    @PostMapping
    public ResponseEntity<ProductResponse> addProduct(@RequestBody ProductRequest product) {
        ProductResponse productResponse = productService.save(product);

        Link getAllProducts = linkTo(methodOn(ProductController.class).getAll())
                .withRel("getAllProducts");
        productResponse.add(getAllProducts);

        Link getByNameLink = linkTo(methodOn(ProductController.class).getByName(product.getName()))
                .withRel("getProductByName");
        productResponse.add(getByNameLink);

        return new ResponseEntity<>(productResponse, HttpStatus.CREATED);
    }

    @Operation(method = "DELETE", summary = "Delete product by name")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Product with given name deleted", content = @Content),
            @ApiResponse(responseCode = "404", description = "Product with given name not found", content = @Content)})
    @DeleteMapping("/{name}")
    public ResponseEntity<String> deleteProduct(@PathVariable("name") String name) {
        productService.deleteByName(name);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(method = "PUT", summary = "Update info for a product identified by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Product info updated",
                    content = {@Content(
                            mediaType = "application/hal+json",
                            schema = @Schema(implementation = ProductResponse.class))
                    }
            ),
            @ApiResponse(responseCode = "404",
                    description = "Product with given id not found",
                    content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> updateProduct(@PathVariable("id") Long id, @RequestBody ProductRequest productRequest) {
        ProductResponse productUpdated = productService.updateById(id, productRequest);
        Link getByProductName = linkTo(methodOn(ProductController.class).getByName(productUpdated.getName()))
                .withRel("getByProductName");
        productUpdated.add(getByProductName);
        return new ResponseEntity<>(productUpdated, HttpStatus.OK);
    }

    @Operation(method = "GET", summary = "Get all existing products")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "All existing products")})
    @GetMapping
    public ResponseEntity<CollectionModel<ProductResponse>> getAll() {
        Link getSelfLink = linkTo(methodOn(ProductController.class).getAll()).withSelfRel();

        List<ProductResponse> productList = productService.findAll()
                .stream()
                .map(product -> {
                    Link selfLink = linkTo(methodOn(ProductController.class).getByName(product.getName()))
                            .withSelfRel();
                    product.add(selfLink);
                    return product;
                }).collect(Collectors.toList());

        CollectionModel<ProductResponse> response = CollectionModel.of(productList, getSelfLink);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(method = "GET", summary = "Get the product by name")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Product with given name found",
                    content = {
                            @Content(mediaType = "application/hal+json",
                                    schema = @Schema(implementation = ProductResponse.class))
                    }
            ),
            @ApiResponse(responseCode = "404", description = "Product with given name not found", content = @Content)})

    @GetMapping("/{name}")
    public ResponseEntity<ProductResponse> getByName(@PathVariable String name) {
        return new ResponseEntity<ProductResponse>(productService.getByName(name), HttpStatus.OK);
    }
}
