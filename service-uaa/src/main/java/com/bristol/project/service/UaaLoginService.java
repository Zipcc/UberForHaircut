package com.bristol.project.service;

import java.io.UnsupportedEncodingException;

public interface UaaLoginService {
    void login(String username, String password, String clientId, String clientSecret, String grant_type) throws UnsupportedEncodingException;
}
