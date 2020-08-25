package com.study.dataHub;

import com.aliyun.datahub.client.DatahubClient;
import com.aliyun.datahub.client.DatahubClientBuilder;
import com.aliyun.datahub.client.auth.AliyunAccount;
import com.aliyun.datahub.client.common.DatahubConfig;
import com.aliyun.datahub.client.http.HttpConfig;
import com.aliyun.datahub.client.model.*;

import java.util.ArrayList;
import java.util.List;

public class Writer {
    public void write1() {
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


        // 一。写入Tuple topic
        String projectName = "<YourProjectName>";
        String topicName = "<YourTopicName>";
        String shardId = "0";

        // Tuple Topic schema
        RecordSchema schema = new RecordSchema();
        schema.addField(new Field("field1", FieldType.STRING));
        schema.addField(new Field("field2", FieldType.BIGINT));

        // generate 10 records
        List<RecordEntry> recordEntries = new ArrayList<RecordEntry>();
        for (int i = 0; i < 10; i++) {
            RecordEntry recordEntry = new RecordEntry();
            // set attributes
            recordEntry.addAttribute("key1", "value1");
            // set tuple data
            TupleRecordData data = new TupleRecordData(schema);

            data.setField("field1", "hellworld");
            data.setField("field2", 1212121);
            recordEntry.setRecordData(data);

            recordEntries.add(recordEntry);
        }

        datahubClient.putRecordsByShard(projectName, topicName, shardId, recordEntries);

    }


    public void write2() {
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

        // 二。写入Blob topic
        String projectName = "<YourProjectName>";
        String topicName = "<YourTopicName>";
        String shardId = "0";
        // generate 10 records
        List<RecordEntry> recordEntries = new ArrayList<RecordEntry>();

        for (int i = 0; i < 10; ++i) {
            RecordEntry recordEntry = new RecordEntry();
            // set attributes
            recordEntry.addAttribute("key1", "value1");
            // set blob data
            BlobRecordData data = new BlobRecordData("123456".getBytes());
            recordEntry.setRecordData(data);
            recordEntries.add(recordEntry);
        }
        // 服务端从2.12版本开始支持，之前版本请使用putRecords接口
        datahubClient.putRecordsByShard(projectName, topicName, shardId, recordEntries);
    }
}
