package com.study.array;

import java.util.LinkedList;

public class LinkedListTest {

    public static void main(String[] args) {
        LinkedList<String> list = new LinkedList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");

        String first = list.getFirst();
        String last = list.getLast();
        System.out.println("链表的第一个元素是 : " + first);
        System.out.println("链表最后一个元素是 : " + last);

    }
}
