package com.study.java8.lambda.Lambda20200714;

import java.util.function.Function;
import java.util.function.Supplier;

/**
 * 学习Lambda表达式终身成就
 */
public class Lambda20200714 {

    public static void main(String[] args) {
        functionalInterface();

        advanced();
    }

    // 函数式接口
    public static void functionalInterface() {
        GreetingService greetingService = message -> System.out.println("函数式接口对你说：hello," + message);

        greetingService.sayMessage("aaa");
        greetingService.doSomething();
        GreetingService.printHello();
    }

    // 进阶
    public static void advanced() {
        // 对比Function接口的抽象方法与String的value方法，可以看到它们是类似的
        Function function1 = (x) -> x;
        Function function2 = String::valueOf;
    }

    // 构造引用
    public static void structure() {
        // 先来创建一个接口对象
        Supplier<String> supplier = () -> new String();
        // 在这用lambda表达式只做一件事，就是返回一个新的test对象，而这种形式可以更简化
        Supplier<String> supplier1 = String::new;


//        Function function = (x) -> new String(x);

//        Function function1 = (x) -> String::new;


    }

}
