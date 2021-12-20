package com.bristol.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class NacosConfigClientMain3001 {
    public static void main(String[] args) {
        SpringApplication.run(NacosConfigClientMain3001.class,args);
    }
}
