package com.bristol.project.dao;

import com.bristol.project.entity.User;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface UserDao
{
    Long create(User user);

    int deleteUserByUsername(String username);

    int updateUserByUsername(User user);

    User getUserByUsername(String username);

    List<User> getAllUser();
}
