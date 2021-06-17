package cn.itcast.springbootdemo;


import cn.itcast.config.EnableApiTimeLog;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;


@SpringBootApplication(scanBasePackages = "cn")
@MapperScan("cn.itcast.dao")
@EnableApiTimeLog
public class SpringbootDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootDemoApplication.class, args);
    }

}
