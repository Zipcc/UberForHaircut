package com.bristol.project.openFeign;

import com.bristol.project.APIs.UserApi;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

//port:9100
@FeignClient(value = "service-user")
@RequestMapping(value = "feign/users")
public interface UserFeignApi extends UserApi {

    //@PostMapping("/login")
    //Result Login(@RequestParam("username") String username, @RequestParam("password") String password);

    //Register entry point
  /*  @PostMapping
    Result<User> create(@RequestBody User user);

    @PreAuthorize("hasAnyAuthority('admin')")
    @DeleteMapping ("/{username}")
    Result<Integer> deleteUserByUsername(@PathVariable("username") String username);

    @PreAuthorize("hasAnyAuthority('admin','barber','client')")
    @PutMapping("/me")
    Result<Integer> updateCurrentUser(@RequestBody User user);

    @PreAuthorize("hasAnyAuthority('admin')")
    @PutMapping("/{username}")
    Result<Integer> updateUserByUsername(@PathVariable("username") String username, @RequestBody User user);

    @PreAuthorize("hasAnyAuthority('admin','barber','client')")
    @GetMapping("/me")
    Result<User> getCurrentUser();

    @PreAuthorize("hasAnyAuthority('admin')")
    @GetMapping("/{username}")
    Result<User> getUserByUsername(@PathVariable("username") String username);

    @PreAuthorize("hasAnyAuthority('admin')")
    @GetMapping
    Result<List<User>> getAllUser();

   */
}
