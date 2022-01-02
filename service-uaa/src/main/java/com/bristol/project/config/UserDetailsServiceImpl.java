package com.bristol.project.config;
//import com.changgou.oauth.util.UserJwt;
//import com.robod.user.feign.UserFeign;
import com.bristol.project.entity.AuthUser;
import com.bristol.project.entity.Result;
import com.bristol.project.openFeign.UserApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;

@Service("userDetailsServiceImpl")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Resource
    ClientDetailsService clientDetailsService;

    @Resource
    private UserApi userApi;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        /*客户端信息认证*/
        //取出身份，如果身份为空说明没有认证
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //没有认证统一采用httpbasic认证，httpbasic中存储了client_id和client_secret，开始认证client_id和client_secret
       // if(authentication == null) {
       //     // Query table oauth_client_details
       //     ClientDetails clientDetails = clientDetailsService.loadClientByClientId(username);
       //     if (clientDetails != null) {
       //         String clientSecret = clientDetails.getClientSecret();
       //         System.out.println("-----------------------------"+username);
       //         System.out.println("-----------------------------"+clientSecret);
       //         return new User(username, clientSecret, AuthorityUtils.commaSeparatedStringToAuthorityList(""));
       //     }
       //     //AuthUser authUser = new AuthUser(username,new BCryptPasswordEncoder().encode("c"),
       //     //                                AuthorityUtils.commaSeparatedStringToAuthorityList("king"));
       //     //authUser.setSex(0);
       //     //authUser.setAddress("University of Bristol");
       //     //        return authUser;
       // }

        if(username == null || username.isEmpty()){
            return null;
        }

        Result<com.bristol.project.entity.User> userResult = userApi.getUserByUsername(username);
        if (userResult == null || userResult.getData() == null) {
            return null;
        }
        String password = userResult.getData().getPassword();
        int roles = userResult.getData().getRole();
        String permissions = "null";
        if(roles == 0){
            permissions = "admin,client,barber";
        }else if(roles == 1){
            permissions = "client";
        }else if(roles == 2){
            permissions = "barber";
        }
        return new AuthUser(username,password,AuthorityUtils.commaSeparatedStringToAuthorityList(permissions));
    }
}
