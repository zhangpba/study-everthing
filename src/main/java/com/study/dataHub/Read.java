package com.study.dataHub;

import com.aliyun.datahub.client.DatahubClient;
import com.aliyun.datahub.client.DatahubClientBuilder;
import com.aliyun.datahub.client.auth.AliyunAccount;
import com.aliyun.datahub.client.common.DatahubConfig;
import com.aliyun.datahub.client.http.HttpConfig;
import com.aliyun.datahub.client.model.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Read {

    public void read() {
        // Endpoint以Region: 华东1为例，其他Region请按实际情况填写
        String endpoint = "http://dh-cn-hangzhou.aliyuncs.com";
        String accessId = "<YourAccessKeyId>";
        String accessKey = "<YourAccessKeySecret>";

        // 创建DataHubClient实例
        DatahubClient datahubClient = DatahubClientBuilder.newBuilder()
                .setDatahubConfig(
                        new DatahubConfig(endpoint,
                                new AliyunAccount(accessId, accessKey), true)).setHttpConfig(
                        new HttpConfig().setConnTimeout(10000)).build();
        //------------------读写数据 start--------------------------------------------------
        // 读数据
        // 读取Topic下的数据，需要指定对应的shard，同时需要指定数据读取的游标位置Cursor。Cursor的获取方式有四种，分别是OLDEST, LATEST, SEQUENCE, SYSTEM_TIME。
        // 1. OLDEST : 表示获取的cursor指向当前有效数据中时间最久远的record。
        // 2. LATEST : 表示获取的cursor指向当前最新的record。
        // 3. SEQUENCE : 表示获取的cursor指向该序列的record。
        // 4. SYSTEM_TIME : 表示获取的cursor指向该大于等于该时间戳的第一条record。

        // 项目名
        String projectName = "test_project";
        String topicName = "topic_name";
        // 项目描述
        String comment = "project comment";
        String shardId = "<id1>";

        // 1. OLDEST用法示例
        String oldestCursor = datahubClient.getCursor(projectName, topicName, shardId, CursorType.OLDEST).getCursor();

        // 2. LATEST用法示例
        String latestCursor = datahubClient.getCursor(projectName, topicName, shardId, CursorType.LATEST).getCursor();

        // 3. SEOUENCE 用法示例
        // 获取新数据的sequence
        long seq = datahubClient.getCursor(projectName, topicName, shardId, CursorType.LATEST).getSequence();
        // 获取最新的十条数据
        String seqCursor = datahubClient.getCursor(projectName, topicName, shardId, CursorType.SEQUENCE, seq - 9).getCursor();

        // 4. SYSTEM_TIME的用法示例
        String time = "2019-07-29 10:00:00";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-DD HH:mm:ss");
        long timestamp = 0L;
        try {
            Date date = simpleDateFormat.parse(time);
            timestamp = date.getTime();
        } catch (ParseException e) {
            System.exit(-1);
        }
        // 获取时间time之后的数据读取位置
        String timeCursor = datahubClient.getCursor(projectName, topicName, shardId, CursorType.SYSTEM_TIME, timestamp).getCursor();


        // 二。读取Tuple topic数据
        String projectName1 = "<YourProjectName>";
        String topicName1 = "<YourTopicName>";
        String shardId1 = "0";
        int recordLimit1 = 10;
        // Tuple Topic schema
        RecordSchema schema = new RecordSchema();
        schema.addField(new Field("field1", FieldType.STRING));
        schema.addField(new Field("field2", FieldType.BIGINT));// 获取cursor, 这里获取最新的一条记录
        // 注: 正常情况下，getCursor只需在初始化时获取一次，然后使用getRecords的nextCursor进行下一次读取
        String cursor1 = datahubClient.getCursor(projectName1, topicName1, shardId1, CursorType.LATEST).getCursor();
        // 读取数据
        GetRecordsResult result = datahubClient.getRecords(projectName1, topicName1, shardId1, schema, cursor1, recordLimit1);
        // 如果有数据则处理，无数据需sleep后重新读取
        if (result.getRecordCount() > 0) {
            for (RecordEntry entry : result.getRecords()) {
                TupleRecordData data = (TupleRecordData) entry.getRecordData();
                System.out.println("field1:" + data.getField("field1"));
                System.out.println("field2:" + data.getField("field2"));
            }
        }

        // Blob topic数据
        String projectName2 = "<YourProjectName>";
        String topicName2 = "<YourTopicName>";
        String shardId2 = "0";
        int recordLimit2 = 10;
        // 获取cursor，这里获取第一条记录
        // 注: 正常情况下，getCursor只需在初始化时获取一次，然后使用getRecords的nextCursor进行下一次读取
        String cursor2 = datahubClient.getCursor(projectName2, topicName2, shardId2, CursorType.LATEST).getCursor();
        // 读取数据
        GetRecordsResult result2 = datahubClient.getRecords(projectName2, topicName2, shardId2, cursor2, recordLimit2);
        // 如果有数据则处理，无数据需sleep后重新读取
        if (result2.getRecordCount() > 0) {
            for (RecordEntry entry : result2.getRecords()) {
                BlobRecordData data = (BlobRecordData) entry.getRecordData();
//                System.out.println("value"+new String(data.getData(), Charsets.UTF_8));
            }

        }

    }
}
