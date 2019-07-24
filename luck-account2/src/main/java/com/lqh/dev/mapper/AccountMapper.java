package com.lqh.dev.mapper;

import com.lqh.dev.base.BaseMapper;
import com.lqh.dev.domain.Account;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface AccountMapper extends BaseMapper<Account> {
}
