package com.companyd.hompage.seoul.model;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class UploadModel {
    private String extraField;
    private MultipartFile[] files;

    public String getExtraField() {
        return extraField;
    }

    public void setExtraField(String extraField) {
        this.extraField = extraField;
    }

    public MultipartFile[] getFiles() {
        return files;
    }

    public void setFiles(MultipartFile[] files) {
        this.files = files;
    }

    public UploadModel(String extraField, MultipartFile[] files) {
        this.extraField = extraField;
        this.files = files;
    }
}
