package com.companyd.hompage.seoul.exception;

public class CustomException extends RuntimeException{
    private String errCode;
    private String errMsg;

    public String getErrCode() {
        return errCode;
    }

    public String getErrMsg() {
        return errMsg;
    }
}
