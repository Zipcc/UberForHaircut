package com.bristol.project.service.impl;

import cn.hutool.crypto.digest.BCrypt;
import com.bristol.project.dao.UserDao;
import com.bristol.project.entity.Result;
import com.bristol.project.entity.User;
import com.bristol.project.service.UserService;
import com.bristol.project.entity.StatusCode;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;

    @Override
    public Result<User> create(User user) {

        //new username
        if(userDao.getUserByUsername(user.getUsername()) == null){
            user.setPassword(BCrypt.hashpw(user.getPassword()));
            userDao.create(user);
            Long userId = user.getUserId();
            if (userId != null) {
                //success
                return new Result<>(StatusCode.OK, "Register successfully!",user);
            }else{
                //create failed
                return new Result<>(StatusCode.CREATE_FAILED,"Failed to register.");
            }
        }else{
            //username already exist
            return new Result<>(StatusCode.ALREADY_EXIST,"Username: " + user.getUsername() + " already exists.");
        }
    }

    @Override
    public Result<Integer> deleteUserByUsername(String username) {

        if(userDao.getUserByUsername(username) == null) {
            return new Result<>(StatusCode.NOT_EXIST,"User: " + username + " not exists.");
        }else{
            int result = userDao.deleteUserByUsername(username);
            if (result > 0) {
                //success
                return new Result<>(StatusCode.OK, "Delete user successfully!");
            }else{
                //create failed
                return new Result<>(StatusCode.ERROR,"Failed to delete user.");
            }
        }
    }

    @Override
    public Result<Integer> updateUserByUsername(String username, User user) {

        User oldUser = userDao.getUserByUsername(username);
        if(oldUser == null){
            return new Result<>(StatusCode.NOT_EXIST,"User not exist.");
        }
        if(user.getPassword()==null){
            user.setPassword(oldUser.getPassword());
        }
        user.setPassword(BCrypt.hashpw(user.getPassword()));
        if(user.getAge()==0){
            user.setAge(oldUser.getAge());
        }
        if(user.getFirstName()==null){
            user.setFirstName(oldUser.getFirstName());
        }
        if(user.getLastName()==null){
            user.setLastName(oldUser.getLastName());
        }
        if(user.getEmailAddress()==null){
            user.setEmailAddress(oldUser.getEmailAddress());
        }
        if(user.getPhoneNumber()==null){
            user.setPhoneNumber(oldUser.getPhoneNumber());
        }
        int result = userDao.updateUserByUsername(user);
        if (result > 0) {
            //success
            return new Result<>(StatusCode.OK, "Update successfully!");
        }else{
            //update failed
            return new Result<>(StatusCode.CREATE_FAILED,"Failed to update.");
        }
    }

    @Override
    public Result<User> getUserByUsername(String username) {

        User user = userDao.getUserByUsername(username);
        if (user == null){
            return new Result<>(StatusCode.NOT_EXIST,"User: " + username + " not exist.");
        }
        return new Result<>(StatusCode.OK,"Find user: " + username + " successfully!", user);
    }

    @Override
    public Result<List<User>> getAllUser() {

        List<User> list;
        list = userDao.getAllUser();
        if(list == null || list.size() == 0){
            return new Result<>(StatusCode.NOT_EXIST,"User not exist.");
        }
        return new Result<>(StatusCode.OK, "Find " + list.size()+ " users", list);
    }
}
