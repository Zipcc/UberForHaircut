package com.bristol.project.openFeign;

import com.bristol.project.entity.Result;
import com.bristol.project.entity.User;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/users")
public interface UserApi {

    @PostMapping
    Result<Integer> create(@RequestBody(required = true) User user);

    @GetMapping("/{username}")
    Result getUserByUsername(@PathVariable("username") String username);
}
