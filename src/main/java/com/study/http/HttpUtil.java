package com.study.http;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;

/**
 * 2020-04-29
 */
public class HttpUtil {

    public static void main(String[] args) throws Exception {
        //httpclient连接池

        //创建连接池
        PoolingHttpClientConnectionManager cManager = new PoolingHttpClientConnectionManager();
        //设置最大连接数
        cManager.setMaxTotal(50);
        //设置每个主机地址的并发数
        cManager.setDefaultMaxPerRoute(20);

        //执行i请求
        doGet(cManager);
    }

    private static void doGet(PoolingHttpClientConnectionManager cm) throws Exception {
        //从连接池获取连接,每次不一样
        CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(cm).build();
        //设置http get
        HttpGet httpGet = new HttpGet("https://www.baidu.com");
        //设置请求参数
        RequestConfig config = RequestConfig.custom().setConnectTimeout(5000) //连接超时时间
                .setConnectionRequestTimeout(500) //从线程池中获取线程超时时间
                .setSocketTimeout(8000) //设置数据超时时间
                .setStaleConnectionCheckEnabled(true) //提交请求前检查连接是否可用
                .build();

        httpGet.setConfig(config);

        //返回数据
        CloseableHttpResponse response = null;

        try {
            response = httpClient.execute(httpGet);
            String con = EntityUtils.toString(response.getEntity(), "utf-8");
            System.out.println(con);
        } finally {
            if (response != null) {
                response.close();
            }
            httpClient.close();
        }

    }
}
