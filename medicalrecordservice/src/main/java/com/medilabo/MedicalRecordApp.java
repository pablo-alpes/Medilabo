package com.medilabo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MedicalRecordApp {
    public static void main(String[] args) {
        SpringApplication.run(MedicalRecordApp.class, args);
    }
}