package com.study.mongdb;

import com.mongodb.client.ListIndexesIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.IndexOptions;
import org.bson.Document;
import org.bson.conversions.Bson;

/**
 * @author zhangpba
 * @description mongodb测试类
 * @date 2023/1/29
 */
public class MongodbDemo {

    static String dbName = "test_db";
    static String collName = "test_collection";
    static MongoCollection<Document> collection = MongodbUtils.instance.getCollection(dbName, collName);

    public static void main(String[] args) {
//        createIndex();

//        insertMany();

//        query();

//        queryById();

        queryMany();
    }

    /**
     * 创建索引
     */
    public static void createIndex() {
        collection.createIndex(new Document("validata", 1)); // 创建索引
        collection.createIndex(new Document("id", 1));
        collection.createIndex(new Document("id", 2));
        collection.createIndex(new Document("ut_wos", 1), new IndexOptions().unique(true));//创建唯一索引
//        coll.dropIndexes();//删除索引
//        coll.dropIndex("validata_1");//根据索引名删除某个索引
        ListIndexesIterable<Document> list = collection.listIndexes();//查询所有索引
        for (Document document : list) {
            System.out.println("查询结果：" + document.toJson());
        }
    }

    /**
     * 插入多个文档
     */
    public static void insertMany() {
        // 插入多条
        for (int i = 1; i <= 4; i++) {
            Document doc = new Document();
            doc.put("ut_wos", i + 30);
            doc.put("name", "zhoulf");
            doc.put("school", "NEFU" + i);
            Document interests = new Document();
            interests.put("game", "game" + i);
            interests.put("ball", "ball" + i);
            doc.put("interests", interests);
            collection.insertOne(doc);
        }
    }

    /**
     * 查询文档
     */
    public static void query() {
        System.out.println("集合中的文档数量：" + collection.count());
        ListIndexesIterable<Document> list = collection.listIndexes();//查询所有索引
        // 查询索引
        for (Document document : list) {
            System.out.println("查询索引结果：" + document.toJson());
        }

        // 查询文档
        MongoCursor<Document> sd = collection.find().iterator();
        while (sd.hasNext()) {
            Document doc = sd.next();
            String id = doc.get("_id").toString();
            System.out.println("查询文档_id：" + id);
            System.out.println("查询文档结果：" + doc.toJson());
        }
    }

    // 根据ID查询
    public static void queryById() {
        String id = "63d6345e54057e73c61232ba";
        Document doc = MongodbUtils.instance.findById(collection, id);
        System.out.println("根据ID查询的文档：" + doc.toJson());
    }

    // 根据多个条件查询
    public static void queryMany() {
        // 条件一
        Bson bson1 = Filters.eq("name", "zhoulf");
        // 条件二
        Bson bson2 = Filters.eq("school", "NEFU2");
        // 同时满足条件一和条件二
        Bson bson = Filters.and(bson1,bson2);
        MongoCursor<Document> cursor = collection.find(bson).iterator();
        while (cursor.hasNext()) {
            Document doc = cursor.next();
            System.out.println(doc.toJson());
        }
    }
}
