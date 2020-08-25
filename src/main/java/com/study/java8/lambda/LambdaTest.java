package com.study.java8.lambda;

/**
 * Lambda 表达式，也可称为闭包，它是推动 Java 8 发布的最重要新特性。
 * Lambda 允许把函数作为一个方法的参数（函数作为参数传递进方法中）。
 * 使用 Lambda 表达式可以使代码变的更加简洁紧凑。
 * <p>
 * lambda 表达式的语法格式如下：
 * (parameters) -> expression
 * 或
 * (parameters) ->{ statements; }
 */
public class LambdaTest {

    /**
     * 1. 不需要参数,返回值为 5
     * () -> 5
     * <p>
     * 2. 接收一个参数(数字类型),返回其2倍的值
     * x -> 2 * x
     * <p>
     * 3. 接受2个参数(数字),并返回他们的差值
     * (x, y) -> x – y
     * <p>
     * 4. 接收2个int型整数,返回他们的和
     * (int x, int y) -> x + y
     * <p>
     * 5. 接受一个 string 对象,并在控制台打印,不返回任何值(看起来像是返回void)
     * (String s) -> System.out.print(s)
     */


    public static void main(String[] args) {

        LambdaTest lambdaTest = new LambdaTest();

        // 下面是个函数相当于对于MathOperation的四个实现
        // 一 类型声明
        MathOperation addition = (int a, int b) -> a + b;

        // 二 不用类型声明
        MathOperation subtraction = (a, b) -> a - b;

        // 三 大括号中的返回语句
        MathOperation multilplition = (int a, int b) -> {
            return a * b;
        };

        // 四 没有大括号及返回语句
        MathOperation division = (int a, int b) -> a / b;


        System.out.println("10 + 5 = " + lambdaTest.operate(10, 5, addition));
        System.out.println("10 - 5 = " + lambdaTest.operate(10, 5, subtraction));
        System.out.println("10 * 5 = " + lambdaTest.operate(10, 5, multilplition));
        System.out.println("10 / 5 = " + lambdaTest.operate(10, 5, division));


        // 不用括号
        GreetingService greetingService1 = message -> System.out.println("hello " + message);

        // 用括号
        GreetingService greetingService2 = (message) -> System.out.println("hello " + message);


        greetingService1.sayMessage("zhang");
        greetingService2.sayMessage("pengbo");
    }


    interface MathOperation {
        int operation(int a, int b);
    }

    interface GreetingService {
        void sayMessage(String message);
    }

    private int operate(int a, int b, MathOperation mathOperation) {
        return mathOperation.operation(a, b);
    }

}
