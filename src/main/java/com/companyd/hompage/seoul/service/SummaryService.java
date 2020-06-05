package com.companyd.hompage.seoul.service;

import com.companyd.hompage.seoul.entity.mongoDto.SummaryData;

import java.util.List;

public interface SummaryService {
    List<SummaryData> getSummaryAll();
    SummaryData getSummaryByFileName(String fileName);
    List<SummaryData> getSummaryAllByUserName(String userName);
}
