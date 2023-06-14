package com.unibuc.ro;

import com.netflix.discovery.EurekaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.context.annotation.Lazy;

@SpringBootApplication
@EnableEurekaServer
public class EurekaServerApp {
    @Autowired
    @Lazy
    private EurekaClient eurekaClient;
    public static void main(String[] args) {
        SpringApplication.run(EurekaServerApp.class, args);
    }
}
