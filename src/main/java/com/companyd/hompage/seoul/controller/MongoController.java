package com.companyd.hompage.seoul.controller;

import com.companyd.hompage.seoul.entity.Users;
import com.companyd.hompage.seoul.entity.mongoDto.SummaryData;
import com.companyd.hompage.seoul.repository.SummaryDataRepo;
import com.companyd.hompage.seoul.service.SummaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
public class MongoController {
    @Autowired
    SummaryService summaryService;

    @Autowired
    SummaryDataRepo summaryDataRepo;

    @GetMapping("/getMongoDB")
    public List<SummaryData> getMongo() {
        return summaryService.getSummaryAll();
    }

    @GetMapping("/getData/{userName}")
    public List<SummaryData> getAllByUserName2(@PathVariable String userName) {
        return summaryService.getSummaryAllByUserName(userName);
    }


    @PostMapping("/testChangeData")
    public SummaryData testSummaryData(@RequestBody SummaryData summaryData) {
        SummaryData newData = summaryService.getSummaryByFileName(summaryData.getFileName());
        newData.setInfo(summaryData.getInfo());
        summaryDataRepo.save(newData);
        SummaryData checkData = summaryService.getSummaryByFileName(newData.getFileName());
        return checkData;
    }
}

    // 파일리스트에서 컬럼 비식별처리버튼으로 update 시켜야함
    @PostMapping("/updateMongoDB/{fileName}")
//    public ModelAndView updateMongoDB(@PathVariable String fileName, Users users) throws Exception {
    public ModelAndView updateMongoDB(@RequestBody String fileName, Users users) throws Exception {
        SummaryData summaryData = summaryService.getSummaryByFileName(fileName);

        ModelAndView mav = new ModelAndView("mypageFilelist");

        mav.addObject("summaryList", summaryData);

        System.out.println(summaryData);
        return mav;
    }
}

