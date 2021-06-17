package cn.itcast.aop;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * 参数日志切面，用于打印请求参数，返回值，接口耗时
 */
@Slf4j
@Aspect// 第一步：声明这是一个切面类
public class ApiTimeLogAspect {

    //request获取的方式：1、自动注入 2、通过RequestContextHolder
//    @Autowired
//    private HttpServletRequest request;

    /**
     * SpringMVC自带的jackon，输出JSON
     */
    @Autowired
    private ObjectMapper objectMapper;

    // 第三步：定义切点表达式，明确要对那些方法起作用（比如，只对cn.itcast.web.controller包的方法计算接口耗时）
    @Pointcut("execution(* cn.itcast.web.controller.*.*(..))")
    public void controllerPointcut(){}


    @Before("controllerPointcut()")
    public void doBefore(JoinPoint joinPoint) throws JsonProcessingException {
        //通过Spring提供的请求上下文工具，获取request
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        //signature是方法签名，可以理解为对方法信息的封装
        Signature signature = joinPoint.getSignature();
        String methodName = signature.getName();

        //根据signature判断当前请求属于哪类操作，前提是工程遵守标准的命名规范
        String operation = "";
        if(methodName.startsWith("list")
                || methodName.startsWith("query")
                || methodName.startsWith("get")
                || methodName.startsWith("select")){
            operation = "查询";
        }else if(methodName.startsWith("save")
                || methodName.startsWith("add")
                || methodName.startsWith("insert")){
            operation = "新增";
        }else if(methodName.startsWith("delete")){
            operation = "删除";
        }else if(methodName.startsWith("update") || methodName.startsWith("modify")){
            operation = "更新";
        }

        Class controllerClazz  = signature.getDeclaringType();
        //cn.itcast.web.controller.HelloController
        String controllerName = controllerClazz.getName();
        //UserController
        String controllerSimpleName = controllerName.substring(controllerName.lastIndexOf(".") + 1);

        // 打印请求信息
        log.info("------------【{}】{}操作 ------------", controllerSimpleName, operation);
        log.info("接口URL: {} {}", request.getRequestURL().toString(), request.getMethod());
        log.info("类名方法: {}#{}()", controllerName, methodName);
        log.info("远程地址: {}", getClientIp(request));

        // 打印请求参数 TODO 敏感字段排除
        log.info("请求参数: {}", getParamJSon(request, joinPoint));
    }

    // 第四步：1.通过引用切点表达式，明确这个增强的应用规则。 2.编写增强逻辑
    @Around("controller()")
    public Object doAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        // 记录接口执行前的时间戳
        long startTime = System.currentTimeMillis();
        // 实际执行目标方法，类似动态代理的invoke()执行目标方法
        Object result = proceedingJoinPoint.proceed();
        // 计算接口耗时
        log.info("返回结果: {}", objectMapper.writeValueAsString(result));
        log.info("------------ 耗时: {} ms ------------", System.currentTimeMillis() - startTime);
        // 只做增强不做改变，还是要把接口原本的结果返回
        return result;
    }


    //-----------------private methods --------------------------

    /**
     * 获取接口参数
     * @param request
     * @param joinPoint
     * @return
     * @throws JsonProcessingException
     */
    private String getParamJSon(HttpServletRequest request,JoinPoint joinPoint) throws JsonProcessingException {
        String requestType = request.getMethod();
        if("GET".equals(requestType)){
            //如果是GET请求，直接返回QueryString
            return request.getQueryString();
        }

        // args长什么样呢？类似于Controller方法中的getUser(Long id, HttpServletRequest request)这种，可能有多个参数
        Object[] args = joinPoint.getArgs();
        Object[] arguments = new Object[args.length];

        for (int i = 0; i < args.length; i++) {
            //只打印客户端传递的参数，排除Spring注入的参数，比如HttpServletRequest
            if(args[i] instanceof ServletRequest
                || args[i] instanceof ServletResponse
                || args[i] instanceof MultipartFile){
                continue;
            }
            arguments[i] = args[i];
        }
        return objectMapper.writeValueAsString(arguments);
    }

    private String getClientIp(HttpServletRequest request){
        //一般都会有代理转发，真实的ip会放在x-forwarded-for
        String xff = request.getHeader("X-Forwarded-For");
        if(xff == null){
            return request.getRemoteAddr();
        }else {
            return xff.contains(",") ? xff.split(",")[0] : xff;
        }
    }
}