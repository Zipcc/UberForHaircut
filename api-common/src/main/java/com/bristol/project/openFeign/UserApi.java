package com.bristol.project.openFeign;

import com.bristol.project.entity.Result;
import com.bristol.project.entity.User;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/users")
public interface UserApi {

    @PostMapping("/login")
    Result Login(@RequestParam String username, @RequestParam String password);

    @PostMapping
    Result<Integer> create(@RequestBody User user);

    @GetMapping("/{username}")
    Result<User> getUserByUsername(@PathVariable("username") String username);
}
