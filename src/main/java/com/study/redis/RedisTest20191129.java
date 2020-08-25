package com.study.redis;

import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * redis的数据类型
 * 1 String(字符串)
 * 2 Hash(哈希)
 * 3 List(列表)
 * 4 Set(集合)
 * 5 zset(sorted set：有序集合)
 *
 * @author zhangpba
 * @date 2019-11-29
 */
public class RedisTest20191129 {

    public static void main(String[] args) {
        connectTest();


//        Jedis jedis = RedisUtil.getJedis();
//        String result = jedis.get("license:licenseInfo");
//        JSONArray array = (JSONArray) JSONArray.parse(result);
//
//        System.out.println(result);
//        System.out.println(result);

    }


    public static void connectTest() {
        // 连接本地的redis服务
        Jedis jedis = RedisUtil.getJedis();
        System.out.println("连接成功");

        // 1 字符串(String)        字符串是一个key一个value的形式存储
        if (jedis.exists("redisStr")) {
            jedis.del("redisStr");
        }
        jedis.set("redisStr", "redisStrVule");
        System.out.println("redis 存储的字符串为: " + jedis.get("redisStr"));

        // 2 Hash(哈希)
        if (jedis.exists("hashSetTest")) {
            jedis.del("hashSetTest");
        }
        Map<String, String> map = new HashMap<>();
        map.put("a", "aa1");
        map.put("b", "bb2");
        map.put("c", "cc3");
        jedis.hmset("hashSetTest", map);
        Map<String, String> hashSetMap = jedis.hgetAll("hashSetTest");
        System.out.println(hashSetMap);
        hashSetMap.forEach((k, v) -> System.out.println("key:" + k + " value:" + v));

        // 3 列表(List)
        if (jedis.exists("site-list")) {
            jedis.del("site-list");
        }
        jedis.lpush("site-list", "Runoob");
        jedis.lpush("site-list", "Google");
        jedis.lpush("site-list", "Taobao");

        List<String> list = jedis.lrange("site-list", 0, 10);
        list.forEach(str -> System.out.println("列表项为：" + str));

        // 4 集合（Set）
        if (jedis.exists("saddTest")) {
            jedis.del("saddTest");
        }
        jedis.sadd("saddTest", "a", "b", "c");
        Set<String> saddTests = jedis.smembers("saddTest");
        System.out.println("saddTests：" + saddTests);
        saddTests.forEach(str -> System.out.println("sadd测试输出：" + str));

        // 5 zset(sorted set：有序集合)--按照score进行排序
        if (jedis.exists("zaddTest")) {
            jedis.del("zaddTest");
        }
        jedis.zadd("zaddTest", 3, "3qw");
        jedis.zadd("zaddTest", 2, "2er");
        jedis.zadd("zaddTest", 1, "1ty");
        jedis.zadd("zaddTest", 4, "4ui");
        jedis.zrange("zaddTest", 0, 10);

        Set<String> zaddTests = jedis.zrange("zaddTest", 0, 10);
        System.out.println(zaddTests);
        zaddTests.forEach(str -> System.out.println("zaddtest输出数据：" + str));


        // 获取Key实例数据并输出
        Set<String> keys = jedis.keys("*");
        keys.forEach(key -> System.out.println("key实例：" + key));
    }


}
