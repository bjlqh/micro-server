package com.lqh.dev.controller;

import com.lqh.dev.domain.Account;
import com.lqh.dev.model.ResponseResult;
import com.lqh.dev.service.IAccountService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/account2")
public class AccountController {

    @Resource
    private IAccountService accountService;

    @GetMapping("/find/name")
    public ResponseResult<Account> findOneByName(String name) {
        ResponseResult<Account> response = new ResponseResult<>();
        Account account = accountService.selectOneByName(name);
        response.setData(account);
        return response;
    }

    @PutMapping("/transfer")
    public ResponseResult<Boolean> transfer(@RequestBody Account account) {
        ResponseResult<Boolean> result = new ResponseResult<>();
        Boolean flag = false;
        accountService.transfer(account);
        flag = true;
        if (flag) {
            result.setMsg("account2转账成功");
        }
        result.setData(flag);
        return result;
    }
}
