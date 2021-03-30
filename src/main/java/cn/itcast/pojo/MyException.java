package cn.itcast.pojo;

import lombok.Data;

/**
 * @author 78703
 * @version 1.0
 * @description: TODO
 * @date 2021/3/18 23:33
 */
@Data
public class MyException extends RuntimeException{


    private int code;//异常状态码
    private String message;//异常信息
    private String descinfo;//描述

    /**
     * @param code 状态
     * @param message 信息
     * @param descinfo 错误,描述!
     */
    public MyException(int code, String message,String descinfo) {
        this.code = code;
        this.message = message;
        this.descinfo = descinfo;
    }


}
