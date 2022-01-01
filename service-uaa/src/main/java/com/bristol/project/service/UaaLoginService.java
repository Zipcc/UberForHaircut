package com.bristol.project.service;

import com.bristol.project.entity.AuthToken;

import java.io.UnsupportedEncodingException;

public interface UaaLoginService {
    AuthToken login(String username, String password, String clientId, String clientSecret, String grant_type) throws UnsupportedEncodingException;
}
