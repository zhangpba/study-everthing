package com.study.kafka;

import org.apache.kafka.clients.admin.*;
import org.apache.kafka.common.config.ConfigResource;

import java.util.*;
import java.util.concurrent.ExecutionException;

/**
 * topic操作:https://blog.csdn.net/qq_21383435/article/details/108764354
 *
 * @author zhangpba
 * @date 2021-11-07
 */
public class TopicAdminClient {

    /**
     * topic的名称
     */
    private static final String TOPIC_NAME = "zhangpba_topic";

    public static void main(String[] args) throws Exception {

//        createTopic(TOPIC_NAME); // 创建topic

//        topicLists(); // 查询Topic列表

//        delTopics(); // TODO 删除会报错

//        describeTopics(); // 查询Topic的描述信息

        alterConfig();      // 修改Topic的配置信息
        describeConfig();   // 查询Topic的配置信息

//        incrementalAlterConfig();   // 修改Topic的配置信息
//        describeConfig();            // 查询Topic的配置信息
    }

    /**
     * 配置并创建AdminClient
     *
     * @return
     */
    public static AdminClient adminClient() {
        Properties properties = new Properties();
        properties.setProperty(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        // 创建AdminClient实例
        return AdminClient.create(properties);
    }

    /**
     * 创建topic
     *
     * @param topicName topic名称
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public static void createTopic(String topicName) throws ExecutionException, InterruptedException {
        AdminClient adminClient = adminClient();
        // partition数量
        int numPartitions = 1;
        // 副本数量
        short replicationFactor = 1;

        List<NewTopic> list = new ArrayList<>();
        NewTopic topic = new NewTopic(topicName, numPartitions, replicationFactor);
        list.add(topic);

        CreateTopicsResult result = adminClient.createTopics(list);
        // 避免客户端连接太快断开而导致Topic没有创建成功
        Thread.sleep(500);
        // 获取topic设置的partition数量
        System.out.println(result.numPartitions(topicName).get());
    }

    /**
     * 查询Topic列表
     */
    public static void topicLists() throws ExecutionException, InterruptedException {
        AdminClient adminClient = adminClient();
        ListTopicsResult result1 = adminClient.listTopics();
        // 打印Topic的名称
        System.out.println(result1.names().get());
        // 打印Topic的信息
        System.out.println(result1.listings().get());

        ListTopicsOptions options = new ListTopicsOptions();
        // 是否列出内部使用的Topic
        options.listInternal(true);
        ListTopicsResult result2 = adminClient.listTopics(options);
        System.out.println(result2.names().get());
    }

    /**
     * 删除Topic
     */
    public static void delTopics() throws ExecutionException, InterruptedException {
        AdminClient adminClient = adminClient();

        List<String> list = new ArrayList<>();
        list.add(TOPIC_NAME);

        DeleteTopicsResult result = adminClient.deleteTopics(list);
        System.out.println(result.all().get());
    }

    /**
     * 查询Topic的描述信息
     */
    public static void describeTopics() throws ExecutionException, InterruptedException {
        AdminClient adminClient = adminClient();

        List<String> list = new ArrayList<>();
        list.add(TOPIC_NAME);

        DescribeTopicsResult result = adminClient.describeTopics(list);
        Map<String, TopicDescription> descriptionMap = result.all().get();
        descriptionMap.forEach((key, value) ->
                System.out.println("name: " + key + ", desc: " + value));
    }

    /**
     * 查询Topic的配置信息
     */
    public static void describeConfig() throws ExecutionException, InterruptedException {

        AdminClient adminClient = adminClient();
        ConfigResource configResource = new ConfigResource(ConfigResource.Type.TOPIC, TOPIC_NAME);

        List<ConfigResource> list = new ArrayList<>();
        list.add(configResource);

        DescribeConfigsResult result = adminClient.describeConfigs(list);
        Map<ConfigResource, Config> map = result.all().get();
        map.forEach((key, value) -> System.out.println("name: " + key.name() + ", desc: " + value));
    }

    /**
     * 修改Topic的配置信息
     */
    public static void alterConfig() throws Exception {
        // 指定ConfigResource的类型及名称
        ConfigResource configResource = new ConfigResource(ConfigResource.Type.TOPIC, TOPIC_NAME);
        // 配置项以ConfigEntry形式存在
        List list = new ArrayList<>();
        list.add(new ConfigEntry("preallocate", "true"));
        Config config = new Config(list);

        AdminClient adminClient = adminClient();
        Map<ConfigResource, Config> configMaps = new HashMap<>();
        configMaps.put(configResource, config);
        AlterConfigsResult result = adminClient.alterConfigs(configMaps);
        System.out.println(result.all().get());
    }

    /**
     * 修改Topic的配置信息
     */
    public static void incrementalAlterConfig() throws Exception {
        // 指定ConfigResource的类型及名称
        ConfigResource configResource = new ConfigResource(ConfigResource.Type.TOPIC, TOPIC_NAME);
        // 配置项同样以ConfigEntry形式存在，只不过增加了操作类型
        // 以及能够支持操作多个配置项，相对来说功能更多、更灵活

        Collection<AlterConfigOp> configs = new ArrayList<>();
        configs.add(new AlterConfigOp(new ConfigEntry("preallocate", "false"), AlterConfigOp.OpType.SET));

        AdminClient adminClient = adminClient();
        Map<ConfigResource, Collection<AlterConfigOp>> configMaps = new HashMap<>();
        configMaps.put(configResource, configs);
        AlterConfigsResult result = adminClient.incrementalAlterConfigs(configMaps);
//        System.out.println(result.all().get());
    }
}
