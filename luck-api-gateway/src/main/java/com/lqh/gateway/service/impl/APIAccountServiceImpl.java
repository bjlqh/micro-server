package com.lqh.gateway.service.impl;

import com.lqh.dev.domain.Account;
import com.lqh.dev.model.ResponseResult;
import com.lqh.gateway.feign.CallRemoteAccount2Service;
import com.lqh.gateway.feign.CallRemoteAccountService;
import com.lqh.gateway.service.IAPIAccountService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;

@Service
public class APIAccountServiceImpl implements IAPIAccountService {

    @Resource
    private CallRemoteAccountService callRemoteAccountService;

    @Resource
    private CallRemoteAccount2Service callRemoteAccount2Service;

    @Override
    public String transfer(String payerName, String gatherName, BigDecimal money) {
        ResponseResult<Account> result1 = callRemoteAccountService.findOneByName(payerName);
        Account payer = result1.getData();
        BigDecimal balance = payer.getBalance();
        BigDecimal subtract = balance.subtract(money);
        payer.setBalance(subtract);
        ResponseResult<Boolean> resp = callRemoteAccountService.transfer(payer);
        ResponseResult<Account> result2 = callRemoteAccount2Service.findOneByName(gatherName);
        Account gather = result2.getData();
        BigDecimal balance1 = gather.getBalance();
        gather.setBalance(balance1.add(money));
        ResponseResult<Boolean> resp2 = callRemoteAccount2Service.transfer(gather);
        return resp.getMsg()+":"+resp2.getMsg();
    }
}
