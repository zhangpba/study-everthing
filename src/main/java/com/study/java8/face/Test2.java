package com.study.java8.face;

import java.util.Arrays;
import java.util.List;

/**
 * 函数式接口:只有一个抽象方法
 */
@FunctionalInterface
interface MyInterface {
    void test();

    // object中的方法增加不会使函数式接口的方法数量+1
    // 即toString不算函数接口里面的抽象方法
    String toString();
}

public class Test2 {

    public void myTest(MyInterface myInterface) {
        System.out.println(1);
        myInterface.test();
        System.out.println();
    }

    public static void main(String[] args) {
        Test2 test2 = new Test2();

        // 传统写法
        test2.myTest(new MyInterface() {
            @Override
            public void test() {
                System.out.println("myTest");
            }
        });

        // lambda表达式
        test2.myTest(() -> {
            System.out.println("myTest");
        });


        MyInterface myInterface = () -> {
            System.out.println("hello");
        };
        System.out.println(myInterface.getClass());
        System.out.println(myInterface.getClass().getSuperclass());
        System.out.println(myInterface.getClass().getInterfaces()[0]);


        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8);
//        for (int i = 0; i < list.size(); i++) {
//            System.out.println(list.get(i));
//        }
//
//        for (Integer i : list) {
//            System.out.println(i);
//        }

        list.forEach(i -> {
            System.out.println("第" + i + "个");
        });

        // 方法引用 method reference
        list.forEach(System.out::println);
    }
}
