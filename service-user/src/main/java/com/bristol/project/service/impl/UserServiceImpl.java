package com.bristol.project.service.impl;

import cn.hutool.crypto.digest.BCrypt;
import com.bristol.project.dao.UserDao;
import com.bristol.project.entity.Result;
import com.bristol.project.entity.User;
import com.bristol.project.service.UserService;
import com.bristol.project.utils.StatusCode;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;

    //@Override
    public Result login(String username, String password, HttpServletResponse response) {

        User user = userDao.getUserByUsername(username);
        if(user == null){
            return new Result<>(StatusCode.NOT_EXIST, "User: " + username + " not exist!");
        }
        if(BCrypt.checkpw(password, user.getPassword())){

            Map<String,Object> tokenMap = new HashMap<>();
            tokenMap.put("id", user.getId());
            tokenMap.put("username", user.getUsername());
            tokenMap.put("role", user.getRole());
           // String token = Jwt.createJWT(UUID.randomUUID().toString(),JSONUtil.toJsonStr(tokenMap),null);

           // Cookie cookie = new Cookie("Authorization", token);
           // cookie.setDomain("localhost");
           // cookie.setPath("/");
           // response.addCookie(cookie);

            return new Result<>(StatusCode.OK, "User: " + username + " login successfully!");
        }
        return new Result(StatusCode.PASSWORD_WRONG, "Password is wrong.");
    }

    @Override
    public Result<Integer> create(User user) {

        if(userDao.getUserByUsername(user.getUsername()) == null){
            user.setPassword(BCrypt.hashpw(user.getPassword()));
            int userId = userDao.create(user);
            if (userId > 0) {
                //Success.
                return new Result<>(StatusCode.OK, "Register successfully!", userId);
            }else{
                //Create failed.
                return new Result<>(StatusCode.CREATE_FAILED,"Failed to register.");
            }
        }else{
            //Already exist.
            return new Result<>(StatusCode.ALREADY_EXIST,"Username: " + user.getUsername() + " already exists.");
        }
    }

    @Override
    public Result<Integer> deleteUserByUsername(String username) {

        if(userDao.getUserByUsername(username) == null) {
            return new Result<>(StatusCode.NOT_EXIST,"User: " + username + " not exists.");
        }else{
            int userId = userDao.deleteUserByUsername(username);
            if (userId > 0) {
                //Success.
                return new Result<>(StatusCode.OK, "Delete user successfully!", userId);
            }else{
                //Create failed.
                return new Result<>(StatusCode.CREATE_FAILED,"Failed to delete user.");
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
            //Success.
            return new Result<>(StatusCode.OK, "Update successfully!", result);
        }else{
            //Update failed.
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
        if(list == null){
            return new Result<>(StatusCode.NOT_EXIST,"User not exist.");
        }
        return new Result<>(StatusCode.OK, "Find " + list.size()+ " users", list);
    }
}
