package com.unibuc.orderservice.controller;

import com.netflix.discovery.converters.Auto;
import com.unibuc.clientservice.controller.ClientController;
import com.unibuc.clientservice.domain.model.Client;
import com.unibuc.clientservice.domain.model.dto.ClientDto;
import com.unibuc.clientservice.service.ClientService;
import com.unibuc.orderservice.model.Order;
import com.unibuc.orderservice.service.ClientServiceProxy;
import com.unibuc.orderservice.service.OrderService;
import com.unibuc.orderservice.service.ProductResponse;
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

        ClientDto clientDto = clientServiceProxy.getByEmail(newOrder.getClientEmail()).getBody();
        ProductResponse productResponse=productServiceProxy.getByName(newOrder.getProductName()).getBody();
        
        orderService.addNewOrder(newOrder);

        Link getAllOrdersLink = linkTo(methodOn(OrderController.class).orderService.getAllOrders())
                .withRel("getAllOrders");
        newOrder.add(getAllOrdersLink);

        Link getByClientEmailLink = linkTo(methodOn(OrderController.class).orderService.getByClientEmail(newOrder.getClientEmail()))
                .withRel("getByClientEmail");
        newOrder.add(getByClientEmailLink);

        Link getByProductNameLink = linkTo(methodOn(OrderController.class).orderService.getByProductName(newOrder.getProductName()))
                .withRel("getByProductName");
        newOrder.add(getByProductNameLink);

        return new ResponseEntity<>(newOrder, HttpStatus.CREATED);
    }

    @Operation(method = "GET", summary = "Get orders by ClientEmail")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Orders with given client found",
                    content = {
                            @Content(mediaType = "application/hal+json",
                                    schema = @Schema(implementation = Order.class))
                    }
            ),
            @ApiResponse(responseCode = "404", description = "Oreders for client not found", content = @Content)})
    @GetMapping("/{clientEmail}")
    public ResponseEntity<List<Order>> getByClientId(@PathVariable("clientEmail") String clientEmail) {
        List<Order> orders = orderService.getByClientEmail(clientEmail);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }


    @Operation(method = "GET", summary = "Get orders by ProductName")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Orders with given product found",
                    content = {
                            @Content(mediaType = "application/hal+json",
                                    schema = @Schema(implementation = Order.class))
                    }
            ),
            @ApiResponse(responseCode = "404", description = "Oreders with given product not found", content = @Content)})
    @GetMapping("/{productName}")
    public ResponseEntity<List<Order>> getByProductId(@PathVariable("productName") String productName) {
        List<Order> orders = orderService.getByProductName(productName);

        return new ResponseEntity<>(orders, HttpStatus.OK);
    }



    @Operation(method = "PUT", summary = "Update info for an order identified by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Order info updated",
                    content = {@Content(
                            mediaType = "application/hal+json",
                            schema = @Schema(implementation = Order.class))
                    }
            ),
            @ApiResponse(responseCode = "404",
                    description = "Order with given id not found",
                    content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<Order> updateProduct(@PathVariable("id") Long id, @RequestBody Order orderRequest) {
        Order orderUpdated = orderService.updateById(id, orderRequest);
        Link getByClientEmail = linkTo(methodOn(OrderController.class).orderService.getByClientEmail(orderUpdated.getClientEmail()))
                .withRel("getByClientEmail");
        orderUpdated.add(getByClientEmail);

        Link getByProductName = linkTo(methodOn(OrderController.class).orderService.getByProductName(orderUpdated.getProductName()))
                .withRel("getByProductName");
        orderUpdated.add(getByProductName);

        return new ResponseEntity<>(orderUpdated, HttpStatus.OK);
    }




}
