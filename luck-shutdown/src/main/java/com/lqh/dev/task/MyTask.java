package com.lqh.dev.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MyTask {

    @Scheduled(fixedDelay = 3000)
    public void taskExecutor() throws InterruptedException {
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
