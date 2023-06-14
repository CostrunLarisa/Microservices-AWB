package com.unibuc.orderservice;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "client-service")
public interface ClientServiceProxy {
}
