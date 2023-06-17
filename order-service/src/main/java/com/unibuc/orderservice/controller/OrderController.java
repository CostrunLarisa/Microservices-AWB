package com.unibuc.orderservice.controller;

import com.netflix.discovery.converters.Auto;
import com.unibuc.clientservice.controller.ClientController;
import com.unibuc.clientservice.domain.model.Client;
import com.unibuc.clientservice.domain.model.dto.ClientDto;
import com.unibuc.clientservice.service.ClientService;
import com.unibuc.orderservice.model.Order;
import com.unibuc.orderservice.service.ClientServiceProxy;
import com.unibuc.orderservice.service.OrderService;
import com.unibuc.orderservice.service.ProductServiceProxy;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
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
@RequestMapping("/orders")
public class OrderController {

    private final ClientServiceProxy clientServiceProxy;

    private final ProductServiceProxy productServiceProxy;

    private final OrderService orderService;


    @Autowired
    public OrderController(ClientServiceProxy clientServiceProxy, ProductServiceProxy productServiceProxy, OrderService orderService) {
        this.clientServiceProxy = clientServiceProxy;
        this.productServiceProxy = productServiceProxy;
        this.orderService = orderService;
    }

    @Operation(method = "GET", summary = "Get all existing orders")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Orders found")})
    @GetMapping
    public ResponseEntity<CollectionModel<Order>> getAll() {
        Link getSelfLink = linkTo(methodOn(OrderController.class).getAll()).withSelfRel();

        List<Order> ordersList = orderService.getAllOrders()
                .stream()
                .collect(Collectors.toList());

        CollectionModel<Order> response = CollectionModel.of(ordersList, getSelfLink);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @Operation(method = "POST", summary = "Add a new order")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "New order added",
                    content = {@Content(mediaType = "application/hal+json",
                            schema = @Schema(implementation = Order.class))
                    }
            )
    })
    @PostMapping
    public ResponseEntity<Order> addOrder(@Valid @RequestBody Order newOrder) {
        orderService.addNewOrder(newOrder);

        Link getByClientIdLink = linkTo(methodOn(OrderController.class).orderService.getByClientId(newOrder.getClientId()))
                .withRel("getByClientId");
        newOrder.add(getByClientIdLink);
        return new ResponseEntity<>(newOrder, HttpStatus.CREATED);
    }

    @Operation(method = "GET", summary = "Get order by ClientId")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Orders with given client found",
                    content = {
                            @Content(mediaType = "application/hal+json",
                                    schema = @Schema(implementation = Order.class))
                    }
            ),
            @ApiResponse(responseCode = "404", description = "Oreders for client not found", content = @Content)})
    @GetMapping("/{clientId}")
    public ResponseEntity<List<Order>> getByClientId(@PathVariable("clientId") Long clientId) {
        List<Order> orders = orderService.getByClientId(clientId);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }





}
