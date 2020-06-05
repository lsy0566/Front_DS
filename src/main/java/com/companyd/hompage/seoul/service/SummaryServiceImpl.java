package com.companyd.hompage.seoul.service;

import com.companyd.hompage.seoul.entity.mongoDto.SummaryData;
import com.companyd.hompage.seoul.repository.SummaryDataRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Service;

import java.util.List;

@EnableMongoRepositories(basePackageClasses = SummaryDataRepo.class)
@Service
public class SummaryServiceImpl implements SummaryService {
    @Autowired
    SummaryDataRepo summaryDataRepo;

    @Override
    public List<SummaryData> getSummaryAll() {

        return summaryDataRepo.findAll();
    }

    @Override
    public SummaryData getSummaryByFileName(String fileName) {
        return summaryDataRepo.findByFileName(fileName);
    }
}
