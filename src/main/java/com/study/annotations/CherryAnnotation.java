package com.study.annotations;

import java.lang.annotation.*;

/**
 * 自定义注解
 * 2020-03-23
 * https://blog.csdn.net/xsp_happyboy/article/details/80987484
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@Documented
public @interface CherryAnnotation {
    String name() default "";

    int age() default 18;

    int[] score() default {};
}
