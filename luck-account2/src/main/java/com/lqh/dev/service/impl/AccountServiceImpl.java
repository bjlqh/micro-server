package com.lqh.dev.service.impl;

import com.lqh.dev.domain.Account;
import com.lqh.dev.mapper.AccountMapper;
import com.lqh.dev.service.IAccountService;
import com.lqh.dev.mytransactional.annotation.MyTransactional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
public class AccountServiceImpl implements IAccountService {

    @Resource
    private AccountMapper accountMapper;

    @Override
    public Account selectOneByName(String name) {
        Account account = new Account();
        account.setName(name);
        return accountMapper.selectOne(account);
    }

    @Override
    public Long save(Account account) {
        return (long) accountMapper.insertSelective(account);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @MyTransactional(isEnd = true)
    public void transfer(Account account) {
        accountMapper.updateByPrimaryKey(account);
    }
}
