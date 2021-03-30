package cn.itcast.util;

import cn.itcast.pojo.MyException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 78703
 * @version 1.0
 * @description: TODO
 * @date 2021/3/18 23:35
 */

@ControllerAdvice//控制器增强，配合ExceptionHandler实现全局补抓异常
@Slf4j
public class MyControllerAdvice {

    @ExceptionHandler(value = MyException.class)
    @ResponseBody
    public Map exceptionHandler(MyException exception){
        Map<String,Object> map = new HashMap<>();
        map.put("code",exception.getCode());
        map.put("message",exception.getMessage());
        return map;
    }
}
