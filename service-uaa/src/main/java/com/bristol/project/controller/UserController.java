package com.bristol.project.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class UserController {
    @RequestMapping("/authentication")
    public Principal authentication(Principal user){
        return user.;
    }
}
