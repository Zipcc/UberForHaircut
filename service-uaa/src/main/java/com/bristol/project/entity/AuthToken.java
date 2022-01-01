package com.bristol.project.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class AuthToken implements Serializable {

    String accessToken;
    String refreshToken;
    String jti;
}
