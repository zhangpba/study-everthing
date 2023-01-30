package com.study.mongdb;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.WriteConcern;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.DeleteResult;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

/**
 * mongdb帮助类
 * 安装mongodb        https://blog.csdn.net/weixin_41466575/article/details/105326230
 * java操作mongodb    https://www.cnblogs.com/sa-dan/p/6836055.html
 *
 * @author zhangpba
 * @date 2021-06-15
 */
public enum MongodbUtils {

    /**
     * 定义一个枚举元素，他表示类的一个实例
     */
    instance;

    private static MongoClient mongoClient;

    static {
        System.out.println("===============MongoDBUtil初始化========================");
        String ip = "9.134.236.215";
        int port = 27017;
        mongoClient = new MongoClient(ip, port);
        // 大部分用户使用mongodb都在安全内网下，但如果将mongodb设为安全验证模式，就需要在客户端提供用户名和密码：
        // boolean auth = db.authenticate(myUserName, myPassword);
        MongoClientOptions.Builder options = new MongoClientOptions.Builder();
        options.cursorFinalizerEnabled(true);
        // options.autoConnectRetry(true);// 自动重连true
        // options.maxAutoConnectRetryTime(10); // the maximum auto connect retry time
        // 连接池设置为300个连接,默认为100
        options.connectionsPerHost(300);
        // 连接超时，推荐>3000毫秒
        options.connectTimeout(30000);
        options.maxWaitTime(5000);
        // 套接字超时时间，0无限制
        options.socketTimeout(0);
        // 线程队列数，如果连接线程排满了队列就会抛出“Out of semaphores to get db”错误
        options.threadsAllowedToBlockForConnectionMultiplier(5000);
        options.writeConcern(WriteConcern.SAFE);
        options.build();
    }

    // ------------------------------------共用方法---------------------------------------------------

    /**
     * 获取库实例
     *
     * @param dbName 库名称
     * @return
     */
    public static MongoDatabase getDB(String dbName) {
        if (dbName != null && !"".equals(dbName)) {
            MongoDatabase database = mongoClient.getDatabase(dbName);
            return database;
        }
        return null;
    }

    /**
     * 获取所有数据库名称列表
     *
     * @return
     */
    public MongoIterable<String> getAllDBNames() {
        MongoIterable<String> s = mongoClient.listDatabaseNames();
        return s;
    }

    /**
     * 删除一个数据库
     *
     * @param dbName 库名称
     */
    public static void dropDB(String dbName) {
        getDB(dbName).drop();
    }


    /**
     * 获取具体的集合对象
     *
     * @param dbName   库名称
     * @param collName 集合名称
     * @return
     */
    public MongoCollection<Document> getCollection(String dbName, String collName) {
        if (null == collName || "".equals(collName)) {
            return null;
        }
        if (null == dbName || "".equals(dbName)) {
            return null;
        }
        MongoCollection<Document> collection = mongoClient.getDatabase(dbName).getCollection(collName);
        return collection;
    }

    /**
     * 查询库下的所有集合名（表名）
     *
     * @param dbName 库名称
     * @return
     */
    public List<String> getAllCollections(String dbName) {
        MongoIterable<String> colls = getDB(dbName).listCollectionNames();
        List<String> list = new ArrayList<String>();
        for (String s : colls) {
            list.add(s);
        }
        return list;
    }

    /**
     * 根据主键_id查询文档对象
     *
     * @param coll 集合对象
     * @param id   文档ID
     * @return
     */
    public Document findById(MongoCollection<Document> coll, String id) {
        ObjectId idobj = null;
        try {
            idobj = new ObjectId(id);
        } catch (Exception e) {
            return null;
        }
        Document myDoc = coll.find(Filters.eq("_id", idobj)).first();
        return myDoc;
    }

    /**
     * 删除集合
     *
     * @param dbName   库名称
     * @param collName 集合名称
     */
    public void dropCollection(String dbName, String collName) {
        getDB(dbName).getCollection(collName).drop();
    }

    /**
     * 统计集合中的文档数
     *
     * @param coll 集合对象
     */
    public int getCount(MongoCollection<Document> coll) {
        int count = (int) coll.count();
        return count;
    }

    /**
     * 条件查询
     */
    public MongoCursor<Document> find(MongoCollection<Document> coll, Bson filter) {
        return coll.find(filter).iterator();
    }

    /**
     * 分页查询
     */
    public MongoCursor<Document> findByPage(MongoCollection<Document> coll, Bson filter, int pageNo, int pageSize) {
        Bson orderBy = new BasicDBObject("_id", 1);
        return coll.find(filter).sort(orderBy).skip((pageNo - 1) * pageSize).limit(pageSize).iterator();
    }


    /**
     * 通过ID删除文档
     *
     * @param coll 集合对象
     * @param id   文档ID
     * @return
     */
    public int deleteById(MongoCollection<Document> coll, String id) {
        int count = 0;
        ObjectId _id = null;
        try {
            _id = new ObjectId(id);
        } catch (Exception e) {
            return 0;
        }
        Bson filter = Filters.eq("_id", _id);
        DeleteResult deleteResult = coll.deleteOne(filter);
        count = (int) deleteResult.getDeletedCount();
        return count;
    }

    /**
     * FIXME
     * 修改文档
     *
     * @param coll   集合对象
     * @param id     文档ID
     * @param newdoc 修改后的文档内容
     * @return
     */
    public Document updateById(MongoCollection<Document> coll, String id, Document newdoc) {
        ObjectId _idobj = null;
        try {
            _idobj = new ObjectId(id);
        } catch (Exception e) {
            return null;
        }
        Bson filter = Filters.eq("_id", _idobj);
//        coll.replaceOne(filter, newdoc); // 完全替代
        coll.updateOne(filter, new Document("$set", newdoc));
        return newdoc;
    }


    /**
     * 关闭Mongodb链接
     */
    public void close() {
        if (mongoClient != null) {
            mongoClient.close();
            mongoClient = null;
        }
    }

}
