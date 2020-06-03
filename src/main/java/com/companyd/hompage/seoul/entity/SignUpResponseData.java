package com.companyd.hompage.seoul.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignUpResponseData {
//    private int id;
    private int isSucceed;
    private String dataType = "signup"; // 회원가입 실패&성공 모두 signup이라는 값 프론트에 전송
}
