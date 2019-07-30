package com.lqh.dev.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@Component
public class MyTask {

    private static ExecutorService pool = Executors.newFixedThreadPool(5);

    @Scheduled(fixedDelay = 30000)
    public void executeTask() {
        System.out.println("========任务开始=======");
        for (int i = 1; i <= 20; i++) {
            Work work = new Work("work " + i);
            pool.execute(work);
        }
        pool.shutdown();
        System.out.println("all thread work was finished");
    }

    @Scheduled(fixedDelay = 30000)
    public void execute() {

    }


    //@Scheduled(fixedDelay = 3000)
    public void task() throws InterruptedException {
        System.out.println("========任务开始=======");
        int count = 0;
        for (int i = 0; i < 20; i++) {
            Thread.sleep(2000);
            count++;
            System.out.println(i);
        }
        System.out.println("========任务结束：count=" + count);
    }

}
