package com.example.cangqiong.Common.Annotation;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Operation {
    String summary() default "";
    String description() default "";
}
