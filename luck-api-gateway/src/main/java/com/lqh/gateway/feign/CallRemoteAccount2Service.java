package com.lqh.gateway.feign;

import com.lqh.dev.domain.Account;
import com.lqh.dev.model.ResponseResult;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("${luck.account2}")
public interface CallRemoteAccount2Service {

    @GetMapping("/account2/find/name")
    ResponseResult<Account> findOneByName(@RequestParam("name") String name);

    @PutMapping("/account2/transfer")
    ResponseResult<Boolean> transfer(@RequestBody Account account);
}
