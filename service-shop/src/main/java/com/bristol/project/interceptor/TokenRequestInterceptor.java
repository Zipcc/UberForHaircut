package com.bristol.project.interceptor;

import com.bristol.project.util.AdminToken;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TokenRequestInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate requestTemplate) {

        String token = AdminToken.CreateAdminToken();
        requestTemplate.header("Authorization","Bearer " + token);
    }
}
