package com.lqh.dev.task;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MyTask implements Runnable {
    private String info;

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " started  Info is: " + this.info);
        try {
            Thread.sleep(8_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() +" "+this.info +"  finished");
    }
}


