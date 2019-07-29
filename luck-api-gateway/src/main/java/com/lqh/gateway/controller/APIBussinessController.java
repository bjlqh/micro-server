package com.lqh.gateway.controller;

import com.lqh.gateway.feign.CallRemoteBussinessService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api")
@Slf4j
public class APIBussinessController {

    @Resource
    private CallRemoteBussinessService callRemoteBussinessService;

    @RequestMapping("/working")
    public String working(@RequestParam(required = false, defaultValue = "10000") Long time) throws InterruptedException {
        return callRemoteBussinessService.working(time);
    }
}
