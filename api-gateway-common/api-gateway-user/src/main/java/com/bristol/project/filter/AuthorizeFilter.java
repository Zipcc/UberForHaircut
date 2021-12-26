package com.bristol.project.filter;

import com.bristol.project.util.Jwt;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpCookie;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;


@Component
public class AuthorizeFilter implements GlobalFilter, Ordered {

    private static final String AUTHORIZE_TOKEN = "Authorization";

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();

        //Whether token is in headers.
        String token = request.getHeaders().getFirst(AUTHORIZE_TOKEN);

        System.out.println("head"+token);

        //Whether token is in parameters.
        if(token == null || token.isEmpty()){
            token = request.getQueryParams().getFirst(AUTHORIZE_TOKEN);
        }

        System.out.println("para"+token);

        //Whether token is in cookies.
        if(token == null || token.isEmpty()){
            HttpCookie httpCookie = request.getCookies().getFirst(AUTHORIZE_TOKEN);
            if(httpCookie != null){
                token = httpCookie.getValue();
            }
        }

        System.out.println("cook"+token);

        //If all are not, there is no authorized token.
        if(token == null || token.isEmpty()){
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            return response.setComplete();
        }

        System.out.println("pwd");

        try {
            Jwt.parseJWT(token);
        } catch (Exception e) {
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            return response.setComplete();
        }

        System.out.println("done");

        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
