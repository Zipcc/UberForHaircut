package com.bristol.project.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.bootstrap.encrypt.KeyProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;
import javax.annotation.Resource;
import java.security.KeyPair;


@Configuration
@EnableAuthorizationServer
class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Resource
    private AuthenticationManager authenticationManager;

    @Resource
    private UserDetailsService userDetailsService;

    @Resource(name = "customJWT")
    public JwtAccessTokenConverter jwtAccessTokenConverter;


 //   @Resource(name = "keyProp")
//    private KeyProperties keyProperties;

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {

        clients.inMemory()
                .withClient("jianchen")          //客户端id
                .secret(new BCryptPasswordEncoder().encode("jianchen"))      //秘钥
    //            .redirectUris("http://localhost")       //重定向地址
                .accessTokenValiditySeconds(3600)          //访问令牌有效期
                .refreshTokenValiditySeconds(3600)         //刷新令牌有效期
                .authorizedGrantTypes(
                        "authorization_code",          //根据授权码生成令牌
                        "client_credentials",          //客户端认证
                        "refresh_token",                //刷新令牌
                        "password")                     //密码方式认证
                .scopes("app");                         //客户端范围，名称自定义，必填
        // clients.jdbc(dataSource).clients(clientDetails());
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpointsConfigurer){

        //刷新令牌时需要的认证管理和用户信息来源
        endpointsConfigurer
                .authenticationManager(authenticationManager)
                .accessTokenConverter(jwtAccessTokenConverter)
                .allowedTokenEndpointRequestMethods(HttpMethod.GET,HttpMethod.POST)
                .authenticationManager(authenticationManager)
                .userDetailsService(userDetailsService);
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
        oauthServer.allowFormAuthenticationForClients()
                .passwordEncoder(new BCryptPasswordEncoder())
                .tokenKeyAccess("permitAll()");
        //        .checkTokenAccess("isAuthenticated()");
    }


    @Bean(name = "customJWT")
    public JwtAccessTokenConverter jwtAccessTokenConverter(CustomUserAuthenticationConverter customUserAuthenticationConverter) {

        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(new ClassPathResource("jianchenprojectkey.jks"), "jianchens".toCharArray());
        converter.setKeyPair(keyStoreKeyFactory.getKeyPair("jianchena","jianchenp".toCharArray()));

      //  converter.setSigningKey("00367171843C185C043DDFB90AA97677F11D02B629DEAFC04F935419D832E697");

        DefaultAccessTokenConverter defaultAccessTokenConverter = (DefaultAccessTokenConverter) converter.getAccessTokenConverter();
        defaultAccessTokenConverter.setUserTokenConverter(customUserAuthenticationConverter);
        return converter;
    }

    @Bean
    public TokenStore tokenStore(@Qualifier("customJWT") JwtAccessTokenConverter jwtAccessTokenConverter) {
        return new JwtTokenStore(jwtAccessTokenConverter);
    }

    /*    @Bean("resJwtAccessTokenConverter")
    public JwtAccessTokenConverter resJwtAccessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessToken();
        String publicKey = getPubKey();
        converter.setVerifierKey(publicKey);
        //不设置这个会出现 Cannot convert access token to JSON
        converter.setVerifier(new RsaVerifier(publicKey));
        return converter;
    }


    private String getPubKey() {
        Resource resource = new ClassPathResource(ResJWTTokenStore.PUBLIC_KEY);
        try (BufferedReader br = new BufferedReader(new InputStreamReader(resource.getInputStream()))) {
            return br.lines().collect(Collectors.joining("\n"));
        } catch (IOException ioe) {
            return getKeyFromAuthorizationServer();
        }
    }


    private String getKeyFromAuthorizationServer() {
        ObjectMapper objectMapper = new ObjectMapper();
        String pubKey = new RestTemplate().getForObject(resourceServerProperties.getJwt().getKeyUri(), String.class);
        try {
            Map map = objectMapper.readValue(pubKey, Map.class);
            return map.get("value").toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public JwtAccessTokenConverter jwtAccessTokenConverter(CustomUserAuthenticationConverter customUserAuthenticationConverter) {

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
    }


*/
}
