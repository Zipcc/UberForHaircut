package com.bristol.project;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class BookingMain {

    public static void main(String[] args) {
        SpringApplication.run(BookingMain.class, args);
    }
}
