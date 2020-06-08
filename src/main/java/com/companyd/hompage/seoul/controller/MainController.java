package com.companyd.hompage.seoul.controller;

import com.companyd.hompage.seoul.entity.LoginResponseData;
import com.companyd.hompage.seoul.entity.Logs;
import com.companyd.hompage.seoul.entity.SignUpResponseData;
import com.companyd.hompage.seoul.entity.Users;
import com.companyd.hompage.seoul.entity.mongoDto.SummaryData;
import com.companyd.hompage.seoul.exception.UserNotFoundException;
import com.companyd.hompage.seoul.service.SummaryService;
import com.companyd.hompage.seoul.service.UserService;
import lombok.AllArgsConstructor;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;


import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@AllArgsConstructor
public class MainController {
    @Autowired
    UserService service;

    @Autowired
    SummaryService summaryService;

    public boolean isMatch(String password, String hashed) {
        System.out.println("password: " + password + " hashed: " + hashed);
        System.out.println("isMatch 메서드 checkpw(): " + BCrypt.checkpw(password, hashed)); // true || false
        return BCrypt.checkpw(password, hashed);
    }

    // 메인페이지
    @GetMapping("/")
//    @PostMapping("/")
    public String index(HttpSession session) {

        return "index";
    }

    // 회원가입 페이지
    @GetMapping("/user/signup")
    public String dispSignup() {

        return "/userRegister";
    }

    //회원 가입 정보 입력
    @PostMapping("/user/signup")
    public ModelAndView createUser(@Valid Users user, Errors errors) {
        ModelAndView mav = new ModelAndView("/index");

        if (errors.hasErrors()){
            mav.addObject("user", user);

            // 유효성 통과 못한 필드 메시지 핸들링
            Map<String, String> validatorResult = service.validateHandling(errors);
            for (String key : validatorResult.keySet()) {
                mav.addObject(key, validatorResult.get(key));
            }

            mav.setViewName("/userRegister");
            return mav;
        }

        System.out.println("회원 가입 정보 등록");
        SignUpResponseData res = new SignUpResponseData();
        String hashPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
        user.setPassword(hashPassword); //암호화 저장
        int createdUser = service.createUser(user);

        if (createdUser >= 1) { // xml파일에다 id값 return받기로함
            res.setIsSucceed(1);
        } else {
            res.setIsSucceed(0);
        }
        Users GetUser = service.getUserById(createdUser);
        return mav;
    }

    // 로그인 페이지
    @GetMapping("/user/login")
    public String dispLogin() {
        return "/userLogin";
    }

    //로그아웃 후 로그인 페이지로 이동 => 세션파기
    @GetMapping("/user/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "/userLogin";
    }

    //로그인 처리 부분
    @PostMapping("/user/login")
    public ModelAndView getLogin(@Valid Users user, HttpSession session) throws Exception {
        if (user == null) {
            throw new UserNotFoundException("id-" + user.getId());
        }
        ModelAndView mav = new ModelAndView();

        Users login = service.getLogin(user);
        LoginResponseData res = new LoginResponseData();

        System.out.println("넣은 비밀번호 : " + user.getPassword());
        System.out.println("가져온 비밀번호 : " + login.getPassword());
        if (isMatch(user.getPassword(), login.getPassword())) {
            res.setIsSucceed(1);
            System.out.println("로그인 성공");


            session.setAttribute("id", user.getUsername());
            System.out.println("session userName : " + session.getAttribute("id"));
            //git hub에서 주석 처리해준 부분
            //session.setAttribute("id", user.getId());
            //System.out.println("session data : " + session.getId());

            System.out.println("userName : " + login.getUsername());

            // 여기서 바로 데이터를 조회해 옴
            List<SummaryData> summaryDataList = summaryService.getSummaryAllByUserName(login.getUsername());

            mav.addObject("summaryList", summaryDataList);
            mav.addObject("sessionData", "jhhyfg");
            mav.addObject("userName", login.getUsername());
            System.out.println("데이터" + summaryDataList);
            mav.setViewName("index");
        } else if (!isMatch(user.getPassword(), login.getPassword())) {
            res.setIsSucceed(0);
            System.out.println("비번이 서로 달라 로그인 실패");
            mav.setViewName("/userLogin");
        } else {

            System.out.println("아이디도 다른듯");
            mav.setViewName("/userLogin");
        }
        return mav;
//        return new ModelAndView("forward:/");
    }

    // 파일리스트 페이지
    @GetMapping("/mypageFilelist")
    public ModelAndView fileList(Users user, HttpSession session) {
        ModelAndView mav = new ModelAndView("mypageFilelist");

        System.out.println(session.getAttribute("id"));
        mav.addObject(session.getAttribute("id"));

        List<SummaryData> summaryDataList = summaryService.getSummaryAllByUserName((String)session.getAttribute("id"));

        mav.addObject("summaryList", summaryDataList);
        mav.addObject("userName", session.getAttribute("id"));

        return mav;
    }

    // 회원정보 상세조회 마이 페이지 -> summary 값을 여기로 전달해야 할듯
    @GetMapping("/mypage")
    public String dispMypage() {
        return "/mypage";
    }

    // Modal로 데이터 전달 테스트
    @GetMapping("/getSummaryData/{fileName}")
    public ModelAndView deidentificatonFile(@PathVariable String fileName) {
        SummaryData summaryData = summaryService.getSummaryByFileName(fileName);

        // 여기서 바로 데이터를 조회해 옴
        ModelAndView mav = new ModelAndView();

        mav.addObject("summaryList", summaryData);

        System.out.println(summaryData);
        // 비식별 선택하는 페이지
        mav.setViewName("summarydataDetail");
        return mav;
    }

    // 마이 페이지 업로드 -> 요청 시 로그인한 정보를 바탕으로 화면에 뿌려줘야 함
    @GetMapping("/mypageUpload")
    public String dispMypageUpload() {
        return "/mypageUpload";
    }

    // 마이 페이지 처리이력 -> 요청 시 로그인한 정보를 바탕으로 화면에 뿌려줘야 함
    @GetMapping("/mypageResultLog")
    public String dispMypageResultLog() {
        return "/mypageResultLog";
    }
}
