package com.bristol.project.service;

import com.bristol.project.entity.User;
import org.apache.ibatis.annotations.Param;

public interface UserService{

    int create(User user);

    User getUserByUsername(@Param("username") String username);

}
