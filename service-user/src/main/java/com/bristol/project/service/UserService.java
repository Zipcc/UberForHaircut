package com.bristol.project.service;

import com.bristol.project.entity.Result;
import com.bristol.project.entity.User;
import org.apache.ibatis.annotations.Param;

public interface UserService{

    int CREATE_FAILED   = -1;
    int NOT_EXIST = -2;
    int ALREADY_EXIST = -3;

    Result<Integer> create(User user);

    Result<User> getUserByUsername(@Param("username") String username);

}
