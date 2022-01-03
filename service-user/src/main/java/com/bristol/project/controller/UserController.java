package com.bristol.project.controller;

import com.bristol.project.entity.Result;
import com.bristol.project.entity.User;
import com.bristol.project.openFeign.UserApi;
import com.bristol.project.service.UserService;
import com.bristol.project.utils.StatusCode;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
public class UserController implements UserApi {

    @Resource
    private UserService userService;

    @Resource
    private HttpServletResponse response;

    @Override
    public Result Login(String username, String password) {

        if(username == null || password == null){
            return new Result<>(StatusCode.NOT_EXIST, "Please enter username and password.");
        }
        return null;
        //return userService.login(username, password, response);
    }

    @Override
    public Result<Integer> create(User user){

        if(user == null || user.getUsername() == null || user.getUsername().trim().isEmpty()){
            return new Result<>(StatusCode.NOT_EXIST, "Please enter username.");
        }
        return userService.create(user);
    }

    @PreAuthorize("hasAnyAuthority('admin','client','barber')")
    @Override
    public Result<User> updateUserByUsername(String username, User user){

        if(username == null || user == null){
            return new Result<>(StatusCode.NOT_EXIST,"User not exist.");
        }
        return userService.updateUserByUsername(username, user);
    }

    @PreAuthorize("hasAnyAuthority('admin','client','barber')")
    @Override
    public Result<User> getUserByUsername(String username){

        return userService.getUserByUsername(username);
    }

    @PreAuthorize("hasAuthority('admin')")
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
