package com.bristol.project.service;

import com.bristol.project.entity.Result;
import com.bristol.project.entity.User;
import org.apache.ibatis.annotations.Param;

public interface UserService{

    int NO_USERNAME   = -1;
    int CREATE_FAILED = -2;
    int ALREADY_EXIST = -3;

    Result<Integer> create(User user);

    User getUserByUsername(@Param("username") String username);

}
