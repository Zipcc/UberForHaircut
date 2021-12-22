package com.bristol.project.service.impl;

import com.bristol.project.dao.UserDao;
import com.bristol.project.entity.User;
import com.bristol.project.service.UserService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;

    @Override
    public int create(User user) {
        return userDao.create(user);
    }

    @Override
    public User getUserByUsername(String username) {
        return userDao.getUserByUsername(username);
    }
}
