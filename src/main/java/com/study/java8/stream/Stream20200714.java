package com.study.java8.stream;

import com.study.model.Student;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * https://blog.csdn.net/qq_37176126/article/details/81273195
 */
public class Stream20200714 {

    public static void main(String[] args) {
//        forEachTest();

//        filterTest();

//        limitTest();

//        sortedTest();

        collectTest();
    }

    private static List<Student> getStudentList() {
        Student student1 = new Student("liu", 22, "male");
        Student student2 = new Student("zhao", 21, "male");
        Student student3 = new Student("li", 18, "famale");
        Student student4 = new Student("wang", 21, "famale");

        List<Student> list = new ArrayList<>();
        list.add(student1);
        list.add(student2);
        list.add(student3);
        list.add(student4);
        return list;
    }

    // 2 forEach遍历集合
    private static void forEachTest() {
        List<Student> list = getStudentList();
        // 使用 forEach方法，直接通过一行代码即可完成对集合的遍历:
        list.forEach(student -> System.out.println(student.toString()));

        // 双冒号::  表示方法引用，可以引用其他方法
        System.out.println("使用双冒号::");
        Consumer<Student> changeAge = student -> student.setAge(student.getAge() + 3);
        list.forEach(changeAge);
        list.forEach(System.out::println);
    }

    // 3 filter 对集合进行过滤
    private static void filterTest() {
        List<Student> list = getStudentList();

        // 3.1 filter 可以根据传入的 Predicate 对象，对集合进行过滤操作，Predicate 实质就是描述了过滤的条件
        list.stream().filter(student -> student.getAge() > 20)
                .forEach(student -> System.out.println(student.toString()));

        System.out.println("----------------");
        // 多条件过滤
        // 3.2 当需要通过 多个过滤条件对集合进行过滤时，可以采取两种方式：
        // 3.2.1.可以通过调用多次filter 通过传入不同的 Predicate对象来进行过滤
        Predicate<Student> ageFilter = student -> student.getAge() > 20;
        Predicate<Student> sexFilter = student -> student.getSex().equals("male");
        list.stream()
                .filter(ageFilter)
                .filter(sexFilter)
                .forEach(student -> System.out.println(student.toString()));

        System.out.println("----------------");
        // 3.2.2.也可以通过 Predicate 对象的 and  or 方法，对多个Predicate 对象进行 且 或 操作
        list.stream().filter(ageFilter.and(sexFilter))
                .forEach(student -> System.out.println(student.toString()));
    }

    // 4 limit 限制结果集的数据量
    private static void limitTest() {
        List<Student> list = getStudentList();
        list.stream().limit(3).forEach(student -> System.out.println(student.toString()));

        System.out.println("----------------");
        list.stream().limit(2).filter(student -> student.getAge() > 20)
                .forEach(student -> System.out.println(student.toString()));
    }

    /**
     * 5 sorted 排序
     * 通过sorted，可以按自定义的规则，对数据进行排序，可以用两种写法，分别按 年龄 和 姓名排序
     */
    private static void sortedTest() {
        List<Student> list = getStudentList();

        // 年龄排序
        list.stream().sorted((s1, s2) -> (s1.getAge() - s2.getAge()))
                .forEach(student -> System.out.println(student.toString()));

        System.out.println("----------------------------");
        // 姓名排序
        list.stream().sorted(Comparator.comparing(Student::getUserName))
                .forEach(student -> System.out.println(student.toString()));
    }


    /**
     * 8 collect方法以集合中的元素为基础，生成新的对象
     * <p>
     * 在实际中，我们经常会以集合中的元素为基础，取其中的数据，来生成新的结果集，例如 按照过滤条件，返回新的List，
     * 或者将集合转化为 Set 或Map等操作，通过collect方法实现是十分简便的
     */
    private static void collectTest() {
        List<Student> list = getStudentList();

        //排序过滤等一系列操作之后的元素 放入新的list
        System.out.println("--------------list--------------");
        List<Student> filterList = list.stream().filter(student -> student.getAge() > 20).collect(Collectors.toList());
        filterList.forEach(student -> System.out.println(student.toString()));

        // 将 name属性用","连接拼接成一个字符串
        String nameStr = list.stream().map(Student::getUserName).collect(Collectors.joining(","));
        System.out.println(nameStr);

        // 将name放入到Set集合中
        System.out.println("------------set----------------");
        Set<String> stringSet = list.stream().map(student -> student.getUserName()).collect(Collectors.toSet());
        stringSet.forEach(s -> System.out.println(s + ","));

        // 放入map中
        System.out.println("-------------map---------------");
        Map<String, Student> studentMap = list.stream().collect(Collectors.toMap(Student::getUserName, student -> student));
        studentMap.forEach((key, val) -> {
            System.out.println(key + ":" + val);
        });
    }


}
