package com.companyd.hompage.seoul.repository;


import com.companyd.hompage.seoul.entity.Uploads;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UploadMapper {
    List<Uploads> selectAllUploads();
    Uploads selectUploadById(int id);
    int insertUpload(Uploads upload);//return 0 or 1
    int updateUpload(Uploads upload); //return update row 수
    int deleteUpload(int id);//return delete row 수
}
