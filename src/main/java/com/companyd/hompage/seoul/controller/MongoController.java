package com.companyd.hompage.seoul.controller;

import com.companyd.hompage.seoul.entity.Users;
import com.companyd.hompage.seoul.entity.mongoDto.SummaryData;
import com.companyd.hompage.seoul.service.SummaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
public class MongoController {
    @Autowired
    SummaryService summaryService;

    @GetMapping("/getMongoDB")
    public List<SummaryData> getMongo() {
        return summaryService.getSummaryAll();
    }

    @GetMapping("/getData/{userName}")
    public List<SummaryData> getAllByUserName2(@PathVariable String userName) {
        return summaryService.getSummaryAllByUserName(userName);
    }

    // 파일리스트에서 컬럼 비식별처리버튼 전송
    @PostMapping("/updateMongoDB/{fileName}")
    public ModelAndView updateMongoDB(@PathVariable String fileName) throws Exception {
        SummaryData summaryData = summaryService.getSummaryByFileName(fileName);

        ModelAndView mav = new ModelAndView("mypageFilelist");

        mav.addObject("summaryList", summaryData);

        System.out.println(summaryData);
        return mav;
    }
}