package com.mark.demo.exception;

/**
 * @program: demo
 * @description: 自定义异常
 * @author: wu
 * @create: 2020-03-14 15:09
 **/
public class CustomizeException extends RuntimeException {
    private String massage;
    private Integer code;

    public CustomizeException(ICustomizeErrorCode errorCode) {
        this.code = errorCode.getCode();
        this.massage = errorCode.getMessage();
    }


    public Integer getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return massage;
    }
}


