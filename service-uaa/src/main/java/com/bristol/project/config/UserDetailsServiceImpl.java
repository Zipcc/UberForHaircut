package com.bristol.project.config;
//import com.changgou.oauth.util.UserJwt;
//import com.robod.user.feign.UserFeign;
import com.bristol.project.entity.AuthUser;
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

/*****
 * 自定义授权认证类
 * @author robod
 */
@Service("userDetailsServiceImpl")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Resource
    ClientDetailsService clientDetailsService;

  //  @Autowired
  //  private UserFeign userFeign;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        /*客户端信息认证*/
        //取出身份，如果身份为空说明没有认证
  //      Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //没有认证统一采用httpbasic认证，httpbasic中存储了client_id和client_secret，开始认证client_id和client_secret
    //    if(authentication==null){
       //     ClientDetails clientDetails = clientDetailsService.loadClientByClientId(username);
    //        if(clientDetails!=null){
                //秘钥
    //            String clientSecret = clientDetails.getClientSecret();
                //静态方式
        AuthUser authUser = new AuthUser(username,new BCryptPasswordEncoder().encode("c"),
                                        AuthorityUtils.commaSeparatedStringToAuthorityList("king"));
        authUser.setSex(0);
        authUser.setAddress("University of Bristol");
                return authUser;
                //数据库查找方式
               // return new User(username,clientSecret, AuthorityUtils.commaSeparatedStringToAuthorityList(""));
     //       }
    //    }

     //   /*用户信息认证*/
     //   if (StringUtils.isEmpty(username)) {
     //       return null;
     //   }
//
     //   com.robod.user.pojo.User user = userFeign.findById(username).getData();
     //   if (user == null ) {
     //       return null;
     //   }
     //   String pwd = user.getPassword();
     //   //创建User对象
     //   String permissions = "USER";
     //   UserJwt userDetails = new UserJwt(username,pwd,AuthorityUtils.commaSeparatedStringToAuthorityList(permissions));
//
      //  return null;
    }
}
