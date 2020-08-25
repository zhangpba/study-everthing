package com.study.java8.stream;

import com.study.model.Student;

import java.util.*;
import java.util.stream.Collectors;

public class Stream20200120 {

    public static void main(String[] args) {
        // 构造数据
        createStream();

        // 二 forEach
        forEatchTest();
        // 三 map方法用于映射每个元素到对应的结果，以下代码片段使用了map输出了元素的平方数
        mapTest();
        // 四 fifter方法
        filterTest();
        // 五 limit方法用于获取指定数量的流，以下代码输出前3个
        limitTest();
        // 六 sorted方法用于对流进行排序
        sortedTest();
        // 七 parallelStream是流并行处理程序的代替方法
        parallelStreamTest();
        // 八 Collectors实现了很多聚合函数，例如将流转换成集合和聚合元素
        colletorsTest();
        // 九 统计
        statistialTest();
    }

    private static List<String> strings;
    private static List<String> filtered;

    // 一 生成流的两种方式：1 stream() 2 parallelStream()
    public static void createStream() {
        strings = Arrays.asList("abc", "", "bc", "efg", "abcd", "", "jkl");
        filtered = strings.stream().filter(string -> !string.isEmpty()).collect(Collectors.toList());
    }

    // 二 forEach    输出十个随机数
    public static void forEatchTest() {
        Random random = new Random();
        random.ints().limit(10).forEach(a -> System.out.print(a + ";"));
        System.out.println();
    }

    // 三 map方法用于映射每个元素到对应的结果，以下代码片段使用了map输出了元素的平方数
    public static void mapTest() {
        List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 5);
        List<Integer> squaresList = numbers.stream().map(i -> i * i).distinct().collect(Collectors.toList());
        squaresList.forEach(num -> System.out.println(num));
    }

    // 四 filter方法用于通过设置的条件过滤出元素
    public static void filterTest() {
        System.out.println("四 fifter方法");
        Long emptyCount = strings.stream().filter(string -> string.isEmpty()).count();
        Long notEmptyCount = strings.stream().filter(string -> !string.isEmpty()).count();
        System.out.println("空字符串的大小：" + emptyCount + "；非空字符串的大小：" + notEmptyCount);
    }

    // 五 limit方法用于获取指定数量的流，以下代码输出前3个
    public static void limitTest() {
        System.out.println("五 limit方法");
        strings.stream().limit(3).forEach(System.out::println);
    }

    // 六 sorted方法用于对流进行排序
    public static void sortedTest() {
        System.out.println("六 sorted方法");
        int[] ints = {8, 4, 5, 7, 2, 50, 27};
        Arrays.stream(ints).sorted().forEach(num -> System.out.println(num + ";"));
    }

    // 七 parallelStream是流并行处理程序的代替方法
    public static void parallelStreamTest() {
        System.out.println("七 parallelStream");
        Long count = strings.parallelStream().filter(string -> !string.isEmpty()).count();
        System.out.println("并行处理：" + count);
    }

    // 八 Collectors实现了很多聚合函数，例如将流转换成集合和聚合元素
    public static void colletorsTest() {
        System.out.println("八 Collectors");
        List<String> filter = strings.stream().filter(string -> !string.isEmpty()).collect(Collectors.toList());
        String mergedString = strings.stream().filter(string -> !string.isEmpty()).collect(Collectors.joining(","));
        System.out.println("筛选列表: " + filter);
        System.out.println("合并字符串: " + mergedString);
    }

    // 九 统计，一些产生统计结果的收集器也非常有用。它们主要用于int/double/long等基本类型上，它们可以用来产生如下的统计
    public static void statistialTest() {
        List<Integer> numberList = Arrays.asList(3, 5, 4);
        IntSummaryStatistics statistics = numberList.stream().mapToInt((x) -> x * x).summaryStatistics();

        System.out.println("statistics=" + statistics);
        System.out.println("最大的数=" + statistics.getMax());
        System.out.println("最小的数=" + statistics.getMin());
        System.out.println("所有数之和=" + statistics.getSum());
        System.out.println("平均数=" + statistics.getAverage());
        System.out.println("数据个数=" + statistics.getCount());
    }

    public static void a() {
        List<Student> students = new ArrayList<>();
        students.add(new Student(12, "aa", 12));
        students.add(new Student(13, "aa", 13));
        students.add(new Student(13, "bb", 13));
        students.add(new Student(15, "aa", 15));
        students.add(new Student(15, "bb", 15));
        students.add(new Student(15, "cc", 15));

        // 根据年龄排序，取最小值
        int minOrelse = students.stream().map(Student::getAge).sorted((x, y) -> x.compareTo(y)).findFirst().orElse(0);
        System.out.println("minOrelse=" + minOrelse);

        // 根据年龄排序，取最大值
        int maxOrelse = students.stream().map(Student::getAge).sorted((x, y) -> y.compareTo(x)).findFirst().orElse(0);
        System.out.println("maxOrelse=" + maxOrelse);

        // 根据name字段去重 distinct
        long ss = students.stream().map(Student::getUserName).distinct().count();
        System.out.println("ss=" + ss);

        // 根据name字段分组 groupingBy
        System.out.println("输出 group" + students.stream()
                .collect(Collectors.groupingBy(Student::getUserName, Collectors.toList()))
                .entrySet());

        // 根据name字段分组，并且把每一组的年龄加起来
        List list = students.stream()
                .collect(Collectors.groupingBy(Student::getUserName, Collectors.toList()))
                .entrySet()
                .stream()
                .map(stuArry -> {
                    Student student = new Student();
                    student.setUserName(stuArry.getValue().get(0).getUserName());

                    student.setAge(stuArry.getValue().stream().map(Student::getAge).reduce((x, y) -> x + y).get());
                    return student;
                }).collect(Collectors.toList());
        System.out.println("输出 group+add" + list.toString());
    }
}
