package com.study.mongdb;

import com.mongodb.client.ListIndexesIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.IndexOptions;
import com.mongodb.client.model.Updates;
import org.bson.Document;

import java.util.Arrays;

/**
 * mongdb测试类
 *
 * @author zhangpba
 * @date 2021-06-15
 */
public class MongodbTest {

    public static void main(String[] args) {
        String dbName = "test";
        String collName = "wd_paper_scie";
        MongoCollection<Document> coll = MongodbUtils.instance.getCollection(dbName, collName);
        coll.createIndex(new Document("validata", 1));//创建索引
        coll.createIndex(new Document("id", 1));
        coll.createIndex(new Document("ut_wos", 1), new IndexOptions().unique(true));//创建唯一索引
//        coll.dropIndexes();//删除索引
//        coll.dropIndex("validata_1");//根据索引名删除某个索引
        ListIndexesIterable<Document> list = coll.listIndexes();//查询所有索引
        for (Document document : list) {
            System.out.println("查询结果：" + document.toJson());
        }

        coll.find(Filters.and(Filters.eq("x", 1), Filters.lt("y", 3)));
        coll.find(Filters.and(Filters.eq("x", 1), Filters.lt("y", 3)));
        coll.find().sort(new Document("id", 1));
        coll.find(new Document("$or", Arrays.asList(new Document("owner", "tom"), new Document("words", new Document("$gt", 350)))));
        coll.updateMany(Filters.eq("validata", 1), Updates.set("validata", 0));
        coll.updateMany(Filters.eq("validata", 1), new Document("$unset", new Document("jigou", "")));//删除某个字段
        coll.updateMany(Filters.eq("validata", 1), new Document("$rename", new Document("affiliation", "affiliation_full")));//修改某个字段名
        coll.updateMany(Filters.eq("validata", 1), new Document("$rename", new Document("affiliationMeta", "affiliation")));
        coll.updateMany(Filters.eq("validata", 1), new Document("$set", new Document("validata", 0)));//修改字段值
        MongoCursor<Document> cursor1 = coll.find(Filters.eq("ut_wos", "WOS:000382970200003")).iterator();
        while (cursor1.hasNext()) {
            Document sd = cursor1.next();
            System.out.println(sd.toJson());
            coll.insertOne(sd);
        }

//        MongoCursor<Document> cursor1 = coll.find(Filters.elemMatch("affInfo", Filters.eq("firstorg", 1))).iterator();
//        while (cursor1.hasNext()) {
//            Document sd = cursor1.next();
//            System.out.println(sd.toJson());
//        }
        //查询只返回指定字段
        // MongoCursor<Document> doc= coll.find().projection(Projections.fields(Projections.include("ut_wos","affiliation"),Projections.excludeId())).iterator();
        //"ut_wos" : "WOS:000382970200003"
        //coll.updateMany(Filters.eq("validata", 1), new Document("$set", new Document("validata", 0)));
        //coll.updateMany(Filters.eq("validata", 0), new Document("$rename", new Document("sid", "ssid")), new UpdateOptions().upsert(false));
        //coll.updateOne(Filters.eq("ut_wos", "WOS:000382970200003"), new Document("$set", new Document("validata", 0)));
        //long isd=coll.count(Filters.elemMatch("affInfo", Filters.elemMatch("affiliationJGList", Filters.eq("sid", 0))));
        // System.out.println(isd);
//        MongoCursor<Document> doc = coll.find(Filters.elemMatch("affInfo", Filters.elemMatch("affiliationJGList", Filters.ne("sid", 0)))).projection(Projections.fields(Projections.elemMatch("affInfo"), Projections.excludeId())).iterator();
//        MongoCursor<Document> doc = coll.find().projection(Projections.include("affInfo", "ssid")).iterator();
//        while (doc.hasNext()) {
//            Document sd = doc.next();
//            System.out.println(sd.toJson());
//
//        }
        MongodbUtils.instance.close();
        // 插入多条
        for (int i = 1; i <= 4; i++) {
            Document doc = new Document();
            doc.put("name", "zhoulf");
            doc.put("school", "NEFU" + i);
            Document interests = new Document();
            interests.put("game", "game" + i);
            interests.put("ball", "ball" + i);
            doc.put("interests", interests);
            coll.insertOne(doc);
        }
        //
                /* MongoCursor<Document> sd =coll.find().iterator();
         while(sd.hasNext()){
             Document doc = sd.next();
             String id =  doc.get("_id").toString();
             List<AffiliationJG> list = new ArrayList<AffiliationJG>();
             AffiliationJG jg = new AffiliationJG();
             jg.setAddress("123");
             jg.setCid(2);
             jg.setCname("eeee");
             jg.setSid(3);
             jg.setSname("rrrr");
             AffiliationJG jg2 = new AffiliationJG();
             jg2.setAddress("3242");
             jg2.setCid(2);
             jg2.setCname("ers");
             jg2.setSid(3);
             jg2.setSname("rasdr");
             list.add(jg);
             list.add(jg2);
             AffiliationList af = new AffiliationList();
             af.setAffiliationAuthos("33333");
             af.setAffiliationinfo("asdsa");
             af.setAffiliationJGList(list);
             JSONObject json = JSONObject.fromObject(af);
             doc.put("affInfo", json);
             MongoDBUtil.instance.updateById(coll, id, doc);
         }*/

        //        Bson bs = Filters.eq("name", "zhoulf");
        //        coll.find(bs).iterator();
        // // 根据ID查询
        // String id = "556925f34711371df0ddfd4b";
        // Document doc = MongoDBUtil2.instance.findById(coll, id);
        // System.out.println(doc);

        // 查询多个
        // MongoCursor<Document> cursor1 = coll.find(Filters.eq("name", "zhoulf")).iterator();
        // while (cursor1.hasNext()) {
        // org.bson.Document _doc = (Document) cursor1.next();
        // System.out.println(_doc.toString());
        // }
        // cursor1.close();

        // 查询多个
        //         MongoCursor<WdPaper> cursor2 = coll.find(WdPaper.class).iterator();
        //         while(cursor2.hasNext()){
        //             WdPaper doc = cursor2.next();
        //             System.out.println(doc.getUt_wos());
        //         }
        // 删除数据库
        // MongoDBUtil2.instance.dropDB("testdb");

        // 删除表
        // MongoDBUtil2.instance.dropCollection(dbName, collName);

        // 修改数据
        // String id = "556949504711371c60601b5a";
        // Document newdoc = new Document();
        // newdoc.put("name", "时候");
        // MongoDBUtil.instance.updateById(coll, id, newdoc);

        // 统计表
        //System.out.println(MongoDBUtil.instance.getCount(coll));

        // 查询所有
        //        Bson filter = Filters.eq("count", 0);
        //        MongoDBUtil.instance.find(coll, filter);
    }
}
