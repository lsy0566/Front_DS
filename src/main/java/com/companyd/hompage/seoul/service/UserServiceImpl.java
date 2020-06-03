package com.companyd.hompage.seoul.service;

import com.companyd.hompage.seoul.entity.Users;
import com.companyd.hompage.seoul.repository.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    UserMapper mapper;

    @Override
    public List<Users> getAllUsers() {

        return mapper.selectAllUsers();

    }

    @Override
    public Users getUserById(int id) {

        return mapper.selectUserById(id);

    }

    @Override // public int createUser(Users user)
    public int createUser(Users user) {
        mapper.insertUser(user);
        System.out.println("생성쿼리 실행!");
        int idData = user.getId();
//        String passwordData = user.getPassword();
        System.out.println("생성된 id 값 : " + idData);
//        System.out.println("생성된 pw 값 : " + passwordData);
//
//        user.setPassword(passwordEncoder.encode(passwordData));
//        System.out.println("encode pw 값 : " + user.getPassword());

        return idData;

    }

    @Override
    public int modifyUser(Users user) {
        return mapper.updateUser(user);
        // update -> delete -> insert
        // mapper.deleteUser(user.getUserId());
        // mapper.insertUser(user);
    }

    @Override
    public int removeUser(int id) {

        return mapper.deleteUser(id);

    }

    @Override
    public Users getLogin(Users user){
        return mapper.login(user);//조회된 값이 담기게 이메일이랑 패스워드
    }
}
