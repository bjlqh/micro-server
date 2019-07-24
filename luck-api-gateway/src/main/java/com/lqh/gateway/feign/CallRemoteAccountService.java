package com.lqh.gateway.feign;

import com.lqh.dev.domain.Account;
import com.lqh.dev.model.ResponseResult;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("${luck.account}")
public interface CallRemoteAccountService {

    @PostMapping("/account/save")
    ResponseResult save(@RequestBody Account account);

    @PutMapping("/account/transfer")
    ResponseResult<Boolean> transfer(@RequestBody Account account);

    @GetMapping("/account/find/name")
    ResponseResult<Account> findOneByName(@RequestParam("name") String name);
}
