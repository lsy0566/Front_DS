package com.companyd.hompage.seoul.repository;

import com.companyd.hompage.seoul.entity.Logs;
import com.companyd.hompage.seoul.entity.mongoDto.SummaryData;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface SummaryDataRepo extends MongoRepository<SummaryData, String> {
    SummaryData findByFileName(String fileName);
    List<SummaryData> findByUserName(String userName);

    @Mapper
    @Repository
    interface LogMapper {
        int columnCount();
        List<Logs> selectAllLogs();
        Logs selectLogById(int id);
        Logs selectLogByFileName(String fileName);
        Logs selectLogByFileNames(String fileName);
        String selectLogByUserNames(String userName);
        int insertLog(Logs log);//return 0 or 1
        int updateLog(Logs log); //return update row 수
        int deleteLog(int id);//return delete row 수
    }
}
