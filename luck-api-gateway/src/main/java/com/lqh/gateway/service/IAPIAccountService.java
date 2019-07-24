package com.lqh.gateway.service;

import java.math.BigDecimal;

public interface IAPIAccountService {

    String transfer(String payer, String gather, BigDecimal money);
}
