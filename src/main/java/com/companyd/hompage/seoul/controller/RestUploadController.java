//package com.companyd.hompage.seoul.controller;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.multipart.MultipartFile;
//
//
//@RestController
//public class RestUploadController {
//
//    private final Logger logger = LoggerFactory.getLogger(RestUploadController.class);
//
//    // 업로드 된 파일을 폴더에 저장
//    private static String Uploaded_Folder = "F://temp//";
//
//    // 단일 파일 업로드
//    @PostMapping("/api/upload")
//    @ResponseBody
//    public ResponseBody<?> uploadFile(
//            @RequestParam("file") MultipartFile uploadfile) {
//
//        logger.debug("Single file upload!");
//
//        if (uploadfile.isEmpty()) {
//            return new ResponseEntity("please select a file!", HttpStatus.OK);
//        }
//
//
//    }
//}
