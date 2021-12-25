package com.bristol.project.service.impl;

import cn.hutool.crypto.digest.BCrypt;
import cn.hutool.json.JSONUtil;
import com.bristol.project.dao.UserDao;
import com.bristol.project.entity.Result;
import com.bristol.project.entity.User;
import com.bristol.project.service.UserService;
import com.bristol.project.utils.Jwt;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;

    @Override
    public Result login(String username, String password) {
        System.out.println(username);
        System.out.println(password);
        User user = userDao.getUserByUsername(username);
        if(user == null){
            return new Result<>(200, "User not exist.!", NOT_EXIST);
        }
        if(BCrypt.checkpw(password, user.getPassword())){

            Map<String,Object> tokenMap = new HashMap<>();
            tokenMap.put("id", user.getId());
            tokenMap.put("username", user.getUsername());
            tokenMap.put("role", user.getRole());
            String token = Jwt.createJWT(UUID.randomUUID().toString(),JSONUtil.toJsonStr(tokenMap),null);

            Cookie cookie = new Cookie("Authorization", token);
            cookie.setDomain("localhost");
            cookie.setPath("/");

            return new Result<>(200, "Login successfully!", token);
        }
        return new Result(444, "Password is wrong.", PASSWORD_WRONG);
    }

    @Override
    public Result<Integer> create(User user) {

        if(userDao.getUserByUsername(user.getUsername()) == null){
            user.setPassword(BCrypt.hashpw(user.getPassword()));
            int userId = userDao.create(user);
            if (userId > 0) {
                //Success.
                return new Result<>(200, "Register successfully!", userId);
            }else{
                //Create failed.
                return new Result<>(444,"Failed to register.", CREATE_FAILED);
            }
        }else{
            //Already exist.
            return new Result<>(444,"Username -> " + user.getUsername() + " <- already exists.", ALREADY_EXIST);
        }
    }

    @Override
    public Result<User> getUserByUsername(String username) {
        User user = userDao.getUserByUsername(username);

        if (user == null){
            return new Result<>(555,"User -> " + username + " <- not exist.", null);
        }
        return new Result<>(200,"Find user -> " + username + " <- successfully!",user);
    }
}
