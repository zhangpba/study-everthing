package com.study.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

/**
 * java中操作redis
 *
 * @author Administrator
 */
public class RedisTest {

    // 连接redis
    private static synchronized Jedis getJedis(String ip, int port) {
        final Jedis jr = new Jedis(ip, port);
        jr.auth("123456");// 密码

        System.out.println("连接成功: \t" + jr.ping());
        return jr;
    }

    // 关闭redis连接
    private static synchronized void closeJedis(Jedis jr) {
        jr.close();
    }

    // 判断是否存在key
    private static boolean keyIsExist(Jedis jd, String key) {
        return jd.exists(key);
    }

    // 删除元素
    private static void delKey(Jedis jd, String key) {
        jd.del(key);
    }

    // 操作String
    private static synchronized void opString(Jedis jr) {
        if (keyIsExist(jr, "company")) {
            delKey(jr, "company");
        }
        jr.set("company", "oracle");
        System.out.println(jr.get("company"));
    }

    // 操作hash
    private static void opHash(Jedis jr) {
        if (keyIsExist(jr, "user1")) {
            delKey(jr, "user1");
        }
        ConcurrentHashMap<String, String> chm = new ConcurrentHashMap<String, String>();
        chm.put("name", "Tom");
        chm.put("age", "26");
        chm.put("major", "computer");
        jr.hmset("user1", chm);
        System.out.println(jr.hgetAll("user1").get("name"));
        System.out.println(jr.hgetAll("user1").get("age"));
        System.out.println(jr.hgetAll("user1").get("major"));
    }

    // 操作list
    private static synchronized void opList(Jedis jr) {
        if (keyIsExist(jr, "colorSet")) {
            delKey(jr, "colorSet");
        }
        jr.lpush("colorSet", "red", "blue", "yellow", "red");
        System.out.println(jr.lrange("colorSet", 0, 10));
    }

    // 操作Set
    private static synchronized void opSet(Jedis jr) {
        if (keyIsExist(jr, "hobby")) {
            delKey(jr, "hobby");
        }
        jr.sadd("hobby", "running", "basketball", "volleyball", "volleyball");
        System.out.println(jr.smembers("hobby"));
        System.out.println(jr.smembers("hobby").size());
    }

    // 操作ZSet
    private static synchronized void opZset(Jedis jr) {
        if (keyIsExist(jr, "hobby2"))
            delKey(jr, "hobby2");
        jr.zadd("hobby2", 2.0, "running");
        jr.zadd("hobby2", 2.0, "basketball");
        jr.zadd("hobby2", 2.0, "volleyball");
        jr.zadd("hobby2", 2.0, "volleyball");
        for (String s : jr.zrangeByScore("hobby2", 0, 4))
            System.out.println(s);
    }

    // 数据库备份
    private static synchronized void save(Jedis jr) {
        jr.save();
    }

    // 事务控制
    private static void tranController(Jedis jr) throws IOException {
        System.out.println("**************************************开启事务**************************************");
        Transaction trans = jr.multi();
        System.out.println("*********java Redis 开始执行事务*********");
        trans.exec();
        System.out.println("*********java Redis operating String*********");
        opString(jr);
        System.out.println("*********java Redis operating String over*********");
        System.out.println("*********java Redis operating hash*********");
        opHash(jr);
        System.out.println("*********java Redis operating hash over*********");
        System.out.println("*********java Redis operating List*********");
        opList(jr);
        System.out.println("*********java Redis operating List over*********");
        System.out.println("*********java Redis operating Set*********");
        opSet(jr);
        System.out.println("*********java Redis operating Set over*********");
        System.out.println("*********java Redis operating zSet*********");
        opZset(jr);
        System.out.println("*********java Redis operating zSet over*********");
        System.out.println("*********java Redis save redis db*********");
        save(jr);
        System.out.println("*********java Redis save redis db over*********");
        trans.close();
        System.out.println("**************************************关闭事务**************************************");
        System.out.println("*********java Redis close redis db connection*********");
        closeJedis(jr);
        System.out.println("*********java Redis close redis db connection over*********");
    }


    public static void main(String[] args) throws IOException {
        Jedis jr = getJedis("127.0.0.1", 6380);
        tranController(jr);
    }

}
