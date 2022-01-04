package com.bristol.project.controller;

import com.bristol.project.entity.Result;
import com.bristol.project.entity.User;
import com.bristol.project.openFeign.UserApi;
import com.bristol.project.service.UserService;
import com.bristol.project.utils.StatusCode;
import com.bristol.project.utils.TokenDecoder;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController implements UserApi {

    @Resource
    private UserService userService;


/*  @Override
    public Result Login(String username, String password) {

        if(username == null || password == null){
            return new Result<>(StatusCode.NOT_EXIST, "Please enter username and password.");
        }
        return null;
        //return userService.login(username, password, response);
    }
*/
    @Override
    public Result<User> create(User user){

        if(user == null || user.getUsername() == null || user.getUsername().trim().isEmpty() || user.getPassword().trim().isEmpty()){
            return new Result<>(StatusCode.NOT_EXIST, "Please enter username.");
        }
        return userService.create(user);
    }

    @Override
    public Result<Integer> deleteUserByUsername(String username) {

        if(username == null || username.trim().isEmpty()){
            return new Result<>(StatusCode.NOT_EXIST, "Please enter username.");
        }
        return userService.deleteUserByUsername(username);
    }

    @Override
    public Result<Integer> updateCurrentUser(User user) {

        if(user == null){
            return new Result<>(StatusCode.NOT_EXIST,"User not exist.");
        }
        String currentUsername = TokenDecoder.tokenUsername();
        user.setUsername(currentUsername);
        return userService.updateUserByUsername(currentUsername, user);
    }

    @Override
    public Result<Integer> updateUserByUsername(String username, User user){

        if(username == null || username.trim().isEmpty() || user == null){
            return new Result<>(StatusCode.NOT_EXIST, "Please enter username.");
        }
        user.setUsername(username);
        return userService.updateUserByUsername(username, user);
    }

    @Override
    public Result<User> getCurrentUser() {

        return userService.getUserByUsername(TokenDecoder.tokenUsername());
    }

    @Override
    public Result<User> getUserByUsername(String username){

        if(username == null || username.trim().isEmpty()){
            return new Result<>(StatusCode.NOT_EXIST, "Please enter username.");
        }
        return userService.getUserByUsername(username);
    }

    @Override
    public Result<List<User>> getAllUser() {

        return userService.getAllUser();
    }



 /*   @GetMapping(value = "/payments/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id){

        Payment payment = userService.getPaymentById(id);

        if (payment != null) {
            return new CommonResult(200,"Successfully found data --" + serverPort,payment);
        }else{
            return new CommonResult(444,"Failed to find data",null);
        }
    }

    @GetMapping(value = "/payments/discovery")
    public Object discovery(){
        List<String> services = discoveryClient.getServices();
        return services.get(0);
    }
    */
}
