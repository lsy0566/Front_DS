package com.companyd.hompage.seoul.service;

import com.companyd.hompage.seoul.entity.Users;

import java.util.List;

public interface UserService{
    List<Users> getAllUsers();
    Users getUserById(int id);
    int createUser(Users user);
    int modifyUser(Users user);
    int removeUser(int id);
    Users getLogin(Users user) throws Exception;
}
