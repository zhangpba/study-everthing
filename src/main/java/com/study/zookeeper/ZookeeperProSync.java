package com.study.zookeeper;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import java.util.concurrent.CountDownLatch;

/**
 * zookeeper
 * 2020-07-17
 */
public class ZookeeperProSync implements Watcher {

    private static CountDownLatch connectedSemaphore = new CountDownLatch(1);
    private static ZooKeeper zooKeeper = null;
    private static Stat stat = new Stat();

    public static void main(String[] args) throws Exception {

        // zookeeper配置数据存放路径
        String path = "/";

        //连接zookeeper并且注册一个默认监听
        zooKeeper = new ZooKeeper("127.0.0.1:2181", 5000, new ZookeeperProSync());


        //等待zk连接成功的通知
        connectedSemaphore.await();

        //获取path目录节点的配置数据，并注册默认的监听器
        System.out.println(new String(zooKeeper.getData(path, true, stat)));

        Thread.sleep(Integer.MAX_VALUE);
    }

    @Override
    public void process(WatchedEvent watchedEvent) {

        if (Event.KeeperState.SyncConnected == watchedEvent.getState()) {// zk连接成功通知事件
            if (Event.EventType.None == watchedEvent.getType() && null == watchedEvent) {
                connectedSemaphore.countDown();
            } else if (watchedEvent.getType() == Event.EventType.NodeDataChanged) {//zk目录节点数据变化通知事件
                try {
                    System.out.println("配置已修改，新值为：" + new String(zooKeeper.getData(watchedEvent.getPath(), true, stat)));
                } catch (Exception e) {
                    System.out.println("异常");
                }
            }
        }
    }
}
