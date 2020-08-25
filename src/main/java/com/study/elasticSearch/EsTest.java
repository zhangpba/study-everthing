package com.study.elasticSearch;

import org.apache.http.HttpHost;
import org.elasticsearch.action.admin.indices.alias.Alias;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * https://www.cnblogs.com/wangzhuxing/p/9609127.html
 * 2020-06-17
 */
public class EsTest {

    private static final Logger logger = LoggerFactory.getLogger(EsTest.class);

    public static void main(String[] args) {

        RestHighLevelClient client = getClient();
        try {

            // 1 创建索引名
            CreateIndexRequest request = new CreateIndexRequest("accounts");

            // 2 索引配置
            request.settings(Settings.builder()
                    .put("index.number_of_shards", 5)
                    .put("index.number_of_replicas", 2)// 副本数
                    .put("analysis.analyzer.default.tokenizer", "standard")
            );

            // 3.2方式二、给出封装成Map
//            Map<String, Object> jsonMap = new HashMap<>();
//            Map<String, Object> message = new HashMap<>();
//            message.put("type", "person");
//            Map<String, Object> properties = new HashMap<>();
//            properties.put("message", message);
//            Map<String, Object> doc = new HashMap<>();
//            doc.put("properties", properties);
//            jsonMap.put("doc", doc);
//            request.mapping("doc", jsonMap);


            XContentBuilder builder = XContentFactory.jsonBuilder();
            builder.startObject();
            {
                builder.startObject("doc");
                {
                    builder.startObject("properties");
                    {
                        builder.startObject("message");
                        {
                            builder.field("type", "text");
                        }
                        builder.endObject();
                        builder.startObject("message1");
                        {
                            builder.field("type", "text");
                        }
                        builder.endObject();
                    }
                    builder.endObject();
                }
                builder.endObject();
            }
            builder.endObject();
            request.mapping("doc", builder);


            // 4 设置索引名称
            request.alias(new Alias("lib1"));

            // 5 发送请求
            // 5.1 同步方式
            CreateIndexResponse response = client.indices().create(request);

            boolean acknowledged = response.isAcknowledged();
            boolean shardsAcknowledged = response.isShardsAcknowledged();

            logger.info("请求结果---------------");
            logger.info("acknowledged:" + acknowledged);
            logger.info("shardsAcknowledged:" + shardsAcknowledged);


        } catch (Exception e) {
            logger.error("异常...");
            e.printStackTrace();
        } finally {
            close(client);
        }


    }

    // 获取客户
    public static RestHighLevelClient getClient() {
        // 初始化
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost("10.91.159.54", 9200, "http"),
                        new HttpHost("10.91.159.42", 9200, "http"),
                        new HttpHost("10.91.159.38", 9200, "http")));

        return client;
    }


    // 不再使用的时候关闭Client
    public static void close(RestHighLevelClient client) {
        try {
            client.close();
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }


}
