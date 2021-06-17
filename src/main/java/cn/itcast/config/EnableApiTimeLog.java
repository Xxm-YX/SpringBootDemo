package cn.itcast.config;

import cn.itcast.aop.ApiTimeLogAspect;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 开启接口耗时计算
 */
@Import(ApiTimeLogAspect.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface EnableApiTimeLog {
}
