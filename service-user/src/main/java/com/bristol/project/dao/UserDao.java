package com.bristol.project.dao;

import com.bristol.project.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserDao
{
    int create(User user);

    int updateUserByUsername(User user);

    User getUserByUsername(String username);

    List<User> getAllUser();
}
