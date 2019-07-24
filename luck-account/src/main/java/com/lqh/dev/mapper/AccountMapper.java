package com.lqh.dev.mapper;

import com.lqh.dev.base.mybatis.BaseMapper;
import com.lqh.dev.domain.Account;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AccountMapper extends BaseMapper<Account> {
}
