package com.unibuc.orderservice.service;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "client-service")
public interface ClientServiceProxy {
}
