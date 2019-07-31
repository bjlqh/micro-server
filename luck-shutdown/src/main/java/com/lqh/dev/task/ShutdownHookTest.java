package com.lqh.dev.task;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ShutdownHookTest {

    private static final int TIMEOUT = 3_000;
    private static ThreadPoolExecutor pool;
    private static ExecutorService fixedThreadPool = Executors.newFixedThreadPool(5);


    static {
        int coreSize = 2;
        int maxSize = 4;
        long keepAlive = 10;
        TimeUnit unit = TimeUnit.SECONDS;
        int queueSize = 2;
        LinkedBlockingDeque<Runnable> deque = new LinkedBlockingDeque<>(queueSize);
        ThreadFactory factory = new MyFactory();
        //RejectedExecutionHandler rejectedHandler = new MyRejectedHandler();
        //ThreadPoolExecutor.AbortPolicy rejectedHandler = new ThreadPoolExecutor.AbortPolicy();
        ThreadPoolExecutor.DiscardPolicy rejectedHandler = new ThreadPoolExecutor.DiscardPolicy();
        pool = new ThreadPoolExecutor(coreSize, maxSize, keepAlive, unit, deque, factory, rejectedHandler);
    }

    public static void main(String[] args) {

        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("invoke shutdownhook");

                pool.shutdown();
                fixedThreadPool.shutdown();
                long time = System.currentTimeMillis();
                while (true) {
                    int activeCount = pool.getActiveCount();
                    int waitCount = pool.getQueue().size();

                    ThreadPoolExecutor executor = (ThreadPoolExecutor) fixedThreadPool;
                    int activeCount1 = executor.getActiveCount();
                    int waitCount1 = executor.getQueue().size();

                    System.out.println("有" + waitCount + "个线程在等待");
                    System.out.println("有" + activeCount + "个线程在工作");

                    System.out.println("有[" + waitCount1 + "]个线程在等待");
                    System.out.println("有[" + activeCount1 + "]个线程在工作");

                    if (pool.isTerminated() && fixedThreadPool.isTerminated()) {
                        System.out.println("等待线程池退出");
                        break;
                    }

                    if (System.currentTimeMillis() - time > TIMEOUT) {
                        System.out.println("线程关闭超时,将强制关闭");
                        pool.shutdown();
                        fixedThreadPool.shutdown();
                    }

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("线程池退出！");
            }
        }));

        System.out.println("==========任务1开始=========");
        for (int i = 1; i <= 7; i++) {
            if (pool.isShutdown()) {
                break;
            }
            pool.submit(new MyTask("work" + "[" + i + "]"));
        }

        System.out.println("========任务2开始=======");
        for (int i = 1; i <= 20; i++) {
            MyTask work = new MyTask("work " + i);
            fixedThreadPool.execute(work);
        }
    }

}
