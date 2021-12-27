package com.bristol.project.controller;

import com.bristol.project.entity.Result;
import com.bristol.project.service.UaaLoginService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;

@RestController
@RequestMapping("/uaa")
public class UaaLoginController {

    @Value("${auth.clientId}")
    private String clientId;

    @Value("${auth.clientSecret}")
    private String clientSecret;

    @Resource
    private UaaLoginService uaaLoginService;

    @RequestMapping("/login")
    public Result login(String username, String password) throws UnsupportedEncodingException {

        String grant_type = "password";
        uaaLoginService.login(username, password, clientId, clientSecret, grant_type);
        return null;

    }
}
