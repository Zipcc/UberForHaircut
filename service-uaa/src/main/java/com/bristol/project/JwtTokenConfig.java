package com.bristol.project;

import org.springframework.cloud.bootstrap.encrypt.KeyProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.security.KeyPair;



public class JwtTokenConfig{

   // @Resource
   // private CustomUserAuthenticationConverter customUserAuthenticationConverter;

  //  @Resource
   // private KeyProperties keyProperties;

  //  @Bean
   // public KeyProperties keyProperties(){
   //     return new KeyProperties();
  //  }

    //@Bean
//    public JwtAccessTokenConverter jwtAccessTokenConverter(){

//        keyProperties.setKey("a");
  //      System.out.println("----------"+keyProperties.getKeyStore().getLocation());

  //      JwtAccessTokenConverter accessTokenConverter = new JwtAccessTokenConverter();
   //     org.springframework.core.io.Resource resource = keyProperties.getKeyStore().getLocation();
 //       char[] chars = keyProperties.getKeyStore().getSecret().toCharArray();
 //       KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(resource, chars);             // secret: jianchen
 //       KeyPair keyPair = keyStoreKeyFactory.getKeyPair(
 //               keyProperties.getKeyStore().getAlias(),                     // alias: jianchen
 //               keyProperties.getKeyStore().getPassword().toCharArray());   // password: jianchen
 //       accessTokenConverter.setKeyPair(keyPair);

      //  DefaultAccessTokenConverter defaultAccessTokenConverter = (DefaultAccessTokenConverter) accessTokenConverter.getAccessTokenConverter();
      //  defaultAccessTokenConverter.setUserTokenConverter(customUserAuthenticationConverter);
  //      return accessTokenConverter;
  //  }

    /*public JwtAccessTokenConverter jwtAccess1TokenConverter(CustomUserAuthenticationConverter customUserAuthenticationConverter) {

        System.out.println(keyProperties.getKeyStore().getLocation());
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(
                keyProperties.getKeyStore().getLocation(),                          // location: classpath:/bristolproject.jks
                keyProperties.getKeyStore().getSecret().toCharArray());             // secret: jianchen
        KeyPair keyPair = keyStoreKeyFactory.getKeyPair(
                keyProperties.getKeyStore().getAlias(),                     // alias: jianchen
                keyProperties.getKeyStore().getPassword().toCharArray());   // password: jianchen
        converter.setKeyPair(keyPair);
        DefaultAccessTokenConverter defaultAccessTokenConverter = (DefaultAccessTokenConverter) converter.getAccessTokenConverter();
        defaultAccessTokenConverter.setUserTokenConverter(customUserAuthenticationConverter);
        return converter;
    }*/
}
