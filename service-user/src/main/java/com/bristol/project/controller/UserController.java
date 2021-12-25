package com.bristol.project.controller;

import com.bristol.project.entity.Result;
import com.bristol.project.entity.User;
import com.bristol.project.openFeign.UserApi;
import com.bristol.project.service.UserService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class UserController implements UserApi {

    @GetMapping("/1")
    public String x(){
        return "1111no";
    }

    @Resource
    private UserService userService;

    public Result Login(String username, String password) {

        if(username == null || password == null){
            return new Result<>(444, "Please enter username and password.",UserService.NOT_EXIST);
        }

        return userService.login(username, password);
    }

    public Result<Integer> create(User user){

        System.out.println(user);

        if(user == null || user.getUsername() == null){
            return new Result<>(444, "Please enter username.",UserService.NOT_EXIST);
        }

        return userService.create(user);
    }

    public Result<User> getUserByUsername(String username){
        return userService.getUserByUsername(username);

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
