package com.companyd.hompage.seoul.controller;

import com.companyd.hompage.seoul.entity.Uploads;
import com.companyd.hompage.seoul.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class UploadController {
    @Autowired
    UploadService service;

    //모든 upload파일 목록
    @GetMapping("/upload")
    public List<Uploads> getAllUploads() {
        List<Uploads> list = service.getAllUploads();
        return list;
    }

    //특정 upload파일 정보
    @GetMapping("upload/{id}")
    public Uploads getUploadById(@PathVariable int id){
        Uploads upload = service.getUploadById(id);
        return upload;
    }

    //upload파일 info 내역 생성
    @PostMapping("upload/enroll")
    public int createupload(@Valid @RequestBody Uploads upload){
        System.out.println("업로드 파일 정보 등록");
        int createdUpload = service.createUpload(upload);
        return createdUpload;
    }

    //upload파일 info 내역 삭제
    @DeleteMapping("/upload/delete/{id}")
    public int removeUpload(@PathVariable int id){
        int upload = service.removeUpload(id);
        return upload;
    }
}
