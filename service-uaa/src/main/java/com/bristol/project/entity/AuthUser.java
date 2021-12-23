package com.bristol.project.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Set;

@Data
@AllArgsConstructor
public class AuthUser implements UserDetails {

    private Integer id;
    private String password;
    private final String username;
    private Set<SimpleGrantedAuthority> authorities;
    private final boolean accountNonExpired;
    private final boolean accountNonLocked;
    private final boolean credentialsNonExpired;
    private final boolean enabled;

}
