package com.companyd.hompage.seoul.controller;

import com.companyd.hompage.seoul.entity.Users;
import com.companyd.hompage.seoul.entity.mongoDto.Info;
import com.companyd.hompage.seoul.entity.mongoDto.Summary;
import com.companyd.hompage.seoul.entity.mongoDto.SummaryData;
import com.companyd.hompage.seoul.repository.SummaryDataRepo;
import com.companyd.hompage.seoul.service.SummaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.LinkedList;
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

    // SummaryData update 쿼리
    @PostMapping("/updateSummaryData/{fileName}")
    public ModelAndView updateSummaryData(@PathVariable String fileName, HttpServletRequest request){
        System.out.println(fileName);
        Enumeration<String> em = request.getParameterNames();
        List<Summary> summaryList = new LinkedList<Summary>();
        Summary newSummary;
        int i = 0;
        String dataTypeValue = "";
        String deIdentifiedValue = "";
        String proveValue = "";
        while (em.hasMoreElements()) {
            String name = em.nextElement();
            int pos = name.lastIndexOf("_") + 1; // dataType_1 ->
            String idx = name.substring(pos); // idx -> 1
            if(i % 3 == 0){
                dataTypeValue = request.getParameter("dataType_" + idx);
                // System.out.println("dataTypeValue : " + dataTypeValue);
            }else if(i%3 == 1){
                deIdentifiedValue = request.getParameter("deIdentified_" + idx);
                // System.out.println("deIdentifiedValue : " + deIdentifiedValue);
            }else{
                proveValue = request.getParameter("prove_" + idx);
                // System.out.println("proveValue : " + proveValue);
                newSummary = new Summary();
                newSummary.setProve(proveValue);
                newSummary.setDataType(dataTypeValue);
                newSummary.setDeIdentified(deIdentifiedValue);
                summaryList.add(newSummary);
            }
            i++;
        }
        // System.out.println("end!");
        SummaryData summaryData = summaryService.getSummaryByFileName(fileName);
        List<Info> infos = summaryData.getInfo();
        for(int num = 0; num<infos.size(); num++){
            infos.get(num).setSummary(summaryList.get(num));
        }
        summaryData.setInfo(infos);
        summaryService.updateSummaryData(summaryData);
        ModelAndView mav = new ModelAndView("mypageFilelist");
        // model and view 만들어서 리턴
        return mav;
    }
}

