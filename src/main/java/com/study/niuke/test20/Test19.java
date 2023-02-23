package com.study.niuke.test20;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * @author zhangpba
 * @description HJ19 简单错误记录
 * @date 2023/2/23
 */
public class Test19 {

    public static void main(String[] args) {
        // 用list记录顺序
        List<String> list = new ArrayList<>();
        // 用map记录个数
        Map<String, Integer> map = new HashMap<>();

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            // 输入数字串
            String info = scanner.nextLine();
            String result = process(info);
            if (!list.contains(result)) {
                map.put(result, 1);
                list.add(result);
            } else {
                int count = map.get(result) + 1;
                map.put(result, count);
            }
        }

        // 输出最后8条
        if (list.size() > 8) {
            list = list.subList(list.size() - 8, list.size());
        }
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i) + " " + map.get(list.get(i)));
        }
    }

    private static String process(String info) {
        String result = new String();
        String[] split = info.split(" ");

        String path = split[0];
        // "\"最后出现的下标
        int lastIndexOf = path.lastIndexOf("\\");
        // 截取文件名称
        String fileName = path.substring(lastIndexOf + 1);
        if (fileName.length() > 16) {
            fileName = fileName.substring(fileName.length() - 16);
        }

        result = fileName + " " + split[1];
        return result;
    }
}
