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
}
