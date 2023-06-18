package com.unibuc.orderservice.mapper;

import com.unibuc.clientservice.domain.model.Client;
import com.unibuc.clientservice.domain.model.dto.ClientDto;
import com.unibuc.orderservice.model.Order;
import com.unibuc.orderservice.model.OrderDto;

public class OrderMapper {

    public OrderDto entityToDto(Order entity) {
        OrderDto dto = new OrderDto();
        dto.setClientEmail(entity.getClientEmail());
        dto.setProductName(entity.getProductName());
        dto.setQuantity(entity.getQuantity());
        return dto;
    }

    public Order dtoToEntity(OrderDto dto) {
        Order entity = new Order();
        entity.setClientEmail(dto.getClientEmail());
        entity.setProductName(dto.getProductName());
        entity.setQuantity(dto.getQuantity());
        return entity;
    }

}
