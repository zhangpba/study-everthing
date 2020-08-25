package com.study.zookeeper;

import org.apache.zookeeper.*;

import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * zooleeper中的watch事件
 * 2020-07-17
 * <p>
 * 主要是KeeperState.SyncConnected 中几种事件类型：
 * <p>
 * EventType.NodeCreated : 节点创建事件类型
 * EventType.NodeDeleted : 节点被删除
 * EventType.NodeDataChanged : 节点被修改
 * EventType.None : 客户端与服务器成功建立会话
 * EventType.NodeChildrenChanged : 子节点列表发生变更
 * <p>
 * https://blog.csdn.net/MOVIE14/article/details/81349272
 */
public class ZookeeperClient implements Watcher {

    private static final String host = "127.0.0.1:2181";
    private static CountDownLatch countDownLatch = new CountDownLatch(1);

    public static void main(String[] args) throws Exception {
        ZooKeeper zooKeeper = new ZooKeeper(host, 3000, new ZookeeperClient());
        // 阻塞线程:zookeeper创建连接是异步，如果不阻塞主线程的话，在运行完 new Zookeeper之后便会结束，这样就看不到process函数被回调。
        // 调用await()方法的线程会被挂起，它会等待直到count值为0才继续执行
        countDownLatch.await();

        String path = "/test5";
        String childrenPath = "/children-1";

        String data = "初始化的数据";
        String updateData = "初始化的数据";

        // 1.创建节点
        create(zooKeeper, path, data);

        // 2.修改节点
        update(zooKeeper, path, updateData);

        // 3.删除节点
        delete(zooKeeper, path);

        // 4.操作子节点
        children(zooKeeper, path, childrenPath, "持久化节点数据", "子节点数据");

        // 5.查询节点
        getData(zooKeeper);

        String result = getData(zooKeeper, path);
        System.out.println("节点-" + path + "中的数据：" + result);
        String resultChildren = getData(zooKeeper, path + childrenPath);
        System.out.println("节点-" + path + childrenPath + "中的数据：" + resultChildren);

    }

    // 查看指定节点下的内容
    public static void getData(ZooKeeper zooKeeper) throws Exception {
        System.out.println("----------------------");
        List<String> childrens = zooKeeper.getChildren("/", true);
        childrens.forEach(children -> {
            System.out.println("返回的节点：" + children);
        });
        System.out.println("----------------------");
    }

    /**
     * 查看节点的数据（节点的值和状态stat）
     *
     * @param zooKeeper 客户端
     * @param path      节点
     * @return 节点的数据
     * @throws Exception
     */
    public static String getData(ZooKeeper zooKeeper, String path) throws Exception {
        byte[] data = zooKeeper.getData(path, true, null);
        String result = new String(data);
        return result;
    }

    /**
     * 创建节点
     *
     * @param zooKeeper 客户端
     * @param path      节点
     * @param data      数据
     * @throws Exception
     */
    public static void create(ZooKeeper zooKeeper, String path, String data) throws Exception {
        // 设置我们的节点被监听
        zooKeeper.exists(path, true);
        // 创建节点
        zooKeeper.create(path, data.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
    }

    /**
     * 删除节点
     *
     * @param zooKeeper 客户端
     * @param path      节点
     * @throws Exception
     */
    public static void delete(ZooKeeper zooKeeper, String path) throws Exception {
        // 设置我们的节点被监听
        zooKeeper.exists(path, true);
        // 删除节点
        zooKeeper.delete(path, -1);
    }

    /**
     * 修改节点
     *
     * @param zooKeeper 客户端
     * @param path      节点
     * @param data      数据
     * @throws Exception
     */
    public static void update(ZooKeeper zooKeeper, String path, String data) throws Exception {
        // 设置我们的节点被监听
        zooKeeper.exists(path, true);
        // 修改节点
        zooKeeper.setData(path, data.getBytes(), -1);
    }

    /**
     * @param zooKeeper    客户端
     * @param path         持久化节点
     * @param childrenPath 子节点
     * @param data         持久化节点数据
     * @param childrenData 子节点数据
     * @throws Exception
     */
    public static void children(ZooKeeper zooKeeper, String path, String childrenPath, String data, String childrenData) throws Exception {
        // 子节点
        zooKeeper.exists(path, true);
        // 创建节点，OPEN_ACL_UNSAFE表示acl权限列表为完全开放，PERSISTENT表示节点类型为持久化节点
        zooKeeper.create(path, data.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        zooKeeper.getChildren(path, true);
        zooKeeper.create(path + childrenPath, childrenData.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
    }

    @Override
    public void process(WatchedEvent watchedEvent) {
        // 将count减1
        countDownLatch.countDown();
        System.out.println("监听器-接收的事件: " + watchedEvent);
        System.out.println("监听器-事件类型 : " + watchedEvent.getType());
    }
}
