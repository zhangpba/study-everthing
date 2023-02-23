package com.study.niuke.test40;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @author zhangpba
 * @description HJ23 删除字符串中出现次数最少的字符
 * @date 2023/2/23
 */
public class Test23 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String info = scanner.nextLine();
        process(info);
    }

    private static void process(String info) {
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < info.length(); i++) {
            if (map.get(info.charAt(i) + "") == null) {
                map.put(info.charAt(i) + "", 1);
            } else {
                map.put(info.charAt(i) + "", map.get(info.charAt(i) + "") + 1);
            }
        }

        // 计算最小次数
        int n = Collections.min(map.values());
        // 替换
        for (String key : map.keySet()) {
            if (map.get(key) == n) {
                info = info.replaceAll(key, "");
            }
        }


//        // 计算最小次数
//        int min = info.length();
//        String minKey = "";
//        for (String key : map.keySet()) {
//            // 最小值判断
//            if (map.get(key) < min) {
//                min = map.get(key);
//                minKey = key;
//            } else if (map.get(key) == min) {
//                // 等于的时候，最小的字符拼接在一起
//                minKey = minKey + key;
//                min = map.get(key);
//            }
//        }
//
//        // 替换
//        for (int i = 0; i < minKey.length(); i++) {
//            info = replace(info, minKey.charAt(i) + "");
//        }
        System.out.println(info);
    }

    private static String replace(String info, String minKey) {
        return info.replaceAll(minKey, "");
    }
}
