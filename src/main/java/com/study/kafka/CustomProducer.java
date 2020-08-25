package com.study.kafka;

import org.apache.kafka.clients.producer.*;

import java.util.Properties;

/**
 * 生产数据：有两种方式
 *
 * @descript 生产者
 * @date 2020-06-16
 */
public class CustomProducer {

    // 定义主题
    public static String topic = "test";

    public static void main(String[] args) throws InterruptedException {
        Properties props = new Properties();
        // Kafka服务端的主机名和端口号
//        props.put("bootstrap.servers", "10.91.159.54:9092");
        props.put("bootstrap.servers", "127.0.0.1:9092");
        // 等待所有副本节点的应答
        props.put("acks", "all");
        // 消息发送最大尝试次数
        props.put("retries", 0);
        // 一批消息处理大小
        props.put("batch.size", 16384);
        // 请求延时
        props.put("linger.ms", 1);
        // 发送缓存区内存大小
        props.put("buffer.memory", 33554432);
        // key序列化
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        // value序列化
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        KafkaProducer<String, String> producer = new KafkaProducer<>(props);

        // 不回调
        send(producer);

        // 回调
//        sendCallBack(producer);

    }

    /**
     * 方式一 生产数据
     *
     * @param producer
     * @date 2020-06-16
     */
    public static void send(Producer<String, String> producer) {
        for (int i = 0; i < 50; i++) {
            producer.send(new ProducerRecord<String, String>(topic, Integer.toString(i), "hello world-" + i));
        }
        producer.close();
    }

    /**
     * 方式二 生产数据-回调
     *
     * @param producer
     * @date 2020-06-16
     */
    public static void sendCallBack(Producer<String, String> producer) {
        //
        try {
            for (int i = 0; i < 10; i++) {
                Thread.sleep(500);
                producer.send(new ProducerRecord<String, String>(topic, "hh" + i), new Callback() {
                    @Override
                    public void onCompletion(RecordMetadata metadata, Exception e) {
                        if (metadata != null) {
                            System.out.println(metadata.partition() + "----" + metadata.offset());
                        }
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            producer.close();
        }
    }

}
