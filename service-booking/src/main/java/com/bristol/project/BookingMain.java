package com.bristol.project;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableDiscoveryClient
@SpringBootApplication
@EnableFeignClients(basePackages = "com.bristol.project.openFeign")
public class BookingMain {

    public static void main(String[] args) {
        SpringApplication.run(BookingMain.class, args);
    }
}
