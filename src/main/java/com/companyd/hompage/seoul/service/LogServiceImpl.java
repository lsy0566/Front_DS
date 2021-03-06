package com.companyd.hompage.seoul.service;

import com.companyd.hompage.seoul.entity.Logs;
import com.companyd.hompage.seoul.repository.LogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogServiceImpl implements LogService{
    @Autowired
    LogMapper mapper;

    @Override
    public int getColumnCount() {
        return mapper.columnCount();
    }

    @Override
    public List<Logs> getAllLogs() {
        return mapper.selectAllLogs();
    }

    @Override
    public Logs getLogById(int id) {

        return mapper.selectLogById(id);
    }

    @Override
    public Logs getLogByFileName(String fileName) {
        return mapper.selectLogByFileName(fileName);
    }

    @Override
    public List<Logs> getLogByFileNames(String fileName) {
        return mapper.selectLogByFileNames(fileName);
    }

    @Override
    public Logs getLogByResultFileName(String fileName) {
        return mapper.selectLogByResultFileName(fileName);
    }

    @Override
    public int createLog(Logs log) {
        mapper.insertLog(log);
        System.out.println("로그 생성쿼리 실행!");
        int isData = log.getId();
        System.out.println("생성된 로그 id값 : "+isData);
        return isData;
    }

    @Override
    public int modifyLog(Logs log) {
        return 0;
    }

    @Override
    public int removeLog(int id) {
        return 0;
    }

    @Override
    public List<Logs> getSuccessLogByUserName(String userName) {
        return mapper.selectSuccessLogByUserName(userName);
    }
}
