package com.bristol.project.controller;

import com.bristol.project.entity.Result;
import com.bristol.project.entity.User;
import com.bristol.project.service.UserService;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
public class UserController {

    @Resource
    private UserService userService;

    @Value("${server.port}")
    private String serverPort;

    @Resource
    private DiscoveryClient discoveryClient;

    @PostMapping(value = "/user/register")
    public Result<Integer> create(@RequestBody(required = true) User user){


        System.out.println(user);

        if(user == null || user.getUsername() == null){
            return new Result<>(444, "Please enter username",-1);
        }

        if(userService.getUserByUsername(user.getUsername()) == null){
            int result = userService.create(user);
            if (result > 0) {
                return new Result<>(200, "Register successfully!", result);
            }else{
                return new Result<>(444,"Failed to register",-1);
            }
        }else{
            return new Result<>(444,"Username -> " + user.getUsername() + " <- already exist.",-1);
        }

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
