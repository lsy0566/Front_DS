package com.companyd.hompage.seoul.controller;

import com.companyd.hompage.seoul.entity.LoginResponseData;
import com.companyd.hompage.seoul.entity.Users;
import com.companyd.hompage.seoul.service.UserService;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class LoginController {
    @Autowired
    UserService service;

//    public boolean isMatch(String password, String hashed){
//        System.out.println("password: "+ password+ " hashed: "+hashed);
//        System.out.println("isMatch 메서드 checkpw(): "+ BCrypt.checkpw(password,hashed)); // true || false
//        return BCrypt.checkpw(password,hashed);
//    }

//    @PostMapping("/login")
//    public LoginResponseData getLogin(@Valid @RequestBody Users user) {
//        Users login = service.getLogin(user);
//        LoginResponseData res = new LoginResponseData();
//
//        System.out.println("넣은 비밀번호 : " + user.getPassword());
//        System.out.println("가져온 비밀번호 : " + login.getPassword());
//        if (isMatch(user.getPassword(),login.getPassword())) {
//            res.setIsSucceed(1);
//            System.out.println("로그인 성공");
//        } else if (!isMatch(user.getPassword(),login.getPassword())) {
//            res.setIsSucceed(0);
//            System.out.println("비번이 서로 달라 로그인 실패");
//        }
//        return res;
//    }

}