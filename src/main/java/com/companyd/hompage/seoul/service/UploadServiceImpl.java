package com.companyd.hompage.seoul.service;

import com.companyd.hompage.seoul.entity.Uploads;
import com.companyd.hompage.seoul.repository.UploadMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UploadServiceImpl implements UploadService {
    @Autowired
    UploadMapper mapper;

    @Override
    public List<Uploads> getAllUploads() {
        return mapper.selectAllUploads();
    }

    @Override
    public Uploads getUploadById(int id) {
        return mapper.selectUploadById(id);
    }

    @Override
    public int createUpload(Uploads upload) {
        mapper.insertUpload(upload);
        System.out.println("upload 생성쿼리 실행!");
        int idData = upload.getId();
        System.out.println("생성된 upload id값 : " + idData);
        return idData;
    }

    @Override
    public int modifyUpload(Uploads upload) {
        return mapper.updateUpload(upload);
    }

    @Override
    public int removeUpload(int id) {
        return mapper.deleteUpload(id);
    }
}
