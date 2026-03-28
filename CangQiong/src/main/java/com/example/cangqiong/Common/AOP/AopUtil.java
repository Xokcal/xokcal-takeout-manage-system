package com.example.cangqiong.Common.AOP;

import com.example.cangqiong.Common.Annotation.Operation;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
@Aspect
@Component
public class AopUtil {

    @Pointcut("@annotation(com.example.cangqiong.Common.Annotation.Operation)")
    private void annotationPointcut() {}

    @Around("annotationPointcut()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        long begin = System.currentTimeMillis();
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        Method method = signature.getMethod();
        Operation operate = method.getAnnotation(Operation.class);
        log.info("接口：{}    方式：{}    执行时间：{}"
                , operate.summary() , operate.description()
                , LocalDateTime.now().format(DateTimeFormatter.ofPattern("YYYY-MM-dd : hh-mm-ss")));
        Object proceed = pjp.proceed();
        long end = System.currentTimeMillis();
        log.info("{} ms",end - begin);
        return proceed;
    }
}
