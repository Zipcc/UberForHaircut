package com.bristol.project.service;

import com.bristol.project.entity.Result;
import com.bristol.project.entity.User;
import org.apache.ibatis.annotations.Param;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface UserService{

    //Result login(String username, String password, HttpServletResponse response);

    Result<Integer> create(User user);

    Result<User> updateUserByUsername(String username, User user);

    Result<User> getUserByUsername(String username);

    Result<List<User>> getAllUser();
}
