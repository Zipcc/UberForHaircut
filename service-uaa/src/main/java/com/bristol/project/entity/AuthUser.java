package com.bristol.project.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;


public class AuthUser extends User {

    public AuthUser(String username, String password, Collection<? extends GrantedAuthority> authorities){
        super(username, password, true, true, true, true, authorities);
    }

    @Getter
    @Setter
    private Integer id;

    @Getter
    @Setter
    private int sex;

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private String address;

    @Getter
    @Setter
    private int role;

}
