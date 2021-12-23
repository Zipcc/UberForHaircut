package com.bristol.project.service.impl;

import com.bristol.project.dao.UserDao;
import com.bristol.project.entity.Result;
import com.bristol.project.entity.User;
import com.bristol.project.service.UserService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;

    @Override
    public Result<Integer> create(User user) {

        if(userDao.getUserByUsername(user.getUsername()) == null){

            int userId = userDao.create(user);
            if (userId > 0) {
                //Success.
                return new Result<>(200, "Register successfully!", userId);
            }else{
                //Create failed.
                return new Result<>(444,"Failed to register.", CREATE_FAILED);
            }
        }else{
            //Already exist.
            return new Result<>(444,"Username -> " + user.getUsername() + " <- already exists.", ALREADY_EXIST);
        }

    }

    @Override
    public User getUserByUsername(String username) {
        return userDao.getUserByUsername(username);
    }
}
