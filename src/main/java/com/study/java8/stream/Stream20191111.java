package com.study.java8.stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Stream20191111 {
    public static void main(String[] args) {

        // 下面提供最常见的几种构造Stream的例子:
        // 1. Individual values
        Stream stream = Stream.of("a", "b", "c");

        // 2. Arrays
        String[] strings = new String[]{"a", "b", "c"};
        stream = Stream.of(strings);
        stream = Arrays.stream(strings);

        // 3. Collections
        List<String> list = Arrays.asList(strings);
        stream = list.stream();


//        IntStream.of(new int[]{1, 2, 3}).forEach(System.out::print);
//        IntStream.range(1, 3).forEach(System.out::print);
//        IntStream.rangeClosed(1, 3).forEach(System.out::print);

        // map生成的是个1:1映射，每个输入元素都按照规则转换成为另外一个元素
//        List<Integer> nums = Arrays.asList(1, 2, 3, 4);
//        List<Integer> squareNums = nums.stream().map(n -> n * n).collect(Collectors.toList());
//        for (Integer i : squareNums) {
//            System.out.println(i);
//        }

        // 还有一些场景，是一对多映射关系的，这时需要flatMap
//        Stream<List<Integer>> inputStream = Stream.of(Arrays.asList(1), Arrays.asList(2, 3), Arrays.asList(4, 5, 6));
//        Stream<Integer> outputStream = inputStream.flatMap((childList) -> childList.stream());
//        outputStream.forEach(out->System.out.println(out));

        // filter对原始Stream进行某项测试，通过测试的元素被留下来生成一个新Stream。
        // 留下偶数
        Integer[] sixNums = {1, 2, 3, 4, 5, 6};
        Integer[] evens = Stream.of(sixNums).filter(n -> n % 2 == 0).toArray(Integer[]::new);
        for (Integer i : evens) {
            System.out.println(i);
        }

        // forEach方法接收一个Lambda表达式，然后在Stream的每一个元素上执行该表达式。
        // forEach 不能修改自己包含的本地变量值，也不能用break/return之类的关键字提前结束循环。
        // 另外一点需要注意，forEach是terminal操作。因此，它执行后，Stream 的元素就被“消费”掉了，你无法对一个Stream进行两次terminal运算。下面的代码是错误的：。

        // peek 对每个元素执行操作并返回一个新的 Stream
        Stream.of("one", "two", "three", "four").filter(e -> e.length() > 3)
                .peek(e -> System.out.println("Filtered value: " + e)).map(String::toUpperCase)
                .peek(e -> System.out.println("Mapped value: " + e)).collect(Collectors.toList());


    }

}
