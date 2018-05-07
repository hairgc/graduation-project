package com.github.mahui53541.graduation.vo;

import org.springframework.http.ResponseEntity;

/**
 * 自定义
 *
 * @Description: java类作用描述
 * @Author: MaHui
 * @CreateDate: 2018/5/7 13:55
 * @Version: 1.0
 */
public class CustomResponse {
    public Object data;


    public String msg;

    public int code;


    public static  CustomResponse buildResponse(int code, String msg, Object data) {
        CustomResponse customResponse = new CustomResponse();
        customResponse.setCode(code);
        customResponse.setData(data);
        customResponse.setMsg(msg);
        return customResponse;
    }


    public static CustomResponse ok(Object data) {
        return buildResponse(200, "访问成功", data);
    }


    public static CustomResponse failure(int statusCode, String msg) {
        return buildResponse(statusCode, msg, null);
    }


    public ResponseEntity toResponseEntity() {
        return ResponseEntity.status(code).body(this);
    }


    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
