package com.study.array;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 给list分批次
 * zhangpba 20190828
 */
public class SplitList {
    private static Logger logger = LoggerFactory.getLogger(SplitList.class);

    public static void main(String[] args) {
        List<String> dataList = new ArrayList<String>();
        for (int i = 0; i < 1288; i++) {
            dataList.add(i + "");
        }

        // 是否为空，空为：true；不空为：false
        boolean isEmpty = dataList.isEmpty();
        logger.info("isEmpty:{}", isEmpty);
        if (isEmpty) {
            return;
        }

        sublistTwo(dataList);

    }

    /**
     * 第一种分段
     *
     * @param dataList
     */
    public static void sublistOne(List<String> dataList) {
        List<List<String>> list = new ArrayList<>();
        // 分批处理
        if (null != dataList & dataList.size() > 0) {
            int pointsDataLimit = 100;//限制条数
            Integer size = dataList.size();

            // 判断是否有必要分批：大于100条数据的进行分批
            if (pointsDataLimit < size) {
                int part = size / pointsDataLimit;
                System.out.println("共有：" + size + "条数据！" + "分为：" + part + "批");
                for (int i = 0; i < part; i++) {
                    // 截取100条
                    List<String> listPage = dataList.subList(0, pointsDataLimit);
                    list.add(listPage);
                    System.out.println("截取的100条" + listPage);
                    // clear 把前100条从集合中去除
                    dataList.subList(0, pointsDataLimit).clear();
                }

                if (!dataList.isEmpty()) {
                    System.out.println("最后剩下的数据：" + dataList);
                }
            } else {
                System.out.println(dataList);
            }
        } else {
            System.out.println("没有数据！！！");
        }

        int size = list.size();
        System.out.println("size=" + size);
    }

    /**
     * 第二种分段
     *
     * @param dataList
     */
    public static void sublistTwo(List<String> dataList) {
        int ftest = 100;//每次取的数据
        int size = dataList.size();
        int temp = size / ftest + 1;
        boolean special = size % ftest == 0;

        List<String> cutList = null;

        for (int i = 0; i < temp; i++) {
            if (i == temp - 1) {
                if (special) {
                    break;
                }
                cutList = dataList.subList(ftest * i, size);
            } else {
                cutList = dataList.subList(ftest * i, ftest * (i + 1));
            }
            System.out.println("第" + (i + 1) + "组：" + cutList.toString());
        }
    }


    // 随机产生1000个名字
    public void add() {
        String[] str1 = {"赵", "钱", "孙", "李", "周", "吴", "郑", "王", "欧阳", "诸葛",
                "桑", "杜", "由", "范", "贾", "彭", "毛", "白", "夏侯", "上官", "司马", "南宫",
                "独孤", "东方", "司徒", "宇文", "公孙", "百里"};
        String[] str2 = {"妙菡", "轩", "洪刚", "刚", "梅", "斌", "柱子", "一根", "流云",
                "嘉信", "建军", "爱国", "乔恩", "胖子", "六爷", "二狗", "三胖", "秋菊"};
        Random rand = new Random();
        for (int i = 0; i < 1000; i++) {
            String name = str1[rand.nextInt(str1.length)] + str2[rand.nextInt(str2.length)];
            System.out.println(name);
        }
    }

}
