package com.companyd.hompage.seoul.service;

import com.companyd.hompage.seoul.entity.Uploads;

import java.util.List;

public interface UploadService {
    List<Uploads> getAllUploads();
    Uploads getUploadById(int id);
    int createUpload(Uploads upload);
    int modifyUpload(Uploads upload);
    int removeUpload(int id);
}
