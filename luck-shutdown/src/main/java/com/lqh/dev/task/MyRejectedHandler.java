package com.lqh.dev.task;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

public class MyRejectedHandler implements RejectedExecutionHandler {
    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        executor.execute(r);
        System.err.println("线程池拒绝了任务");
        System.err.println("线程池"+executor);
        System.err.println("任务"+r);
    }
}
