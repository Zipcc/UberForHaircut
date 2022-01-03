package com.bristol.project.filter;

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

        String url = request.getURI().getPath();
        if(!URLFilter.needAuthorize(url)){
            System.out.println(url + " permitted");
            return chain.filter(exchange);
        }

        //Whether token is in headers
        String token = request.getHeaders().getFirst(AUTHORIZE_TOKEN);
        boolean headerHasToken = true;

        //Whether token is in parameters
        if(token == null || token.isEmpty()){
            token = request.getQueryParams().getFirst(AUTHORIZE_TOKEN);
            headerHasToken = false;
        }

        //Whether token is in cookies
        if(token == null || token.isEmpty()){
            HttpCookie httpCookie = request.getCookies().getFirst(AUTHORIZE_TOKEN);
            if(httpCookie != null){
                token = httpCookie.getValue();
            }
        }

        //If all are not, there is no authorized token.
        if(token == null || token.isEmpty()){
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            return response.setComplete();
        }else{
            //Add token to header
            if(!headerHasToken){
                if(!token.startsWith("bearer ") && !token.startsWith("Bearer ")){
                    token = "bearer " + token;
                }
                request.mutate().header(AUTHORIZE_TOKEN, token);
            }
        }
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
