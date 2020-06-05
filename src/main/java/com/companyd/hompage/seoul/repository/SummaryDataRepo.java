package com.companyd.hompage.seoul.repository;

import com.companyd.hompage.seoul.entity.mongoDto.SummaryData;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface SummaryDataRepo extends MongoRepository<SummaryData, String> {
    SummaryData findByFileName(String fileName);
    List<SummaryData> findByUserName(String userName);
}
