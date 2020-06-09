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

    public String getColName() {
        return colName;
    }

    public void setColName(String colName) {
        this.colName = colName;
    }

    public Summary getSummary() {
        return summary;
    }

    public void setSummary(Summary summary) {
        this.summary = summary;
    }

    public int getNullCount() {
        return nullCount;
    }

    public void setNullCount(int nullCount) {
        this.nullCount = nullCount;
    }
}
