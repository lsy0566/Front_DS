package com.companyd.hompage.seoul.controller;

import com.companyd.hompage.seoul.entity.Users;
import com.companyd.hompage.seoul.entity.mongoDto.Info;
import com.companyd.hompage.seoul.entity.mongoDto.Summary;
import com.companyd.hompage.seoul.entity.mongoDto.SummaryData;
import com.companyd.hompage.seoul.repository.SummaryDataRepo;
import com.companyd.hompage.seoul.service.SummaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

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
    // 파일리스트에서 컬럼 비식별처리버튼으로 update 시켜야함
    @PostMapping("/updateMongoDB/{fileName}")
    public ModelAndView updateMongoDB(@RequestBody String fileName, Users users) throws Exception {
        SummaryData summaryData = summaryService.getSummaryByFileName(fileName);

        ModelAndView mav = new ModelAndView("mypageFilelist");

        mav.addObject("summaryList", summaryData);

        System.out.println(summaryData);
        return mav;
    }

    // SummaryData update 쿼리
    @CrossOrigin("*")
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
        // RestTemplate 처리
        ModelAndView mav = new ModelAndView("mypageFilelist");

        // RestTemplate이용 데이터 받아오는 곳
        HashMap<String, Object> result = new HashMap<String, Object>();
        // 비식별화 처리
        String url = "http://dei-app-service:8081/deidentifier/"+ fileName;
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
        factory.setConnectTimeout(5000); //타임아웃 설정 5초
        factory.setReadTimeout(5000);//타임아웃 설정 5초
        RestTemplate restTemplate = new RestTemplate(factory);

        HttpHeaders headers = new HttpHeaders();
        HttpEntity<?> entity = new HttpEntity<>(headers);

        restTemplate.exchange(url, HttpMethod.GET, entity, Map.class);
        /* 응답 결과 받아오는 부분
        ResponseEntity<Map> resultMap = restTemplate.exchange(url, HttpMethod.GET, entity, Map.class);
        result.put("statusCode", resultMap.getStatusCodeValue());
        result.put("header", resultMap.getHeaders());
        result.put("body", resultMap.getBody());
        System.out.println(result.get("statusCode"));
        System.out.println(result.get("header"));
        System.out.println(result.get("body").toString());
        */

        // model and view 만들어서 리턴
        return mav;
    }

    // test 하는 부분
    @CrossOrigin("*")
    @GetMapping("/testTransaction")
    public String transaction(){
        HashMap<String, Object> result = new HashMap<String, Object>();

        String url = "http://dei-app-service:8081/getSummary/police.csv";
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
        factory.setConnectTimeout(5000); //타임아웃 설정 5초
        factory.setReadTimeout(5000);//타임아웃 설정 5초
        RestTemplate restTemplate = new RestTemplate(factory);

        HttpHeaders headers = new HttpHeaders();
        HttpEntity<?> entity = new HttpEntity<>(headers);

        ResponseEntity<Map> resultMap = restTemplate.exchange(url, HttpMethod.GET, entity, Map.class);
        result.put("statusCode", resultMap.getStatusCodeValue());
        result.put("header", resultMap.getHeaders());
        result.put("body", resultMap.getBody());
        System.out.println(result.get("statusCode"));
        System.out.println(result.get("header"));
        System.out.println(result.get("body").toString());

        return result.get("body").toString();
    }
}

