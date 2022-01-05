package com.bristol.project.APIs;

import com.bristol.project.entity.Result;
import com.bristol.project.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//port:9100
//@FeignClient(value = "service-user", path = "/users")
@RequestMapping(value = "/users")
public interface UserApi {

    //@PostMapping("/login")
    //Result Login(@RequestParam("username") String username, @RequestParam("password") String password);

    //Register entry point
    @PostMapping
    Result<User> create(@RequestBody User user);

    @PreAuthorize("hasAnyAuthority('admin')")
    @DeleteMapping (value = "/{username}")
    Result<Integer> deleteUserByUsername(@PathVariable("username") String username);

    @PreAuthorize("hasAnyAuthority('admin','barber','client')")
    @PutMapping(value = "/me")
    Result<Integer> updateCurrentUser(@RequestBody User user);

    @PreAuthorize("hasAnyAuthority('admin')")
    @PutMapping(value = "/{username}")
    Result<Integer> updateUserByUsername(@PathVariable("username") String username, @RequestBody User user);

    @PreAuthorize("hasAnyAuthority('admin','barber','client')")
    @GetMapping(value = "/me")
    Result<User> getCurrentUser();

    @PreAuthorize("hasAnyAuthority('admin')")
    @GetMapping(value = "/{username}")
    Result<User> getUserByUsername(@PathVariable("username") String username);

    @PreAuthorize("hasAnyAuthority('admin')")
    @GetMapping
    Result<List<User>> getAllUser();
}
