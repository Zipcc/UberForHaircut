package com.bristol.project.dao;

import com.bristol.project.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserDao
{
    int create(User user);

    User getUserByUsername(@Param("username") String username);
}
