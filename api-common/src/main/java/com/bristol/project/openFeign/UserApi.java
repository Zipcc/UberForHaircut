package com.bristol.project.openFeign;

import com.bristol.project.entity.Result;
import com.bristol.project.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/users")
@FeignClient(value = "service-user")
public interface UserApi {

    @PostMapping("/login")
    Result Login(@RequestParam("username") String username, @RequestParam("password") String password);

    @PostMapping
    Result<Integer> create(@RequestBody User user);

    @PutMapping("/{username}")
    Result<User> updateUserByUsername(@PathVariable("username") String username, @RequestBody User user);

    @GetMapping
    Result<List<User>> getAllUser();

    @GetMapping("/{username}")
    Result<User> getUserByUsername(@PathVariable("username") String username);
}
