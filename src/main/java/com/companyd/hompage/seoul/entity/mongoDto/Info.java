package com.companyd.hompage.seoul.entity.mongoDto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
public class Info {
    private String colName;
    private Summary summary;
    private int nullCount;
}
