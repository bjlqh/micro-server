package com.lqh.dev.mytransactional.task;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MyTask {
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void waitTask() {
        try {
            lock.lock();
            condition.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void signalTask() {
        lock.lock();
        condition.signal();
        lock.unlock();
    }
}
