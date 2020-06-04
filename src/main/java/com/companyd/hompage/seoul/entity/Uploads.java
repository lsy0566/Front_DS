package com.companyd.hompage.seoul.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Uploads {
    private int id;
    private String file_name;
    private Date upload_date;
    private String upload_position;
}
