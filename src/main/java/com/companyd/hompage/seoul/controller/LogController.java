package com.companyd.hompage.seoul.controller;

import com.companyd.hompage.seoul.entity.Logs;
import com.companyd.hompage.seoul.entity.Users;
import com.companyd.hompage.seoul.service.LogService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    public ModelAndView getResultLog(Users user, HttpSession session, Model model){
        ModelAndView mav = new ModelAndView("mypageResultLog");
        mav.addObject(session.getAttribute("id"));

        List<Logs> LogsList = service.getSuccessLogByUserName((String)session.getAttribute("id"));
        mav.addObject("username",session.getAttribute("id"));

        //mav.addObject(list);
        mav.addObject("logList", LogsList);

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