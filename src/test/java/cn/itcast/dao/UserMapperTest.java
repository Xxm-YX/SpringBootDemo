package cn.itcast.dao;

//import cn.itcast.pojo.TkUserPojo;
import cn.itcast.pojo.User;
import cn.itcast.springbootdemo.SpringbootDemoApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@SpringBootTest(classes = SpringbootDemoApplication.class)
@WebAppConfiguration
@RunWith(SpringRunner.class)
public class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testQuery(){
        User user = userMapper.selectByPrimaryKey(8l);
        System.out.println(user);
    }
}