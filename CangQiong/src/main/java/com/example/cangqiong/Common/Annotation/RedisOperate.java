package com.example.cangqiong.Common.Annotation;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RedisOperate {
    String api() default "";
    String summary() default "";
}
