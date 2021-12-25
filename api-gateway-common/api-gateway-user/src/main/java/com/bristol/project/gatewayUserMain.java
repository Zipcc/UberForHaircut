package com.bristol.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Objects;

@SpringBootApplication
@EnableDiscoveryClient
public class gatewayUserMain {
    public static void main(String[] args) {
        SpringApplication.run(gatewayUserMain.class, args);
    }

    @Bean("ipKeyResolver")
    public KeyResolver userKeyResolver(){
        return new KeyResolver() {
            @Override
            public Mono<String> resolve(ServerWebExchange exchange) {
                String ip = Objects.requireNonNull(exchange.getRequest().getRemoteAddress()).getHostName();
                return Mono.just(ip);
            }
        };
    }
}
