package com.study.java8.lambda.Lambda20200714;

/**
 * 函数式接口
 */
@FunctionalInterface
public interface GreetingService {

    // 抽象方法
    void sayMessage(String message);

    // todo 默认方法
    default void doSomething() {
        System.out.println("默认方法执行...");
    }

    // 静态方法
    static void printHello() {
        System.out.println("静态方法对你说:hello");
    }

    // Object里的pubic方法
    @Override
    boolean equals(Object object);


}
