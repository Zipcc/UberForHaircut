package com.bristol.project.service;

import com.bristol.project.entity.Result;
import com.bristol.project.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.http.server.ServerHttpResponse;

import javax.servlet.http.HttpServletResponse;

public interface UserService{

    Result login(String username, String password, HttpServletResponse response);

    Result<Integer> create(User user);

    Result<User> getUserByUsername(@Param("username") String username);

}
