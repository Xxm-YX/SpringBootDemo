package cn.itcast.web.controller;

import cn.itcast.pojo.Result;
import cn.itcast.pojo.User;
import javax.validation.constraints.NotNull;

import cn.itcast.pojo.ValidationList;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Validated
@RestController
public class UserController {

    @GetMapping("getUser")
    public User getUser(@NotNull(message = "id不能为空") Long id) {
        test();
        return null;
    }

    private void test(){
        System.out.println("test");
    }

    @PostMapping("updateBatchUser")
    public Result<Boolean> updateBatchUser(@Validated @RequestBody ValidationList<User> userList) {
        System.out.println(userList);
        return Result.success(null);
    }
}
