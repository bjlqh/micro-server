package com.lqh.dev.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.sql.PooledConnection;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class TaskHandler {

    private static ExecutorService fixedThreadPool = Executors.newFixedThreadPool(5);

    private static ExecutorService customPool;
    static {
        int coreSize = 2;
        int maxSize = 4;
        long keepAlive = 10;
        TimeUnit unit = TimeUnit.SECONDS;
        int queueSize = 2;
        LinkedBlockingDeque<Runnable> deque = new LinkedBlockingDeque<>(queueSize);
        ThreadFactory factory = new MyFactory();
        RejectedExecutionHandler rejectedHandler = new MyRejectedHandler();
        customPool = new ThreadPoolExecutor(coreSize, maxSize, keepAlive, unit, deque, factory, rejectedHandler);
    }

    @Scheduled(fixedDelay = 10000)
    public void execute() {
        System.out.println("========任务开始=======");
        int taskCount = 4;
        for (int i = 1; i <=4 ; i++) {
            customPool.execute(new MyTask("work"+"["+i+"]"));
        }

        System.out.println(customPool.isShutdown());
        System.out.println(customPool.isTerminated());
        System.out.println("========任务结束=======");
    }

    //@Scheduled(fixedDelay = 30000)
    public void executeTask() {
        System.out.println("========任务开始=======");
        for (int i = 1; i <= 20; i++) {
            MyTask work = new MyTask("work " + i);
            fixedThreadPool.execute(work);
        }
        fixedThreadPool.shutdown();
        System.out.println("all thread work was finished");
    }
}
