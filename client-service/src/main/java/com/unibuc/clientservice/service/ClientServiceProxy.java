package com.unibuc.clientservice.service;

@FeignClient(value = "client", url = "localhost:8081")
public interface ClientServiceProxy {
}
