package com.medilabo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class) //needs to exclude the db config
@EnableFeignClients
@EnableDiscoveryClient
public class frontEndApp {
    public static void main(String[] args) {
        SpringApplication.run(frontEndApp.class, args);
    }
}