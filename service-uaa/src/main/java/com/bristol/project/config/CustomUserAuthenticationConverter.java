package com.bristol.project.config;

import com.bristol.project.entity.AuthUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.provider.token.DefaultUserAuthenticationConverter;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import java.util.LinkedHashMap;
import java.util.Map;

@Component
public class CustomUserAuthenticationConverter extends DefaultUserAuthenticationConverter {

    @Resource
    UserDetailsService userDetailsService;

    @Override
    public Map<String, ?> convertUserAuthentication(Authentication authentication) {

        Map<String, Object> response = new LinkedHashMap<>();
        String name = authentication.getName();
        response.put("username", name);

        Object principal = authentication.getPrincipal();
        AuthUser authUser = null;
        if(principal instanceof  AuthUser){
            authUser = (AuthUser) principal;
        }else{
            //Add user properties into refresh_token
            UserDetails userDetails = userDetailsService.loadUserByUsername(name);
            authUser = (AuthUser) userDetails;
        }

        //Input customized properties
        response.put("name", authUser.getName());
        response.put("id", authUser.getId());
        response.put("role", authUser.getRole());

        if (authentication.getAuthorities() != null && !authentication.getAuthorities().isEmpty()) {
            response.put("authorities", AuthorityUtils.authorityListToSet(authentication.getAuthorities()));
        }
        return response;
    }
}
