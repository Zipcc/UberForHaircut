package com.bristol.project.service;

import com.bristol.project.entity.Result;
import com.bristol.project.entity.User;
import java.util.List;

public interface UserService{

    //Result login(String username, String password, HttpServletResponse response);

    Result<User> create(User user);

    Result<Integer> deleteUserByUsername(String username);

    Result<Integer> updateUserByUsername(String username, User user);

    Result<User> getUserByUsername(String username);

    Result<List<User>> getAllUser();
}
