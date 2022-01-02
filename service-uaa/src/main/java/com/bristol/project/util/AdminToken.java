package com.bristol.project.util;

import cn.hutool.json.JSONUtil;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.jwt.Jwt;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.security.jwt.crypto.sign.RsaSigner;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;
import java.security.KeyPair;
import java.security.interfaces.RSAPrivateKey;
import java.util.HashMap;
import java.util.Map;

public class AdminToken {


    public static String CreateAdminToken() {

        ClassPathResource classPathResource = new ClassPathResource("jianchenkey.jks");
        KeyStoreKeyFactory keyStoreKeyFactory =
                new KeyStoreKeyFactory(classPathResource, "jianchens".toCharArray());
        KeyPair keyPair = keyStoreKeyFactory.getKeyPair("jianchena", "jianchenp".toCharArray());
        RSAPrivateKey rsaPrivateKey = (RSAPrivateKey) keyPair.getPrivate();

        Map<String, Object> payload = new HashMap<>();
        payload.put("authorities", new String[]{"admin", "oauth"});

        Jwt jwt = JwtHelper.encode(JSONUtil.toJsonStr(payload), new RsaSigner(rsaPrivateKey));

        return jwt.getEncoded();
    }
}
