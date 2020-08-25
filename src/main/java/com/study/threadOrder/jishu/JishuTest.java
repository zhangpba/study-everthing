package com.study.threadOrder.jishu;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class JishuTest {
    public static void main(String[] args) {

        ExecutorService executor = Executors.newFixedThreadPool(7);
        int taskNum = 10;
        for (int i = 0; i < 10; i++) {
            AnyTask anyTask = new AnyTask();
            executor.execute(anyTask);
        }

        EndTask endTask = new EndTask(taskNum);
        executor.execute(endTask);
        executor.shutdown();
    }
}
