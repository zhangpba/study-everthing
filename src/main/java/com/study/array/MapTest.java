package com.study.array;

import java.util.HashMap;
import java.util.Map;

public class MapTest {

    public static void main(String[] args) {
        // 我们不希望Map返回的值为null，所以理所当然的想到了getOrDefault方法，
        // 我之前的想法是getOrDefault方法会在get到的结果为null时返回默认值
        Map<String, Integer> map = new HashMap<>();
        map.put("a", null);
        map.put("b", 2);
        map.put("c", 3);
        System.out.println(map.getOrDefault("a", 100));
        System.out.println(map.getOrDefault("d", 200));
        //从以上代码可以看出来，getOrDefault的默认值的生效条件并不是get的值为null，而是containsKey的结果为false
    }
}
