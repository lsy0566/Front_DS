package com.companyd.hompage.seoul.repository;

import com.companyd.hompage.seoul.entity.Logs;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface LogMapper {
    int columnCount();
    List<Logs> selectAllLogs();
    Logs selectLogById(int id);
    Logs selectLogByFileName(String fileName);
    Logs selectLogByResultFileName(String fileName);
    List<Logs> selectLogByFileNames(String fileName);

    List<Logs> selectLogByUserName(String userName);
    List<Logs> selectSuccessLogByUserName(String userName);

    int insertLog(Logs log);//return 0 or 1
    int updateLog(Logs log); //return update row 수
    int deleteLog(int id);//return delete row 수
}