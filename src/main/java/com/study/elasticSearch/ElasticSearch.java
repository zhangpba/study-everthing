package com.study.elasticSearch;

import org.apache.http.HttpHost;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ElasticSearch {

    private static final Logger logger = LoggerFactory.getLogger(ElasticSearch.class);

    public static RestHighLevelClient client = null;

    public static void main(String[] args) throws Exception {
//        client = creatClient();
        insertOrUpdate();
        long id = 3;
//        get(id);
//        delete(id);
        search();

    }

    // 创建客户端对象
    public static RestHighLevelClient creatClient() {
        // 如果是多个ip
        // String[] ips = {"192.168.85.133:9200","192.168.85.133:9400"}
        String[] ips = {"10.91.159.54:9200"};
        HttpHost[] httpHost = new HttpHost[ips.length];
        for (int i = 0; i < ips.length; i++) {
            httpHost[i] = HttpHost.create(ips[i]);
        }
        RestClientBuilder builder = RestClient.builder(httpHost);
        RestHighLevelClient client = new RestHighLevelClient(builder);
        logger.info("client：{}", client);

        return client;
    }

    // 插入和跟新文档
    public static void insertOrUpdate() throws Exception {
        RestHighLevelClient client = creatClient();
        Map map = new HashMap();
        map.put("id", "3");
        map.put("user", "3");
        map.put("titile", "架构师");
        map.put("desc", "王五");
        IndexRequest request = new IndexRequest("accounts", "person", map.get("id") + "");

        request.source(map);
        client.index(request);
    }

    // 根据文档id删除文档
    public static void delete(Long id) throws Exception {
        RestHighLevelClient client = creatClient();
        DeleteRequest request = new DeleteRequest("accounts", "person", id + "");
        client.delete(request);
    }

    // 通过文档id获取文档
    public static Object get(Long id) throws Exception {
        RestHighLevelClient client = creatClient();
        GetRequest request = new GetRequest("accounts", "person", id + "");
        GetResponse response = client.get(request);
        Map<String, Object> source = response.getSource();
        logger.info("查出的数据：{}", source);

        return source;
    }

    // 搜索文档
    public static void search() throws Exception {
        RestHighLevelClient client = creatClient();
        SearchRequest request = new SearchRequest();
        request.indices("accounts");
        request.types("person");
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        HighlightBuilder highlightBuilder = new HighlightBuilder();
        sourceBuilder.highlighter(highlightBuilder);
        request.source(sourceBuilder);
        SearchResponse response = client.search(request);
        SearchHits searchHits = response.getHits();
        SearchHit[] searchHitArray = searchHits.getHits();
        List<Object> data = new ArrayList<Object>();
        for (SearchHit hit : searchHitArray) {
            Map<String, Object> source = hit.getSourceAsMap();
            data.add(source);
        }
        logger.info("data：{}", data);
    }


}
