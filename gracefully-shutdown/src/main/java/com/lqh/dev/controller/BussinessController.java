package com.lqh.dev.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BussinessController {

    @RequestMapping("/working")
    public Object working(@RequestParam(required = false, defaultValue = "5000") Long time) throws InterruptedException {
        System.out.println("业务开始");
        Thread.sleep(time);
        System.out.println("业务结束");
        return "业务结束";
    }
}
