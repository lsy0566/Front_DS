package com.companyd.hompage.seoul.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Logs {
    private int id;
    private String user_name;
    private Date created_date;
    private int download_cnt;
    private Date download_date;
    private String file_name;
    private int is_succeed;
    private String origin_location;
    private String result_location;

    public int getId() {
        return id;
    }

    public String getUser_name() {
        return user_name;
    }

    public Date getCreated_date() {
        return created_date;
    }

    public int getDownload_cnt() {
        return download_cnt;
    }

    public Date getDownload_date() {
        return download_date;
    }

    public String getFile_name() {
        return file_name;
    }

    public int getIs_succeed() {
        return is_succeed;
    }

    public String getOrigin_location() {
        return origin_location;
    }

    public String getResult_location() {
        return result_location;
    }
}
