package com.companyd.hompage.seoul.controller;

import com.companyd.hompage.seoul.entity.LoginResponseData;
import com.companyd.hompage.seoul.entity.SignUpResponseData;
import com.companyd.hompage.seoul.entity.Users;
import com.companyd.hompage.seoul.exception.UserNotFoundException;
import com.companyd.hompage.seoul.service.UserService;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

    @GetMapping("/users/{id}")
    public Users getUsersById(@PathVariable int id) {
        Users user = service.getUserById(id);
        if (user == null ) {
            throw new UserNotFoundException("id-" + id);
        }
        return user;
    }

////  회원가입
//    @RequestMapping(value = "/user/signup", method = RequestMethod.POST)
//    public Users createUser(@Valid @RequestBody Users user){
//        System.out.println("회원 가입 정보 등록");
//        SignUpResponseData res = new SignUpResponseData();
//        String hashPassword = BCrypt.hashpw(user.getPassword(),BCrypt.gensalt());
//        user.setPassword(hashPassword); //암호화 저장
//        int createdUser = service.createUser(user);
//
//        if(createdUser >= 1){ // xml파일에다 id값 return받기로함
//            res.setIsSucceed(1);
//        }else{
//            res.setIsSucceed(0);
//        }
//        Users GetUser = service.getUserById(createdUser);
//        return GetUser;
//    }

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
