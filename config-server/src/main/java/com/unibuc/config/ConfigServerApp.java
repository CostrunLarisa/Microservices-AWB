package com.unibuc.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;


@SpringBootApplication
@EnableConfigServer
@Profile("git")
@PropertySource("classpath:application.properties")
public class ConfigServerApp {
    public static void main(String[] args) {
        SpringApplication.run(ConfigServerApp.class, args);
    }
}
