package com.bristol.project.controller;

import com.bristol.project.APIs.UserApi;
import com.bristol.project.entity.Result;
import com.bristol.project.entity.User;
import com.bristol.project.service.UserService;
import com.bristol.project.utils.StatusCode;
import com.bristol.project.utils.StringUtil;
import com.bristol.project.utils.TokenDecoder;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import java.util.List;

@RestController
public class UserController implements UserApi {

    @Resource
    private UserService userService;

    @Override
    public Result<User> create(User user){

        if(user == null || StringUtil.notExist(user.getUsername())
                || StringUtil.notExist(user.getPassword())){
            return new Result<>(StatusCode.NOT_EXIST, "Please enter username and password.");
        }
        return userService.create(user);
    }

    @Override
    public Result<Integer> deleteUserByUsername(String username) {

        if(StringUtil.notExist(username)){
            return new Result<>(StatusCode.NOT_EXIST, "Please enter username.");
        }
        return userService.deleteUserByUsername(username);
    }

    @Override
    public Result<Integer> updateCurrentUser(User user) {

        if(user == null){
            return new Result<>(StatusCode.NOT_EXIST,"User not exist.");
        }
        String currentUsername = TokenDecoder.tokenUsername();
        user.setUsername(currentUsername);
        return userService.updateUserByUsername(currentUsername, user);
    }

    @Override
    public Result<Integer> updateUserByUsername(String username, User user){

        if(StringUtil.notExist(username) || user == null){
            return new Result<>(StatusCode.NOT_EXIST, "Please enter username.");
        }
        user.setUsername(username);
        return userService.updateUserByUsername(username, user);
    }

    @Override
    public Result<User> getCurrentUser() {

        return userService.getUserByUsername(TokenDecoder.tokenUsername());
    }

    @Override
    public Result<User> getUserByUsername(String username){

        if(StringUtil.notExist(username)){
            return new Result<>(StatusCode.NOT_EXIST, "Please enter username.");
        }
        return userService.getUserByUsername(username);
    }

    @Override
    public Result<List<User>> getAllUser() {

        return userService.getAllUser();
    }
}
