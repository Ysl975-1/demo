package com.example.demo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//公共返回对象
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReturnBack {
    private long code;
    private String message;
    private Object obj;
    public static ReturnBack success(){
        return new ReturnBack(ReturnBackEnum.SUCCESS.getCode(), ReturnBackEnum.SUCCESS.getMessage(), null);
    }
    public static ReturnBack success(Object obj){
        return new ReturnBack(ReturnBackEnum.SUCCESS.getCode(), ReturnBackEnum.SUCCESS.getMessage(), obj);
    }
    public static ReturnBack error(ReturnBackEnum returnBackEnum){
        return new ReturnBack(returnBackEnum.getCode(), returnBackEnum.getMessage(), null);
    }
    public static ReturnBack error(ReturnBackEnum returnBackEnum, Object obj){
        return new ReturnBack(returnBackEnum.getCode(), returnBackEnum.getMessage(), obj);
    }
}
