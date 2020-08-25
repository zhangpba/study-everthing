package com.study.hashcode;

import java.util.HashMap;

public class HashCodeTest20191126 {

    public static void main(String[] args) {
        People p1 = new People("Jack", 12);
        System.out.println(p1.hashCode());

        HashMap<People, Integer> hashMap = new HashMap<>();
        hashMap.put(p1, 1);

        System.out.println(hashMap.get(new People("Jack", 12)));
    }

}
