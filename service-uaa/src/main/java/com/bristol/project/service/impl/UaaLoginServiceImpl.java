package com.bristol.project.service.impl;

import com.bristol.project.entity.AuthToken;
import com.bristol.project.service.UaaLoginService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Map;

@Service
public class UaaLoginServiceImpl implements UaaLoginService {

    @Resource
    private RestTemplate restTemplate;

    @Value("${server.port}")
    private String uaaPort;

    public AuthToken login(String username, String password, String clientId, String clientSecret, String grant_type) {

        String uaaHost = "localhost";
        String url = "http://" + uaaHost + ":" + uaaPort + "/oauth/token";

        //Body
        MultiValueMap<String,String> bodyMap = new LinkedMultiValueMap<>();
        bodyMap.add("username", username);
        bodyMap.add("password", password);
        bodyMap.add("grant_type", grant_type);

        //Header
        HttpHeaders httpHeaders = new HttpHeaders();
        String authorization = "Basic " +
                new String(Base64.getEncoder().encode((clientId + ":" + clientSecret).getBytes()), StandardCharsets.UTF_8);
        httpHeaders.add("Authorization", authorization);

        HttpEntity<MultiValueMap<String,String>> httpEntity = new HttpEntity<>(bodyMap, httpHeaders);
        Map<String,String> responseMap =  restTemplate.postForEntity(url, httpEntity, Map.class).getBody();
        if(responseMap == null){
            return new AuthToken("", "", "");
        }
        return new AuthToken(responseMap.get("access_token"),responseMap.get("refresh_token"),responseMap.get("jti"));
    }

}
