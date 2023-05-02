package com.example.demo.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

//公共返回对象枚举
@Getter
@ToString
@AllArgsConstructor
public enum ReturnBackEnum {
    SUCCESS(200, "SUCCESS"),
    ERROR(500, "服务端异常"),
    NOT_EXIST(502, "NOT_EXIST_USER"),
    NO_INFO(503, "NO_INFO"),
    ADD_ERROR(500210, "ADD FAIL"),
    UPDATE_ERROR(500211, "UPDATE FAIL"),
    //绑定异常
    BIND_ERROR(500212, "参数校验异常");
    private final Integer code;
    private final String message;
}
