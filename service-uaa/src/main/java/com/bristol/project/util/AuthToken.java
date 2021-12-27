package com.bristol.project.util;

import lombok.Data;

import java.io.Serializable;

@Data
public class AuthToken implements Serializable{

    String accessToken;
    String refreshToken;
    String jti;

}
