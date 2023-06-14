package com.unibuc.clientservice.service;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "client", url = "localhost:8081")
public interface ClientServiceProxy {
}
