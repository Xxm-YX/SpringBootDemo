package cn.itcast.config;

import cn.itcast.interceptor.MyInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new MyInterceptor()).addPathPatterns("/**")
                .excludePathPatterns(
                //放行静态资源js,css,图片等
                "/css/**",
                "/fonts/**",
                "/error/**",
                "/img/**",
                "/js/**",
                "/lib/**",
                //放行请求
                "/",
                "/login",//登陆请求
                "/*.html"
        );
    }


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**").addResourceLocations("classpath:/resources/")
                .addResourceLocations("classpath:/static/");
    }


}
