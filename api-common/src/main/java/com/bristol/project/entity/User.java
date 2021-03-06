package com.bristol.project.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private Long userId;
    private int age;
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String phoneNumber;
    private String sex;
    private String username;
    private String password;
    private int role;
}
