package com.unibuc.orderservice.service;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "clientservice")
public interface ClientServiceProxy {
}
