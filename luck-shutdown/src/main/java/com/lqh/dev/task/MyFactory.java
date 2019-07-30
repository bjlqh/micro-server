package com.lqh.dev.task;

import java.util.concurrent.ThreadFactory;

public class MyFactory implements ThreadFactory {
    @Override
    public Thread newThread(Runnable r) {
        return new Thread(r);
    }
}
