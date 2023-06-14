package com.unibuc.ro.client;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Lazy;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableDiscoveryClient
@RestController
public class FeignClientApp {
    @Autowired
    @Lazy
    private EurekaClient eurekaClient;

    public static void main(String[] args) {
        SpringApplication.run(FeignClientApp.class,args);
    }
    @PostMapping( "/products")
    public void save(Model model) {
        InstanceInfo service = eurekaClient
                .getApplication("product-service")
                .getInstances()
                .get(0);

        String hostName = service.getHostName();
        int port = service.getPort();
        System.out.println(hostName);
        System.out.println(port);
    }
}
