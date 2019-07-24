package com.lqh.gateway.controller;

import com.lqh.dev.model.ResponseResult;
import com.lqh.gateway.service.IAPIAccountService;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.math.BigDecimal;

@RestController
@RequestMapping("/api/account")
public class APIAccountController {

    @Resource
    private IAPIAccountService accountService;

    @PutMapping("/transfer")
    public ResponseResult transfer(@RequestParam String payer,
                                   @RequestParam String gather,
                                   @RequestParam BigDecimal money) {
        ResponseResult<String> response = new ResponseResult<>();
        String msg = accountService.transfer(payer, gather, money);
        response.setMsg(msg);
        return response;
    }
}
