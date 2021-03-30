package cn.itcast.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;

/**
 * @author 78703
 * @version 1.0
 * @description: TODO
 * @date 2021/3/22 15:20
 */
@RestController
public class HomeController {

    @GetMapping("/")
    public void index(HttpServletResponse response) throws IOException {

        response.sendRedirect("http://localhost:8080/index.html");
    }
//    @GetMapping("/")
//    public String index() {
//        return "home/homeNotSignedIn";
//    }

}
