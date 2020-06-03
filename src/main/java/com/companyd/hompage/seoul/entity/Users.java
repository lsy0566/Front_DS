package com.companyd.hompage.seoul.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Users {
    private int id;
    private String email;
    private String password;
    private String username;
    private String phoneNumber;
    private int isMember;
    private int admin;
}
