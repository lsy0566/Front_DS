package com.companyd.hompage.seoul.controller;

import com.companyd.hompage.seoul.entity.Users;
import com.companyd.hompage.seoul.exception.UserNotFoundException;
import com.companyd.hompage.seoul.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UsersController {
    @Autowired
    UserService service;

    @GetMapping("/users")
    public List<Users> getAllUsers() {
        List<Users> list = service.getAllUsers();
        return list;
    }

//  회원탈퇴
    @DeleteMapping("/delete/{id}")
    public int removeUser(@PathVariable int id) {
        int user = service.removeUser(id);
        if (user == 0 ) {
            throw new UserNotFoundException("id-" + id);
        }
        return user;
    }
}
