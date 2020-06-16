package com.companyd.hompage.seoul.entity.mongoDto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "SummaryData")
@Data
@Setter
@NoArgsConstructor
public class SummaryData {
    @Id
    private String _id;
    private String userName;
    private String fileName;
    private String originLocation;
    private List<Info> info;
    private Integer nullCount;
}
