package com.study.dataHub;

import com.aliyun.datahub.client.DatahubClient;
import com.aliyun.datahub.client.DatahubClientBuilder;
import com.aliyun.datahub.client.auth.AliyunAccount;
import com.aliyun.datahub.client.common.DatahubConfig;
import com.aliyun.datahub.client.http.HttpConfig;
import com.aliyun.datahub.client.model.*;

public class DataHubClientTest {

    public static void main(String[] args) {

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

        System.out.println(datahubClient.toString());

        // 项目名
        String projectName = "test_project";
        // 项目描述
        String comment = "project comment";
        //------------------Project操作 start--------------------------------------------------
        // 创建project
        datahubClient.createProject(projectName, comment);
        // 删除project
        datahubClient.deleteProject(projectName);
        // 跟新project:目前只跟新comment
        datahubClient.updateProject(projectName, comment);
        // 查看是否修改成功
        GetProjectResult getProjectResult = datahubClient.getProject(projectName);
        System.out.println(getProjectResult.getComment());
        // 列出project
        ListProjectResult listProjectResult = datahubClient.listProject();
        for (String pName : listProjectResult.getProjectNames()) {
            System.out.println(pName);
        }
        // 查询Project
        GetProjectResult projectResult = datahubClient.getProject(projectName);
        System.out.println(projectResult.getCreateTime() + "\t"
                + projectResult.getLastModifyTime() + "\t"
                + projectResult.getComment());
        //------------------Project操作 end--------------------------------------------------


        //------------------Topic操作 start--------------------------------------------------

        // 定义schema
        RecordSchema schema = new RecordSchema();
        schema.addField(new Field("bigint_field", FieldType.BIGINT));
        schema.addField(new Field("double_field", FieldType.DOUBLE));
        schema.addField(new Field("boolean_field", FieldType.BOOLEAN));
        schema.addField(new Field("timestamp_field", FieldType.TIMESTAMP));
        schema.addField(new Field("string_field", FieldType.STRING));

        // topic shard数量
        int shardCount = 10;
        // 数据生命周期(单位：天)
        int lifeCycle = 1;
        String topicName = "topic_name";

        // 创建Tuple Topic
        datahubClient.createTopic(projectName, topicName, shardCount, lifeCycle, RecordType.TUPLE, schema, comment);

        // 创建Blob topic
        datahubClient.createTopic(projectName, topicName, shardCount, lifeCycle, RecordType.BLOB, comment);

        // 删除 Topic
        datahubClient.deleteTopic(projectName, topicName);
        // 列出Topic
        ListTopicResult listTopicResult = datahubClient.listTopic(projectName);
        for (String tName : listTopicResult.getTopicNames()) {
            System.out.println(tName);
        }

        // 更新Topic大：目前只跟新comment
        datahubClient.updateTopic(projectName, topicName, comment);
        // 查看跟新
        GetTopicResult getTopicResult = datahubClient.getTopic(projectName, topicName);
        System.out.println(getTopicResult.getComment());
        // 查询Topic
        GetTopicResult topicResult = datahubClient.getTopic(projectName, topicName);
        System.out.println(topicResult.getShardCount() + "\t"
                + topicResult.getLifeCycle() + "\t"
                + topicResult.getRecordType() + "\t"
                + topicResult.getComment());
        // Tuple Topic 新增Field
        Field field = new Field("newFieldName", FieldType.STRING, true);
        datahubClient.appendField(projectName, topicName, field);
        //------------------Topic操作 end--------------------------------------------------


        //------------------Shard操作 start--------------------------------------------------
        String shardId = "<id1>";
        // 列出shard
        ListShardResult listShardResult = datahubClient.listShard(projectName, topicName);
        for (ShardEntry entry : listShardResult.getShards()) {
            System.out.println(entry.getShardId() + "\t"
                    + entry.getState() + "\t"
                    + entry.getLeftShardId() + "\t"
                    + entry.getRightShardId());
        }
        // 分裂shard
        SplitShardResult splitShardResult = datahubClient.splitShard(projectName, topicName, shardId);
        for (ShardEntry entry : splitShardResult.getNewShards()) {
            System.out.println(entry.getShardId());
        }
        // 合并shard:合并一个Topic中两个处于ACTIVE状态的Shard,两个Shard的位置必须相邻
        String adjacentShardId = "<id2>";
        MergeShardResult mergeShardResult = datahubClient.mergeShard(projectName, topicName, shardId, adjacentShardId);
        System.out.println(mergeShardResult.getShardId());
        //------------------Shard操作 end--------------------------------------------------


    }

}
