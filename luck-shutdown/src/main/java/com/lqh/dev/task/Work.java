package com.lqh.dev.task;

public class Work implements Runnable {
    private String info;

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "started,Info is:" + this.info);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "finished");
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Work(String info) {
        this.info = info;
    }

    public Work() {
    }

    @Override
    public String toString() {
        return "Work{" +
                "info='" + info + '\'' +
                '}';
    }
}
