package com.bristol.project.service.impl;

import cn.hutool.crypto.digest.BCrypt;
import cn.hutool.json.JSONUtil;
import com.bristol.project.dao.UserDao;
import com.bristol.project.entity.Result;
import com.bristol.project.entity.User;
import com.bristol.project.service.UserService;
import com.bristol.project.utils.Jwt;
import com.bristol.project.utils.StatusCode;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;

    @Override
    public Result login(String username, String password, HttpServletResponse response) {

        User user = userDao.getUserByUsername(username);
        if(user == null){
            return new Result<>(StatusCode.NOT_EXIST, "User: " + username + " not exist!");
        }
        if(BCrypt.checkpw(password, user.getPassword())){

            Map<String,Object> tokenMap = new HashMap<>();
            tokenMap.put("id", user.getId());
            tokenMap.put("username", user.getUsername());
            tokenMap.put("role", user.getRole());
           // String token = Jwt.createJWT(UUID.randomUUID().toString(),JSONUtil.toJsonStr(tokenMap),null);

           // Cookie cookie = new Cookie("Authorization", token);
           // cookie.setDomain("localhost");
           // cookie.setPath("/");
           // response.addCookie(cookie);

            return new Result<>(StatusCode.OK, "User: " + username + " login successfully!");
        }
        return new Result(StatusCode.PASSWORD_WRONG, "Password is wrong.");
    }

    @Override
    public Result<Integer> create(User user) {

        if(userDao.getUserByUsername(user.getUsername()) == null){
            user.setPassword(BCrypt.hashpw(user.getPassword()));
            int userId = userDao.create(user);
            if (userId > 0) {
                //Success.
                return new Result<>(StatusCode.OK, "Register successfully!", userId);
            }else{
                //Create failed.
                return new Result<>(StatusCode.CREATE_FAILED,"Failed to register.");
            }
        }else{
            //Already exist.
            return new Result<>(StatusCode.ALREADY_EXIST,"Username: " + user.getUsername() + " already exists.");
        }
    }

    @Override
    public Result<User> getUserByUsername(String username) {
        User user = userDao.getUserByUsername(username);

        if (user == null){
            return new Result<>(StatusCode.NOT_EXIST,"User -> " + username + " <- not exist.");
        }
        return new Result<>(StatusCode.OK,"Find user -> " + username + " <- successfully!",user);
    }
}
