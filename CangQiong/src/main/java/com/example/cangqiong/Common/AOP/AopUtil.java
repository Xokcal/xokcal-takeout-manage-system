package com.example.cangqiong.Common.AOP;

import com.example.cangqiong.Common.Annotation.Operation;
import com.example.cangqiong.Common.Annotation.RedisOperate;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
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
    private void annotationOperationPointcut() {}

    @Around("annotationOperationPointcut()")
    public Object aroundAnnotationOperation(ProceedingJoinPoint pjp) throws Throwable {
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        Method method = signature.getMethod();
        Operation operate = method.getAnnotation(Operation.class);
        log.info("[Operation] 接口：{}    方式：{}    执行时间：{}"
                , operate.summary() , operate.description()
                , LocalDateTime.now().format(DateTimeFormatter.ofPattern("YYYY-MM-dd : hh-mm-ss")));
        Object proceed = pjp.proceed();
        return proceed;
    }

    @Pointcut("@annotation(com.example.cangqiong.Common.Annotation.RedisOperate)")
    private void annotationRedisOperatePointcut() {}

    @Around("annotationRedisOperatePointcut()")
    public Object aroundAnnotationRedisOperate(ProceedingJoinPoint pjp) throws Throwable {
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        Method method = signature.getMethod();
        RedisOperate redisOperate = method.getAnnotation(RedisOperate.class);
        log.info("[RedisOperate] 接口所属接口：{}    缓存功能描述：{}    执行时间：{}"
                , redisOperate.api() , redisOperate.summary()
                , LocalDateTime.now().format(DateTimeFormatter.ofPattern("YYYY-MM-dd : hh-mm-ss")));
        Object proceed = pjp.proceed();
        return proceed;
    }

    @Around("execution(* com.example.cangqiong.Service.Redis..*(..))")
    public Object aroundRedis(ProceedingJoinPoint pjp) throws Throwable {
        long begin = System.currentTimeMillis();
        Object proceed = pjp.proceed();
        long end = System.currentTimeMillis();
        log.info("[Redis] AOP处理Redis层接口耗时，此接口耗时：{} ms" ,end - begin);
        return proceed;
    }

    @Around("execution(* com.example.cangqiong.Controller..*(..))")
    public Object aroundController(ProceedingJoinPoint pjp) throws Throwable {
        long begin = System.currentTimeMillis();
        Object proceed = pjp.proceed();
        long end = System.currentTimeMillis();
        log.info("[Controller] AOP处理Controller层接口耗时，此接口耗时：{} ms",end - begin);
        return proceed;
    }

    @AfterReturning(
            pointcut = "execution(* com.example.cangqiong.Controller..*(..))",
            returning = "result"
    )
    public void afterReturning(JoinPoint joinPoint, Object result) {
        Signature signature = joinPoint.getSignature();
        String name = signature.getName();
        log.info("[ControllerAfterReturning] 方法名为："+name+ "   方法执行完毕，返回值：" + result);
    }
}
