package com.companyd.hompage.seoul.controller;

import com.companyd.hompage.seoul.entity.Logs;
import com.companyd.hompage.seoul.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class LogController {
    @Autowired
    LogService service;

    //모든 로그 목록
    @GetMapping("/logs")
    public List<Logs> getAllLogs() {
        List<Logs> list = service.getAllLogs();
        return list;
    }

    //특정 로그 정보
    @GetMapping("/log/{id}")
    public Logs getLogById(@PathVariable int id){
        Logs log = service.getLogById(id);
        return log;
    }

    //로그 생성
    @PostMapping("/log/enroll")
    public int createlog(@Valid @RequestBody Logs log) {
        System.out.println("로그 정보 등록");
        int createdLog = service.createLog(log);

//        if(createdLog >= 1){ // xml파일에다 id값 return받기로함
//            res.setIsSucceed(1);
//        }else{
//            res.setIsSucceed(0);
//        }
        return createdLog;
    }

    // 로그 삭제
    @DeleteMapping("/log/delete/{id}")
    public int removeLog(@PathVariable int id) {
        int log = service.removeLog(id);
        return log;
    }
}