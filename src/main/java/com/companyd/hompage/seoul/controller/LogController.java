package com.companyd.hompage.seoul.controller;

import com.companyd.hompage.seoul.entity.Logs;
import com.companyd.hompage.seoul.entity.Users;
import com.companyd.hompage.seoul.service.LogService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@Controller
@AllArgsConstructor
public class LogController {
    @Autowired
    LogService service;

    //모든 로그 목록
    @GetMapping("/logs")
    public List<Logs> getAllLogs() {
        List<Logs> list = service.getAllLogs();
        return list;
    }

    //로그인한 username의 로그 정보
    @GetMapping("/mypageResultLog")
    public ModelAndView getResultLog(Users user, HttpSession session){
        List<Logs> list = service.getAllLogs();
        String username = session.getId();

        Logs log = service.getLogByUserNames(username);
        ModelAndView mav = new ModelAndView("mypageResultLog");
        mav.addObject(session.getAttribute("id"));
        mav.addObject("username", session.getAttribute("id"));
        if (true){
            //mav.addObject(list);
            mav.addObject("id", log.getId());
            mav.addObject("user_name", log.getUser_name());
            mav.addObject("created_date", log.getCreated_date());
            mav.addObject("download_cnd", log.getDownload_cnt());
            mav.addObject("download_date", log.getDownload_date());
            mav.addObject("is_succeed", log.getIs_succeed());
            mav.addObject("origin_location", log.getOrigin_location());
            mav.addObject("result_location", log.getResult_location());

            mav.addObject("");
        }
        return mav;
    }

    //로그 생성
    @PostMapping("/log/enroll")
    public int createlog(@Valid @RequestBody Logs log) {
        System.out.println("로그 정보 등록");
        int createdLog = service.createLog(log);
        return createdLog;
    }

    // 로그 삭제
    @DeleteMapping("/log/delete/{id}")
    public int removeLog(@PathVariable int id) {
        int log = service.removeLog(id);
        return log;
    }
}