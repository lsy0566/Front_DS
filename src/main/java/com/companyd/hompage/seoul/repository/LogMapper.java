package com.companyd.hompage.seoul.repository;

import com.companyd.hompage.seoul.entity.Logs;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface LogMapper {
    List<Logs> selectAllLogs();
    Logs selectLogById(int id);
    int insertLog(Logs log);//return 0 or 1
    int updateLog(Logs log); //return update row 수
    int deleteLog(int id);//return delete row 수
}
