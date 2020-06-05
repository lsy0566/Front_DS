package com.companyd.hompage.seoul.controller;

import com.companyd.hompage.seoul.entity.mongoDto.SummaryData;
import com.companyd.hompage.seoul.service.SummaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MongoController {
    @Autowired
    SummaryService summaryService;
    @GetMapping("/getMongoDB")
    public List<SummaryData> getMongo(){
        return summaryService.getSummaryAll();
    }

    @GetMapping("/getData/{userName}")
    public List<SummaryData> getAllByUserName2(@PathVariable String userName){
        return summaryService.getSummaryAllByUserName(userName);
    }
}
