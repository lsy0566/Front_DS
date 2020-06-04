package com.companyd.hompage.seoul.service;

import com.companyd.hompage.seoul.entity.Logs;

import java.util.List;

public interface LogService {
    List<Logs> getAllLogs();
    Logs getLogById(int id);
    int createLog(Logs log);
    int modifyLog(Logs log);
    int removeLog(int id);
}
