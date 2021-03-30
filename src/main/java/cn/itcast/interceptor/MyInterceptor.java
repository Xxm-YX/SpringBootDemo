package cn.itcast.interceptor;

import cn.itcast.pojo.MyException;
import cn.itcast.pojo.User;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StreamUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;


@Slf4j
public class MyInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {

//        if(!(handler instanceof HandlerMethod)){
//           return true;
//        }
//        if (handler instanceof ResourceHttpRequestHandler){
//            return true;
//        }
//
//        if(1 == 2){
//            return true;
//        }
//        throw new MyException(20001,"No access","bbb");
//
//        System.out.println("aaaaaa");
//        String user = StreamUtils.copyToString(request.getInputStream(), StandardCharsets.UTF_8);
//        JSONObject jsonObject = JSON.parseObject(user);
//        jsonObject.getString("");

        return true;
//        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.debug("postHandle method is running");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        log.debug("afterCompletion method is running");
    }
}
