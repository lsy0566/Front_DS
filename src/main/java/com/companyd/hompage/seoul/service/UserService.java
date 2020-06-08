package com.companyd.hompage.seoul.service;

import com.companyd.hompage.seoul.entity.Users;
import org.springframework.validation.Errors;

import java.util.List;
import java.util.Map;

public interface UserService{
    List<Users> getAllUsers();
    Users getUserById(int id);
    int createUser(Users user);
    int modifyUser(Users user);
    int removeUser(int id);
    Users getLogin(Users user) throws Exception;
    // 유효성 검사 핸들링
    Map<String, String> validateHandling(Errors errors);
}
