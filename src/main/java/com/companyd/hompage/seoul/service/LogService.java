package com.companyd.hompage.seoul.service;

import com.companyd.hompage.seoul.entity.Logs;

import java.util.List;

public interface LogService {
    int getColumnCount();
    List<Logs> getAllLogs();
    Logs getLogById(int id);
    Logs getLogByFileName(String fileName);
    List<Logs> getLogByFileNames(String fileName);
    int createLog(Logs log);
    int modifyLog(Logs log);
    int removeLog(int id);
}
