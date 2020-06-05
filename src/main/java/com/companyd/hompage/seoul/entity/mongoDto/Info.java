package com.companyd.hompage.seoul.entity.mongoDto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Info {
    private final String colName;
    private final Summary summary;
}
