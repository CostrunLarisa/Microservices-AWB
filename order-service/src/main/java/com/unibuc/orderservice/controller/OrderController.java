package com.unibuc.orderservice.controller;

import com.unibuc.clientservice.domain.model.dto.ClientDto;
import com.unibuc.orderservice.mapper.OrderMapper;
import com.unibuc.orderservice.model.Order;
import com.unibuc.orderservice.model.OrderDto;
import com.unibuc.orderservice.service.ClientServiceProxy;
import com.unibuc.orderservice.service.OrderService;
import com.unibuc.orderservice.service.ProductServiceProxy;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    private OrderMapper orderMapper = new OrderMapper();

    @Value("${msg:Config Server is not working. Please check...}")
    private String msg;

    private Logger LOGGER = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    public OrderController(ClientServiceProxy clientServiceProxy, ProductServiceProxy productServiceProxy, OrderService orderService) {
        this.clientServiceProxy = clientServiceProxy;
        this.productServiceProxy = productServiceProxy;
        this.orderService = orderService;
    }

    @Operation(method = "GET", summary = "Get all existing orders")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Orders found")})
    @GetMapping
    public ResponseEntity<CollectionModel<OrderDto>> getAllOrders() {
        Link getSelfLink = linkTo(methodOn(OrderController.class).getAllOrders()).withSelfRel();

        List<OrderDto> orderDtoList = orderService.getAllOrders()
                .stream()
                .map(order -> {
                    OrderDto dto = orderMapper.entityToDto(order);
                    Link selfLink = linkTo(methodOn(OrderController.class).getByClientEmail(order.getClientEmail())).withSelfRel(); dto.add(selfLink);
                    return dto;
                }).collect(Collectors.toList());
        CollectionModel<OrderDto> response = CollectionModel.of(orderDtoList, getSelfLink);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @Operation(method = "POST", summary = "Add a new order")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "New order added",
                    content = {@Content(mediaType = "application/hal+json",
                            schema = @Schema(implementation = Order.class))
                    }
            ), @ApiResponse(responseCode = "404",
            description = "Client with given email does not exist",
            content = @Content)
    })
    @PostMapping
    public ResponseEntity<Order> addOrder(@Valid @RequestBody OrderDto newOrder) {

        LOGGER.info("Aiciiii");
        Order order= orderMapper.dtoToEntity(newOrder);

        LOGGER.info("Hello");
        float price = productServiceProxy.getByName(order.getProductName()).getBody().getPrice();
        LOGGER.info("after proxy");
        price= price * order.getQuantity();
        order.setTotalPrice(price);
        orderService.addNewOrder(order);

        LOGGER.info("Here");

        Link getAllOrdersLink = linkTo(methodOn(OrderController.class).getAllOrders()).withSelfRel();
        newOrder.add(getAllOrdersLink);

        Link selfLink = linkTo(methodOn(OrderController.class).getByClientEmail(order.getClientEmail())).withSelfRel();
        newOrder.add(selfLink);

        Link selfLink1 = linkTo(methodOn(OrderController.class).getByProductName(order.getProductName())).withSelfRel();
        newOrder.add(selfLink1);

        return new ResponseEntity<>(order, HttpStatus.CREATED);
        

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
    @GetMapping("/clientEmail/{clientEmail}")
    public ResponseEntity<List<OrderDto>> getByClientEmail(@PathVariable("clientEmail") String clientEmail) {
        List<OrderDto> dtos = orderService.getOrderByClientEmail(clientEmail).stream().map(order -> {
            OrderDto dto = orderMapper.entityToDto(order);
            Link selfLink = linkTo(methodOn(OrderController.class).getByClientEmail(order.getClientEmail())).withSelfRel();
            dto.add(selfLink);
            return dto;
        }).collect(Collectors.toList());
         return new ResponseEntity<>(dtos, HttpStatus.OK);
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
    @GetMapping("/product/{productName}")
    public ResponseEntity<List<OrderDto>> getByProductName(@PathVariable("productName") String productName) {
        List<OrderDto> dtos = orderService.getOrderByProductName(productName).stream().map(order -> {
            OrderDto dto = orderMapper.entityToDto(order);
            Link selfLink = linkTo(methodOn(OrderController.class).getByProductName(order.getProductName())).withSelfRel();
            dto.add(selfLink);
            return dto;
        }).collect(Collectors.toList());

        return new ResponseEntity<>(dtos, HttpStatus.OK);
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
    public ResponseEntity<Order> updateOrder(@PathVariable("id") Long id, @RequestBody Order orderRequest) {
        Order orderUpdated = orderService.updateById(id, orderRequest);
        Link selfLink = linkTo(methodOn(OrderController.class).getByClientEmail(orderUpdated.getClientEmail())).withSelfRel();
        orderUpdated.add(selfLink);

        Link selfLink1 = linkTo(methodOn(OrderController.class).getByProductName(orderUpdated.getProductName())).withSelfRel();
        orderUpdated.add(selfLink1);

        return new ResponseEntity<>(orderUpdated, HttpStatus.OK);
    }




}
