package com.bristol.project.service.impl;

import cn.hutool.crypto.digest.BCrypt;
import cn.hutool.json.JSON;
import cn.hutool.json.JSONUtil;
import com.bristol.project.dao.UserDao;
import com.bristol.project.entity.Result;
import com.bristol.project.entity.User;
import com.bristol.project.service.UserService;
import com.bristol.project.utils.Jwt;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;

    @Override
    public Result login(String username, String password) {

        User user = userDao.getUserByUsername(username);
        if(BCrypt.checkpw(password, user.getPassword())){

            Map<String,Object> tokenMap = new HashMap<>();
            tokenMap.put("id", user.getId());
            tokenMap.put("username", user.getUsername());
            tokenMap.put("role", user.getRole());
            String token = Jwt.createJWT(UUID.randomUUID().toString(),JSONUtil.toJsonStr(tokenMap),null);

            return new Result<>(200, "Login successfully!", user);
        }
        return new Result(444, "Password is wrong.", PASSWORD_WRONG);
    }

    @Override
    public Result<Integer> create(User user) {

        if(userDao.getUserByUsername(user.getUsername()) == null){
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
