package com.companyd.hompage.seoul.controller;

import com.companyd.hompage.seoul.entity.Logs;
import com.companyd.hompage.seoul.entity.Users;
import com.companyd.hompage.seoul.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.List;

@Controller
public class DownloadController {
    @Autowired
    LogService logservice;

    @GetMapping("/mypageDownload")
    public ModelAndView dispMypageDownload(HttpSession session, Model model, Users user) {
        ModelAndView mav = new ModelAndView();
        // id = userName
        System.out.println("session.getAttribute: " + session.getAttribute("id"));
        mav.addObject(session.getAttribute("id"));

//        List<Logs> LogsList = logservice.getLogByUserName((String)session.getAttribute("id"));
        List<Logs> LogsList = logservice.getSuccessLogByUserName((String)session.getAttribute("id"));
        System.out.println("logList2 : " + LogsList);
         mav.addObject("logList", LogsList);
         mav.addObject("userName", session.getAttribute("id"));

        System.out.println("session : " + session);
        System.out.println("session userName in mypageDownload loaded : " + session.getAttribute("id"));

        return mav;
    }

        // fileName이 아닌 직접 결과값 위치 반환
        @RequestMapping("/fileDown/{fileName}")
        private void fileDown(@PathVariable String fileName, HttpServletRequest request, HttpServletResponse response) throws  Exception{
            request.setCharacterEncoding("UTF-8");
            Logs log = logservice.getLogByFileName(fileName);
            System.out.println("파일 result_location: "+fileName);
            //파일 업로드된 경로
            try{
                String fileUrl = log.getResult_location();
                //fileUrl +="/";
                String savePath = fileUrl;
                System.out.println("fileUrl= "+ savePath);
                //내보낼 파일명
                String oriFileName = log.getFile_name();
                InputStream in = null;
                OutputStream os = null;
                File file = null;
                boolean skip = false;
                String client ="";

                //파일을 읽어 스트림에 담기
                try{
                    file = new File(savePath);
                    System.out.println("file=new File(savePath실행)");
                    in = new FileInputStream(file);
                }catch (FileNotFoundException fe){
                    System.out.println("FileNotFoundException 예외");
                    skip=true;
                }
                client = request.getHeader("User-Agent");

                //다운로드 헤더 지정
                response.reset();
                response.setContentType("application/octet-stream");
                response.setHeader("Content-Description", "JSP Generated Data");

                if(!skip){
                    // IE
                    if (client.indexOf("MSIE") != -1) {
                        response.setHeader("Content-Disposition", "attachment; filename=\""
                                + java.net.URLEncoder.encode(oriFileName, "UTF-8").replaceAll("\\+", "\\ ") + "\"");
                        // IE 11 이상.
                    } else if (client.indexOf("Trident") != -1) {
                        response.setHeader("Content-Disposition", "attachment; filename=\""
                                + java.net.URLEncoder.encode(oriFileName, "UTF-8").replaceAll("\\+", "\\ ") + "\"");
                    } else {
                        // 한글 파일명 처리
                        response.setHeader("Content-Disposition",
                                "attachment; filename=\"" + new String(oriFileName.getBytes("UTF-8"), "ISO8859_1") + "\"");
                        response.setHeader("Content-Type", "application/octet-stream; charset=utf-8");
                    }
                    response.setHeader("Content-Length", "" + file.length());
                    os = response.getOutputStream();
                    byte b[] = new byte[(int) file.length()];
                    int leng = 0;
                    while ((leng = in.read(b)) > 0) {
                        os.write(b, 0, leng);
                    }
                } else {
                    response.setContentType("text/html;charset=UTF-8");
                    System.out.println("<script language='javascript'>alert('파일을 찾을 수 없습니다');history.back();</script>");
                }
                in.close();
                os.close();
            } catch (Exception e){
                System.out.println("ERROR에러" + e.getMessage());
            }
        }
}
