package com.lqh.dev.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author One
 * @Description
 * @date 2019/07/21
 */
@Slf4j
@RestController
public class BusinessController {

    @RequestMapping("/working")
    public String working(@RequestParam(required = false, defaultValue = "10000") Long time) throws InterruptedException {
        log.warn("开始处理业务");
        Thread.sleep(time);
        log.warn("结束处理业务");
        return "业务完成";
    }
}
