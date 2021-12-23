package com.bristol.project.controller;

import com.bristol.project.entity.Result;
import com.bristol.project.entity.User;
import com.bristol.project.openFeign.UserApi;
import com.bristol.project.service.UserService;

import javax.annotation.Resource;

public class UserController implements UserApi {

    @Resource
    private UserService userService;

    public Result<Integer> create(User user){

        System.out.println(user);

        if(user == null || user.getUsername() == null){
            return new Result<>(444, "Please enter username",UserService.NO_USERNAME);
        }

        return userService.create(user);
    }

    public Result getUserByUsername(String username){
        User user = userService.getUserByUsername(username);
        if (user == null){
            return new Result<>(555,"User -> " + username + " <- not exist.");
        }
        return new Result<>(200,"Find user -> " + username + " <- successfully!",user);
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
