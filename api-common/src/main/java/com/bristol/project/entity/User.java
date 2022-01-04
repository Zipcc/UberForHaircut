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
    private String sex;
    private String username;
    private String password;
    private String phoneNumber;
    private int role; // 0 Administrator
                      // 1 Client
                      // 2 Barber
}
