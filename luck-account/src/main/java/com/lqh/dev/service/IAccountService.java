package com.lqh.dev.service;

import com.lqh.dev.domain.Account;

public interface IAccountService {

    Account selectOneByName(String name);

    Long save(Account account);

    void transfer(Account account);

    void print();
}
