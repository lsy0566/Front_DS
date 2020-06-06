package com.companyd.hompage.seoul.service;

import java.util.List;

public interface UploadService {
    List<Uploads> getAllUploads();
    Uploads getUploadById(int id);
    int createUpload(Uploads upload);
    int modifyUpload(Uploads upload);
    int removeUpload(int id);
}
