package com.unibuc.ro.service;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "client-service")
public interface ClientServiceProxy {
}
