package com.unibuc.clientserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ClientServiceApp {
    public static void main(String[] args) {
        SpringApplication.run(ClientServiceApp.class, args);
    }
}

