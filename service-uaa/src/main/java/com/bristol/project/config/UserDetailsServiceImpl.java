package com.bristol.project.config;
import com.bristol.project.entity.AuthUser;
import com.bristol.project.entity.Result;
import com.bristol.project.entity.Role;
import com.bristol.project.entity.User;
import com.bristol.project.openFeign.UserFeignApi;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("userDetailsServiceImpl")
public class UserDetailsServiceImpl implements UserDetailsService {


    @Resource()
    private UserFeignApi userApi;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

           if (username == null || username.isEmpty()) {
            return null;
        }

        Result<com.bristol.project.entity.User> userResult = userApi.getUserByUsername(username);
        if (userResult == null || userResult.getData() == null) {
            return null;
        }
        User user = userResult.getData();

        String password = user.getPassword();
        int role = user.getRole();
        String permissions = "null";
        if (role == Role.ADMIN_NUM) {
            permissions = "admin,client,barber";
        } else if (role == Role.Client_NUM) {
            permissions = "client";
        } else if (role == Role.Barber_NUM) {
            permissions = "barber";
        }

        AuthUser authUser = new AuthUser(username, password, AuthorityUtils.commaSeparatedStringToAuthorityList(permissions));
        authUser.setRole(role);
        authUser.setId(user.getUserId());
        authUser.setName(user.getUsername());
        return authUser;
    }
}
