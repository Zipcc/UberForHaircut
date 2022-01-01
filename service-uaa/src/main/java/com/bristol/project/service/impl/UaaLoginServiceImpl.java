package com.bristol.project.service.impl;

import com.bristol.project.entity.AuthToken;
import com.bristol.project.service.UaaLoginService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Map;

@Service
public class UaaLoginServiceImpl implements UaaLoginService {

    @Resource
    private RestTemplate restTemplate;

    @Value("${server.port}")
    private String uaaPort;

    public AuthToken login(String username, String password, String clientId, String clientSecret, String grant_type) throws UnsupportedEncodingException {

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

        HttpEntity<MultiValueMap<String,String>> httpEntity = new HttpEntity<>(bodyMap,httpHeaders);
        ResponseEntity<Map> response = restTemplate.postForEntity(url, httpEntity, Map.class);
        Map<String,String> responseMap = response.getBody();
        AuthToken authToken = new AuthToken(responseMap.get("access_token"),responseMap.get("refresh_token"),responseMap.get("jti"));
        return authToken;
    }

}
