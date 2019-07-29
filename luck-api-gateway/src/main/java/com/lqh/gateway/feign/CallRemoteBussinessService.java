package com.lqh.gateway.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("${luck.shutdown}")
public interface CallRemoteBussinessService {

    @RequestMapping("/working")
    String working(@RequestParam(value = "time", defaultValue = "10000", required = false) Long time) throws InterruptedException;
}
