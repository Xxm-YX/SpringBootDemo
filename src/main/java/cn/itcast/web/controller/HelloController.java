package cn.itcast.web.controller;

//import cn.itcast.pojo.TkUserPojo;
import cn.itcast.pojo.ActionResult;
import cn.itcast.pojo.User;
import cn.itcast.service.UserService;
import cn.itcast.util.Http.HttpRequsetUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/user")
public class HelloController {

    @Autowired
    private UserService userService;

    @GetMapping("{id}")
    public User hello(@PathVariable("id") Long id){
       return userService.queryById(id);
    }

    @GetMapping("/all")
    public ModelAndView all(User user){
        ModelAndView mv = new ModelAndView();

        //查询用户
        List<User> users = userService.queryAll(user);

        PageInfo pageInfo = new PageInfo(users);
        //放入模型
        mv.setViewName("users");
        mv.addObject("users",pageInfo);

        // 返回模板名称（就是classpath:/templates/目录下的html文件名）
        return mv;
    }

    @PostMapping("/nll")
    public ActionResult aa( User user,@RequestParam(value = "code",required = false) String code){
        ActionResult result = new ActionResult();
        //查询用户
        List<User> users = userService.queryAll(user);

        result.setCode(200);
        result.setData(users);

        return result;
    }

//    @GetMapping("/cll")
//    public ModelAndView bb(User user){
//        ModelAndView mv = new ModelAndView();
//        ActionResult result = new ActionResult();
//        //查询用户
//        List<User> users = userService.queryAll(user);
//
//        result.setCode(200);
//        result.setData(users);
//
//        mv.setViewName("users");
//        mv.addObject("users",result);
//        return mv;
//    }

    @GetMapping("/login")
    public String login(String code) throws IOException {
        if(StringUtils.isBlank(code)){
            return "";
        }
        System.out.println("code:"+code);

        String url = "https://api.weixin.qq.com/sns/jscode2session";
        url += "?appid=wxe082391dcbe53e8a";
        url += "&secret=c6f6d6f7b18da614a3f458565e8530ed";
        url += "&js_code=" + code;
        url += "&grant_type=authorization_code";
        url += "&connect_redirect=1";

        System.out.println(url);

        JSONObject jo = JSON.parseObject(HttpRequsetUtil.getResponse(url));
        System.out.println(jo);
        String openid = jo.getString("openid");
        String sessionkey = jo.getString("session_key");
        System.out.println(openid);
        System.out.println(sessionkey);

        //这个地方获取了session_Key 和 openid 后就直接给前台
        return openid;
    }


//    @GetMapping("/")
//    public void index(HttpServletResponse response) throws IOException {
//
//        response.sendRedirect("/index.html");
//    }
}
