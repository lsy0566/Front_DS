package com.companyd.hompage.seoul.repository;


import com.companyd.hompage.seoul.entity.Users;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
// test
@Mapper
@Repository
public interface UserMapper {
    List<Users> selectAllUsers();
    Users selectUserById(int id);
    int insertUser(Users user);//return 0 or 1
    int updateUser(Users user); //return update row 수
    int deleteUser(int id);//return delete row 수
    Users login(Users user);
}
