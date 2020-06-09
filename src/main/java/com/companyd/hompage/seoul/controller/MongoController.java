package com.companyd.hompage.seoul.controller;

import com.companyd.hompage.seoul.entity.mongoDto.SummaryData;
import com.companyd.hompage.seoul.repository.SummaryDataRepo;
import com.companyd.hompage.seoul.service.SummaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MongoController {
    @Autowired
    SummaryService summaryService;
    @Autowired
    SummaryDataRepo summaryDataRepo;
    @GetMapping("/getMongoDB")
    public List<SummaryData> getMongo(){
        return summaryService.getSummaryAll();
    }

    @GetMapping("/getData/{userName}")
    public List<SummaryData> getAllByUserName2(@PathVariable String userName){
        return summaryService.getSummaryAllByUserName(userName);
    }

    @PostMapping("/testChangeData")
    public SummaryData testSummaryData(@RequestBody SummaryData summaryData){
        SummaryData newData = summaryService.getSummaryByFileName(summaryData.getFileName());
        newData.setInfo(summaryData.getInfo());
        summaryDataRepo.save(newData);
        SummaryData checkData = summaryService.getSummaryByFileName(newData.getFileName());
        return checkData;
    }
}
