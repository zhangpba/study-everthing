package com.study.java8.lambda;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class Lambda20200120 {
    public static void main(String[] args) {
//        one();
//        two();
//        three();
        four();
    }

    // 一 用 "() -> {}" 代码块替代了整个匿名类
    public static void one() {
        // java8之前
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Before java8,too mach code for too little to do");
            }
        }).start();

        // java8方式
        new Thread(() -> System.out.println("in java8 ,Lambda expression rocks!")).start();
    }

    // 二
    public static void two() {
        JButton show = new JButton("show");
        // java8之前
        show.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Event handling without lambda expression is boring");
            }
        });
        // java8
        show.addActionListener((e) -> {
            System.out.println("Light, Camera, Action !! Lambda expressions Rocks");
        });
    }

    // 三 对列表进行迭代
    public static void three() {
        // Java 8之前：
        List<String> features = Arrays.asList("Lambdas", "Default Method", "Stream API", "Date and Time API");
        for (String feature : features) {
            System.out.println(feature);
        }

        // Java 8之后：
        features.forEach(n -> System.out.println(n));
        // 使用Java 8的方法引用更方便，方法引用由::双冒号操作符标示，看起来像C++的作用域解析运算符
        features.forEach(System.out::println);
    }

    // 使用lambda表达式和函数式接口
    public static void four() {
        List languages = Arrays.asList("Java", "Scala", "C++", "Haskell", "Lisp");
        System.out.println("Languages which starts with J :" + languages);

    }

    public static void filter(List<String> names, Predicate condition) {
        for (String name : names) {
            if (condition.test(name)) {
                System.out.println(name + " ");
            }
        }
    }
}
