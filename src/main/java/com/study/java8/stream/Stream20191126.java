package com.study.java8.stream;

import com.study.model.Student;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Stream20191126 {

    private static List<Student> students = new ArrayList<>();


    // 初始化集合
    public static void init() {
        students.add(new Student(1, "a", 32));
        students.add(new Student(2, "b", 32));
        students.add(new Student(3, "c", 32));
        students.add(new Student(4, "d", 33));
        students.add(new Student(5, "e", 33));
        students.add(new Student(6, "f", 33));
        students.add(new Student(7, "g", 34));
        students.add(new Student(8, "h", 34));
        students.add(new Student(9, "i", 35));
        students.add(new Student(10, "j", 35));
    }

    public static void main(String[] args) {
        System.out.println(students.size());
        sort();
    }

    public static void groupBy() {
        // 根据年龄分组
        Set<Map.Entry<Integer, List<Student>>> set = students.stream()
                .collect(Collectors.groupingBy(Student::getAge, Collectors.toList()))
                .entrySet();
        for (Map.Entry<Integer, List<Student>> map : set) {
            System.out.println(map);
        }

        List<Student> studentList = set.stream().map(entry -> {
            Student student = new Student();
            student.setId(entry.getValue().get(0).getId());
            student.setUserName(entry.getValue().get(0).getUserName());

            // 排序求最大值
            int max = entry.getValue().stream().map(Student::getAge).max((x, y) -> x.compareTo(y)).get();
            // 加和
            int total = entry.getValue().stream().map(Student::getAge).reduce((x, y) -> x + y).get();

            student.setMax(max);
            student.setTotal(total);
            return student;
        }).collect(Collectors.toList());

        System.out.println(studentList);
    }

    // 2019-11-26
    public static void list() {
        Stream<Student> list = students.stream().map(student -> {
            Random random = new Random();
            student.setUserName("学生姓名：" + student.getId());
            student.setAge(random.nextInt(100 - (-100) + 1) + (-100));
            return student;
        }).sorted((x, y) -> y.getUserName().compareTo(x.getUserName()));

        list.forEach(stu -> System.out.println(stu + "==" + stu.hashCode()));

        // 把学生中的id拿出来放入Set中，并过滤id为偶数
        Set<Integer> idSet = students.stream().map(Student::getId).filter(id -> id % 2 == 0).collect(Collectors.toSet());
        // // 把学生中的id拿出来放入List中，并过滤id为偶数
        List<Integer> idList = students.stream().map(Student::getId).filter(id -> id % 2 == 0).collect(Collectors.toList());
        idSet.forEach(id -> System.out.println("Set的 id为：" + id));
        idList.forEach(id -> System.out.println("List的 id为：" + id));
    }

    // 2020-05-20
    public static void sort() {
        init();
        //先升序之后利用 reversed进行降序
        students.sort(Comparator.comparing(Student::getUserName).reversed());
        students.forEach(student -> {
            System.out.println("用户：" + student);
        });
    }
}
