package com.bristol.project.utils;

import cn.hutool.json.JSONUtil;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.jwt.Jwt;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.security.jwt.crypto.sign.RsaVerifier;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.stream.Collectors;

public class TokenDecoder {

    private static final String PUBLIC_KEY = "public.key";

    public static String tokenUsername(){

        Map<String, Object> currentUserInfo = TokenDecoder.getUserInfo();
        return  (String)currentUserInfo.get("username");
    }

    public static Map<String, Object> getUserInfo(){

        OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails) SecurityContextHolder.getContext().getAuthentication().getDetails();
        String token = details.getTokenValue();
        return decodeToken(token);
    }

    public static Map<String,Object> decodeToken(String token){

        Jwt jwt = JwtHelper.decodeAndVerify(token, new RsaVerifier(getPublicKey()));
        String claims = jwt.getClaims();
        return (Map<String, Object>) JSONUtil.parseObj(claims).toBean(Map.class);
    }

    private static String getPublicKey(){

        Resource resource = new ClassPathResource(PUBLIC_KEY);
        return readPublicKey(resource);
    }

    public static String readPublicKey(Resource resource){

        try {
            InputStreamReader inputStreamReader = new InputStreamReader(resource.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            return bufferedReader.lines().collect(Collectors.joining("\n"));
        } catch (IOException e){
            return "Read public key failed.";
        }
    }
}
