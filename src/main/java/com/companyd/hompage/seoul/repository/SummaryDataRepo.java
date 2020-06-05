package com.companyd.hompage.seoul.repository;

import com.companyd.hompage.seoul.entity.mongoDto.SummaryData;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SummaryDataRepo extends MongoRepository<SummaryData, String> {
    SummaryData findByFileName(String fileName);
}
